package processor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class Order {

	public ArrayList<Thread> threadList;
	public ArrayList<OrdersProcessor> objList; //track all order objects
	public TreeMap<String, Double> items; //shared between all threads
	public TreeMap<Integer, StringBuilder> output;	//final output
	public TreeMap<String, Integer> totals;	//shared resource for all threads -- avoid data races
	public double totalCost;
	
	public Order(){
	
		threadList = new ArrayList<Thread>(); 
		objList = new ArrayList<OrdersProcessor>();
		output = new TreeMap<Integer, StringBuilder>();
		items = new TreeMap<String, Double>();
		
		totals = new TreeMap<String, Integer>();
		totalCost = 0;
	}
	
}
