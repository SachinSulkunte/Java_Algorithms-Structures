package processor;

import java.util.*;
import java.io.*;
import java.text.NumberFormat;

public class OrdersProcessor implements Runnable {

	public String fileName, baseName, resultsName;
	public int orders;
	public TreeMap<String, Integer> orderItems;
	public int clientID;
	public static Order lists = new Order(); // holds threads list and object list

	public OrdersProcessor(String fileName, int orders, String baseName, String resultsFile) {

		this.fileName = fileName;
		this.orders = orders;
		this.baseName = baseName;
		resultsName = resultsFile;
		orderItems = new TreeMap<String, Integer>();
		clientID = 0;
	}

	public static void main(String[] args) {

		Scanner key = new Scanner(System.in);

		System.out.println("Enter item's data file name: ");
		String fileName = key.next();

		System.out.println("Enter 'y' for multiple threads, any other character otherwise: ");
		String threads = key.next();

		System.out.println("Enter number of orders to process: ");
		int orders = key.nextInt();

		System.out.println("Enter order's base filename: ");
		String baseName = key.next();

		System.out.println("Enter results filename: ");
		String results = key.next();

		long startTime = System.currentTimeMillis(); // start timer

		readDataIntoArr(fileName, lists.items); // writes inventory data to shared map

		OrdersProcessor first = new OrdersProcessor(fileName, orders, baseName, results);
		first.createThreads(orders, lists.threadList, lists.objList); // creates list of threads

		if (threads.equalsIgnoreCase("y")) { // multithreaded processing

			for (Thread t : lists.threadList) {

				t.start(); // begin thread processing
			}

			for (Thread end : lists.threadList) {

				try {
					end.join(); // verifies threads finish before continuing
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		else { // single thread processing

			for (Thread t : lists.threadList) {

				t.run(); // runs each 'thread' in single-thread fashion
			}
		}

		key.close();

		StringBuilder summary = writeSummary(lists);	//generate total summary
		writeData(results, lists.output, summary); // compile output
		long endTime = System.currentTimeMillis(); // end timing

		System.out.println("Processing time (msec): " + (endTime - startTime));
		System.out.println("Results can be found in the file: " + results);
	}

	@Override
	public void run() {

		this.userOrder(); // reads in user order information to item array

		genOutput(this, lists);
		genSummary(this, lists); // synchronized method
		System.out.println("Reading order for client with id: " + this.clientID); //includes customer id

	}

	public static TreeMap<String, Double> readDataIntoArr(String fileName, TreeMap<String, Double> items) {

		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			Scanner fileScan = new Scanner(bufferedReader);

			while (fileScan.hasNextLine()) {

				String itemName = fileScan.next();
				double itemPrice = fileScan.nextDouble();

				items.put(itemName, itemPrice);	//reads in items and prices to arraylist
			}

			fileScan.close();

			return items;
		} catch (IOException e) {

			System.err.println(e.getMessage());
		}

		return items;
	}

	public static void writeData(String fileName, TreeMap<Integer, StringBuilder> output, StringBuilder summary) {

		try {

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false));

			for (Integer s : output.keySet()) {

				String out = output.get(s).toString();
				bufferedWriter.write(out); // writes client's output to file
				bufferedWriter.newLine();
			}

			String sum = summary.toString();
			bufferedWriter.write(sum);	//adds summary to the end
			bufferedWriter.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public ArrayList<Thread> createThreads(int order, ArrayList<Thread> threads, ArrayList<OrdersProcessor> objects) {

		for (int i = 0; i < order; i++) {

			String base = this.baseName + (i + 1) + ".txt"; // concatenates proper file ending
			OrdersProcessor newOrder = new OrdersProcessor(this.fileName, this.orders, base, this.resultsName);
			Thread newThread = new Thread(newOrder);

			threads.add(newThread);
			objects.add(newOrder);
		}

		return threads;
	}

	public TreeMap<String, Integer> userOrder() {	//reads in user purchase information

		try {
			FileReader fileReader = new FileReader(this.baseName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			Scanner fileScan = new Scanner(bufferedReader);

			fileScan.next();	//skip over initial text
			this.clientID = fileScan.nextInt(); // read in client id

			while (fileScan.hasNextLine()) {

				String itemName = fileScan.next();
				fileScan.next(); // pass over date
				if (this.orderItems.containsKey(itemName)) {

					int quantity = this.orderItems.get(itemName);
					this.orderItems.put(itemName, quantity + 1); // increments quantity by one
				} else {

					this.orderItems.put(itemName, 1);
				}
			}

			fileScan.close();
		} catch (IOException e) {

			System.err.println(e.getMessage());
		}

		return this.orderItems;
	}

	public static TreeMap<Integer, StringBuilder> genOutput(OrdersProcessor current, Order lists) {

		StringBuilder outputClient = new StringBuilder();
		outputClient.append("----- Order details for client with Id: " + current.clientID + " -----\n");

		double orderTotal = 0; // track overall total price
		for (String entry : current.orderItems.keySet()) {

			outputClient.append("Item's name: " + entry + ", ");

			outputClient.append("Cost per item: " + NumberFormat.getCurrencyInstance().format(lists.items.get(entry)));

			outputClient.append(", Quantity: " + current.orderItems.get(entry) + ", ");

			double totalCost = current.orderItems.get(entry) * lists.items.get(entry);
			orderTotal += totalCost;

			outputClient.append("Cost: " + NumberFormat.getCurrencyInstance().format(totalCost) + "\n");
		}

		outputClient.append("Order Total: " + NumberFormat.getCurrencyInstance().format(orderTotal));

		lists.output.put(current.clientID, outputClient);
		return lists.output;
	}

	public static TreeMap<String, Integer> genSummary(OrdersProcessor current, Order lists) {

		for (String entry : current.orderItems.keySet()) {

			int quantity = 0;

			synchronized (lists) {	//locked on Order object

				if (lists.totals.get(entry) != null) {

					quantity = lists.totals.get(entry);
				}
				quantity += current.orderItems.get(entry);

				lists.totals.put(entry, quantity);	//updates shared map of total quantity purchased
			}
		}
		return lists.totals;

	}

	public static StringBuilder writeSummary(Order lists) {

		StringBuilder summary = new StringBuilder();

		summary.append("***** Summary of all orders *****\n");

		double orderTotal = 0; // track overall total price
		for (String entry : lists.totals.keySet()) {

			summary.append("Summary - ");
			summary.append("Item's name: " + entry + ", ");

			summary.append("Cost per item: " + NumberFormat.getCurrencyInstance().format(lists.items.get(entry)));

			summary.append(", Number sold: " + lists.totals.get(entry) + ", ");

			double totalCost = lists.totals.get(entry) * lists.items.get(entry);
			orderTotal += totalCost;

			summary.append("Item's Total: " + NumberFormat.getCurrencyInstance().format(totalCost) + "\n");
		}

		for (String entry : lists.totals.keySet()) {

			lists.totalCost += lists.items.get(entry) * lists.totals.get(entry); // adds price of each item
		}

		summary.append("Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(lists.totalCost) + "\n");
		return summary;
	}
}
