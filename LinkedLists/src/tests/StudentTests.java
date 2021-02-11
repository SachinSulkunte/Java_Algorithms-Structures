package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

public class StudentTests {

	public static final String TESTS_TAG = "\nLinkedList";
	
	/*
	 * Creation of basic list
	 * BasicLinkedList constructor
	 */
	@Test
	public void testCreation() {	
		String answer = "";
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		
		list.addToFront("Red");
		list.addToFront("Green");
		list.addToEnd("Red");
		list.addToEnd("Blue");
		list.addToEnd("Yellow");
		
		answer += "List:\n";
		for(String color : list) {
			
			answer += color + "\n";
		}
		
		answer += list.getSize();
		
		assertTrue(TestSupport.isCorrect("StudentTest_testCreation.txt", answer));
	}
	
	/*
	 * Creation of sorted list
	 * SortedLinkedList constructor
	 */
	@Test
	public void testSortedCreation() {
		String answer = "";

		SortedLinkedList<String> list = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		SortedLinkedList<String> list2 = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);

		
		list.add("Red");
		list.add("Green");
		list.add("Yellow");
		list.add("Blue");
		
		list2.add("B").add("b").add("c").add("D").add("s");
		
		answer += "List:\n";
		for(String color : list) {
			
			answer += color + "\n";
		}
		
		answer += "Size of Color List: " + list.getSize() + "\n\n";
		answer += "List 2\n";
		for(String letter : list2) {
			
			answer += letter + "\n";
		}
		
		answer += "Size of List 2: " + list2.getSize();
		
