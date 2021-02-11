package tests;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTests extends TestCase {
	
	/*
	 * Test creation of polymorphic bst, size, height, clear, and delete methods
	 */
	@Test
	public void testCreation() {
		
		String answer = "";
		PolymorphicBST<Integer,Integer> ptree = new PolymorphicBST<Integer,Integer>();

		ptree.put(20, 1);
		ptree.put(10, 5);
		ptree.put(30, 4);
		ptree.put(28, 15);
		ptree.put(8, 2);
		ptree.put(32, 13);
		
		answer += "Tree Size: " + ptree.size();
		answer += "Tree Height: " + ptree.height();
		
		ptree.remove(30);
		ptree.remove(32);
		
		answer += "\n\nAfter Removal";
		answer += "Tree Size: " + ptree.size();
		answer += "Tree Height: " + ptree.height();
		
		answer += "\nClearing the tree";
		ptree.clear();
		answer += "Updated size: " + ptree.size();
		
		assertTrue(TestSupport.isCorrect("StudentTest_testCreation.txt", answer));
	}
	
	/*
	 * Test traversals of bst and min/max functions
	 */
	@Test
	public void testTraversal() {
		
		String answer = "";
		PolymorphicBST<Integer,Integer> ptree = new PolymorphicBST<Integer,Integer>();

		ptree.put(20, 1);
		ptree.put(10, 2);
		ptree.put(25, 6);
		ptree.put(8, 4);
		ptree.put(15, 7);
		ptree.put(22, 20);
		ptree.put(30, 24);
		ptree.put(27, 35);
		ptree.put(23,  5);
		ptree.put(31, 12);
		ptree.put(4, 21);
		
		answer += "Tree Size: " + ptree.size();
		answer += "\nTree Height: " + ptree.height();
		
		PlaceKeysValuesInArrayLists<Integer, Integer> task = new PlaceKeysValuesInArrayLists<Integer, Integer>();

		ptree.inorderTraversal(task);
		answer += "\nInorder: " + task.getKeys();
		
		PlaceKeysValuesInArrayLists<Integer, Integer> task2 = new PlaceKeysValuesInArrayLists<Integer, Integer>();

		ptree.rightRootLeftTraversal(task2);
		answer += "\nRRLTraversal: " + task2.getKeys();
		
		answer += "\nMin key: " + ptree.getMin();
		answer += "\nMax key: " + ptree.getMax();
		
		assertTrue(TestSupport.isCorrect("StudentTest_testTraverse.txt", answer));
	}
	
	@Test
	public void testSearch() {
		
		String answer = "";
		PolymorphicBST<Integer,Integer> ptree = new PolymorphicBST<Integer,Integer>();

		ptree.put(20, 1);
		ptree.put(10, 2);
		ptree.put(25, 6);
		ptree.put(8, 4);
		
		answer += "Searching for Key: 2 -- Value is " + ptree.get(2);
		answer += "\nSearching for Key: 25 -- Value is " + ptree.get(25); 
		
		assertTrue(TestSupport.isCorrect("StudentTest_testSearch.txt", answer));
	}
	
	@Test
	public void testSubTree() {
		
		String answer = "";
		PolymorphicBST<Integer,Integer> ptree = new PolymorphicBST<Integer,Integer>();

		ptree.put(20, 1);
		ptree.put(10, 2);
		ptree.put(25, 6);
		ptree.put(8, 4);
		ptree.put(15, 7);
		ptree.put(22, 20);
		ptree.put(30, 24);
		ptree.put(27, 35);
		ptree.put(23,  5);
		ptree.put(31, 12);
		ptree.put(4, 21);
				
		PlaceKeysValuesInArrayLists<Integer, Integer> task = new PlaceKeysValuesInArrayLists<Integer, Integer>();
		PlaceKeysValuesInArrayLists<Integer, Integer> task2 = new PlaceKeysValuesInArrayLists<Integer, Integer>();

		PolymorphicBST<Integer,Integer> subtree = new PolymorphicBST<Integer,Integer>();
		
		subtree = ptree.subMap(0, 15);
		subtree.inorderTraversal(task);
		answer += "Subtree size: " + subtree.size();
		answer += "\nSubtree from Keys 0 - 15: " + task.getKeys();
		
		ptree.inorderTraversal(task2);
		answer += "\nOriginal tree: " + task2.getKeys();
		
		assertTrue(TestSupport.isCorrect("StudentTest_testSubTrees.txt", answer));
	}
	
	@Test
	public void testKeySet() {
		
		String answer = "";
		PolymorphicBST<Integer,Integer> ptree = new PolymorphicBST<Integer,Integer>();

		ptree.put(20, 1);
		ptree.put(10, 2);
		ptree.put(25, 6);
		ptree.put(8, 4);
		ptree.put(15, 7);
		ptree.put(22, 20);
		
		answer += ptree.keySet();
		
		assertTrue(TestSupport.isCorrect("StudentTest_testKeySet.txt", answer));
	}
}