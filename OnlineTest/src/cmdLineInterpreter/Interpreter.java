package cmdLineInterpreter;
import java.util.Scanner;

import onlineTest.SystemManager;
/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter {

	static Scanner key = new Scanner(System.in);

	public static void main(String[] args) {
		
		SystemManager manager = new SystemManager();
		
		Boolean cont = true;
		
		while(cont == true) {
			
			printMenu(); //see helper method for displaying menu
			System.out.println("Enter choice (numerically): ");
			int choice = key.nextInt();
			
			if(choice == 1) {
				System.out.println("Enter the student's name as LastName,FirstName");
				String name = key.next();
				manager.addStudent(name);
				System.out.println("Task completed");
			}
			else if(choice == 2) {
				
				System.out.println("Enter the exam id followed by the exam title");
				int id = key.nextInt();
				String title = key.next();
				manager.addExam(id, title);
				System.out.println("Task completed");
			}
			else if(choice == 3) {
				
				System.out.println("Enter the exam ID for the question: ");
				int id = key.nextInt();
				System.out.println("Enter the question's text: ");
				String text = key.next();
				text += key.nextLine();
				System.out.println("Enter the question number: ");
				int num = key.nextInt();
				System.out.println("Enter the number of points: ");
				double points = key.nextDouble();
				System.out.println("Enter the correct answer as either 'true' or 'false'");
				String answer = key.next();
				
				Boolean ans = true;
				if(answer.equals("true")) {
					
					ans = true;
				}
				else {
					ans = false;
				}
				
				manager.addTrueFalseQuestion(id, num, text, points, ans);
				System.out.println("Task completed");
			}
			else if(choice == 4) {
				
				System.out.println("Enter the student's name in LastName,FirstName format: ");
				String name = key.next();
				System.out.println("Enter the exam ID for the question: ");
				int id = key.nextInt();
				System.out.println("Enter the question number: ");
				int num = key.nextInt();
				System.out.println("Enter the correct answer as either 'true' or 'false'");
				String answer = key.next();
				
				Boolean ans = true;
				if(answer.equals("true")) {
					
					ans = true;
				}
				else {
					ans = false;
				}
				
				manager.answerTrueFalseQuestion(name, id, num, ans);
				System.out.println("Task completed");
			}
			else if(choice == 5) {
				
				System.out.println("Enter student's name in LastName,FirstName format: ");
				String name = key.next();
				System.out.println("Enter the id for the exam to be graded: ");
				int id = key.nextInt();
				
				System.out.println(manager.getExamScore(name, id));
			}
			else {
				
				System.out.println("Invalid input");
			}
			
			cont = cont();
		}
		
		System.out.println("Interpreter closed");
	}
	
	private static void printMenu() {
		
		System.out.println("1. Add a Student");
		System.out.println("2. Add an Exam");
		System.out.println("3. Add a True/False Question");
		System.out.println("4. Answer a True/False Question");
		System.out.println("5. Get Exam Score for a Student");
	}	
	
	private static Boolean cont() {
		
		System.out.println("Do you wish to continue? Enter 'yes' or 'no': ");
		String ans = key.next();
		if(ans.equals("yes")) {
			
			return true;
		}
		else {
			return false;
		}
	}
}