		assertTrue(TestSupport.isCorrect("StudentTest_SortedList.txt", answer));
	}
	
	/*
	 * test – addToFront()
	 */
	@Test
	public void testAddToFr() {
		String answer = "";
		
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		
		list.addToFront("Red");
		list.addToFront("Green");
		list.addToFront("Red");
		list.addToFront("Blue");
		list.addToFront("Yellow");
		
		answer += "List:\n";
		for(String color : list) {
			
			answer += color + "\n";
		}
		
		answer += list.getSize();
		
		SortedLinkedList<String> list2 = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		try {
			
			list2.addToFront("Purple");
		} catch (UnsupportedOperationException e) {
			
			answer += "\nUnsupported Operation for Sorted List\n";
		}
		
		assertTrue(TestSupport.isCorrect("StudentTest_addToFront.txt", answer));
	}
	
	@Test
	public void testaddToEnd() {
		String answer = "";
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		
		list.addToEnd("Red");
		list.addToEnd("Green");
		list.addToEnd("Red");
		list.addToEnd("Blue");
		list.addToEnd("Yellow");

		answer += "List:\n";
		for(String color : list) {
			
			answer += color + "\n";
		}
		
		SortedLinkedList<String> list2 = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		try {
			
			list2.addToEnd("Purple");
			list2.addToEnd("Blue");
			
		} catch (UnsupportedOperationException e) {
			
			answer += "\nUnsupported Operation for Sorted List\n";
		}
		
		assertTrue(TestSupport.isCorrect("StudentTest_addToEnd.txt", answer));
	}
	
	/*
	 * test size method
	 */
	@Test
	public void testSize() {
		
		String answer = "";
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		
		list.addToFront("Red");
		list.addToFront("Green");
		list.addToEnd("Red");
		list.addToEnd("Blue");
		list.addToEnd("Yellow");
		
		answer += "Removing blue\n";
		list.remove("Blue", String.CASE_INSENSITIVE_ORDER);
		answer += list.getSize() + "\n";
		
		answer += "Removing red";
		list.remove("Red", String.CASE_INSENSITIVE_ORDER);
		answer += "\n" + list.getSize();
		
		SortedLinkedList<String> list2 = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		list2.add("S").add("D").add("C");
		answer += "\nSize of List 2: " + list2.getSize();
		
		BasicLinkedList<String> nullList = new BasicLinkedList<String>();
		answer += "\nSize of empty List: " + nullList.getSize();
		
		list2.remove("D");
		list2.remove("S");
		
		answer += "\nSize after removal: " + list2.getSize();
		
		assertTrue(TestSupport.isCorrect("StudentTest_Size.txt", answer));
	}
	
	/*
	 * test getFirst() and getLast()
	 */
	@Test
	public void testFirstAndLast() {
		
		String answer = "";
		BasicLinkedList<String> list = new BasicLinkedList<String>();

		list.addToFront("Blue").addToEnd("Purple");
		answer += list.getFirst() + "\n";
		answer += list.getLast() + "\n";
		
		SortedLinkedList<String> list2 = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		list2.add("Yellow").add("Green").add("Blue");
		answer += list2.getFirst() + "\n";
		answer += list2.getLast() + "\n";
		
		assertTrue(TestSupport.isCorrect("StudentTest_get.txt", answer));
	}
	
	/*
	 * retrieveFirstElement() and retrieveLastElement()
	 */
	@Test
	public void testRetrieve() {
		String answer = "";
		BasicLinkedList<String> list = new BasicLinkedList<String>();

		list.addToEnd("G");
		list.addToFront("B");
		list.addToEnd("F");
		
		answer += "Original List\n";
		for(String letter : list) {
			
			answer += letter + "\n";
		}

		answer += "First Element: " + list.retrieveFirstElement() + "\n";
		answer += "Last Element: " + list.retrieveLastElement() + "\n";
		
		answer += "Updated List";
		for(String letter : list) {
			
			answer += letter + "\n";
		}
		
		assertTrue(TestSupport.isCorrect("StudentTest_retrieve.txt", answer));
	}
	
	/*
	 * test remove() and sortedlist remove()
	 */
	@Test
	public void testRemove() {
		
		String answer = "";
		BasicLinkedList<String> list = new BasicLinkedList<String>();

		SortedLinkedList<String> list2 = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		list.addToFront("Blue").addToEnd("Purple");
		
		list2.add("Green").add("Blue");
		
		for(String color : list) {
			
			answer += color + "\n";
		}

		answer += "Removing blue from list 1\n";
		list.remove("blue", String.CASE_INSENSITIVE_ORDER);
		answer += list.getSize() + "\n";
		
		answer += "Removing red";
		list.remove("Red", String.CASE_INSENSITIVE_ORDER);
		
		for(String color : list) {
			
			answer += color + "\n";
		}
		
		answer += "Removing blue from list 2\n";
		list2.remove("Blue");
		for(String color: list2) {
			
			answer += color + "\n";
		}
		assertTrue(TestSupport.isCorrect("StudentTest_remove.txt", answer));
	}
	
	/*
	 * iterator test
	 */
	@Test
	public void testIterator() {
		String answer = "";
		BasicLinkedList<String> list = new BasicLinkedList<String>();

		SortedLinkedList<String> list2 = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);

		list.addToEnd("Red");
		list.addToEnd("Green");
		list.addToEnd("Red");
		list.addToEnd("Blue");
		list.addToEnd("Yellow");
		
		list2.add("B").add("b").add("c").add("D").add("s");

		answer += "First List: \n";

		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			
			answer += it.next();
		}
		
		answer += "Second List: \n";
		for(String element: list2) {
			
			answer += element + "\n";
		}
		assertTrue(TestSupport.isCorrect("StudentTest_iterator.txt", answer));
	}
	
	@Test
	public void testSortedAdd() {
		
		String answer = "";
		SortedLinkedList<String> list = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		list.add("B");
		list.add("C");
		list.add("S");
		list.add("F");
		
		for(String element: list) {
			
			answer += element + "\n";
		}
		assertTrue(TestSupport.isCorrect("StudentTest_SortedAdd.txt", answer));
	}
	
	/*
	 * test reverseList method for basic and sorted lists
	 */
	@Test
	public void testReverseList() {
		
		String answer = "";
		BasicLinkedList<Integer> list = new BasicLinkedList<Integer>();
		
		list.addToFront(5);
		list.addToFront(4);
		list.addToFront(3);
		list.addToFront(2);
		list.addToFront(1);
		
		answer += "Basic List\n";
		BasicLinkedList<Integer> reverse = list.getReverseList();
		for(Integer i : list) {
			
			answer += i + "\n";
		}
		
		answer += "\n\nReversed Basic List\n";
		for(Integer i : reverse) {
			
			answer += i + "\n";
		}
		
		SortedLinkedList<String> listS = new SortedLinkedList(String.CASE_INSENSITIVE_ORDER);
		
		listS.add("Green").add("Purple").add("Yellow").add("Red");
		
		BasicLinkedList<String> reversedSorted = listS.getReverseList();
		answer += "\nSorted List\n";
		for(String i : listS) {
			
			answer += i + "\n";
		}
		
		answer += "\nReverse Sorted List\n";
		for(String i : reversedSorted) {
			
			answer += i + "\n";
		}
		
		answer += "\nTest null list";
		BasicLinkedList<String> nullList = new BasicLinkedList<String>();
		BasicLinkedList<String> reversedNull = nullList.getReverseList();
		for(String i : reversedNull) {
			
			answer += i + "/n";
		}
		
		assertTrue(TestSupport.isCorrect("StudentTest_reverseList.txt", answer));
	}
	
	@Test
	public void testReverseArray() {
		
		String answer = "";
		BasicLinkedList<Integer> list = new BasicLinkedList<Integer>();
		
		list.addToFront(5);
		list.addToFront(4);
		list.addToFront(3);
		list.addToFront(2);
		list.addToFront(1);
		
		answer += "Basic List\n";
		ArrayList<Integer> reverse = list.getReverseArrayList();
		for(Integer i : list) {
			
			answer += i + "\n";
		}
		
		answer += "\n\nReversed Basic List\n";
		for(Integer i : reverse) {
			
			answer += i + "\n";
		}
		
		SortedLinkedList<String> listS = new SortedLinkedList(String.CASE_INSENSITIVE_ORDER);
		
		listS.add("Green").add("Purple").add("Yellow").add("Red");
		
		ArrayList<String> reversedSorted = listS.getReverseArrayList();
		answer += "\nSorted List\n";
		for(String i : listS) {
			
			answer += i + "\n";
		}
		
		answer += "\nReverse Sorted List\n";
		for(String i : reversedSorted) {
			
			answer += i + "\n";
		}
		
		answer += "\nTest Null List";
		BasicLinkedList<String> nullList = new BasicLinkedList<String>();
		ArrayList<String> empty = nullList.getReverseArrayList();
		for(String i : empty) {
			
			answer += i + "/n";
		}
		assertTrue(TestSupport.isCorrect("StudentTest_reverseArrayList.txt", answer));
	}
}
