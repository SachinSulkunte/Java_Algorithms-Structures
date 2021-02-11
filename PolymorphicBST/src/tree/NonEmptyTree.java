package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */

	private K keyT;
	private V data;
	private Tree<K, V> leftT, rightT;

	/**
	 * Only constructor we need.
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {

		keyT = key;
		data = value;
		leftT = left;
		rightT = right;
	}

	public V search(K key) {

		int compare = key.compareTo(keyT);
		if(compare == 0) {
			
			return this.data;	//return data associated with the key
		}
		else if(compare > 0) {
			
			return rightT.search(key);	//recursively check the right subtree
		}
		else {
			
			return leftT.search(key);	//recursively check the left subtree
		}
		
	}

	public NonEmptyTree<K, V> insert(K key, V value) {

		if(this.keyT.compareTo(key) == 0) {
			
			this.data = value;	//overwrites key
	        return this;
	    }
		else if(key.compareTo(this.keyT) < 0) { 
	            
	        leftT = leftT.insert(key, value);	//recursively checks smaller subtree
	    }
	    else if(key.compareTo(this.keyT) > 0) {
	        	  
	    	rightT = rightT.insert(key, value);
	    }
	    
		return this;
	}

	public Tree<K, V> delete(K key) {
		
		int compare = key.compareTo(keyT);
		
		if (compare > 0) {
			
			rightT = rightT.delete(key);	//keep searching further in right subtree for key
			return this;
		} 
		else if (compare < 0) {
			
			leftT = leftT.delete(key);	//recursively search for key in left subtree
			return this;
		} 
		if(compare == 0) {	//once key is found
			/*
			 * Checks first the smallest value of the right subtree as default replacement
			 */
			try { 
				
				data = rightT.search(rightT.min());	//sets data of current to value of min in subtree
				keyT = rightT.min();	//sets current to the lowest value in the right subtree
				rightT = rightT.delete(rightT.min());	//removes occurrence of min value from right subtree
			} catch (TreeIsEmptyException s) {
				
				try { //to find largest value in left subtree if right subtree is empty
					
					data = leftT.search(leftT.max());	//sets current object data to data of max key of left subtree
					keyT = leftT.max();					//sets current object value to left subtree max
					leftT = leftT.delete(leftT.max());	//removes occurrence of max value from left subtree
				} catch (TreeIsEmptyException e) {
			
					return EmptyTree.getInstance(); //handles case where right and left subtrees are empty trees
				}
			}
		}
		return this;
	}

	public K max() {

		try {
			
			return rightT.max();	//keep trying to move right because larger values to the right
		} catch (TreeIsEmptyException e) {
			
			return keyT;	//if empty then return the current tree key
		}
	}

	public K min() {
		
		try {
			
			return leftT.min();	//keep trying to move left because smaller values are to the left
		} catch (TreeIsEmptyException e) {
			
			return keyT;	//if empty then return the current tree key
		}
	}

	public int size() {

		int size = leftT.size() + rightT.size() + 1; // count 1 for node called, recursively call on left and right
														// subtree
		return size;
	}

	public void addKeysToCollection(Collection<K> c) {
		
		c.add(keyT);	//adds current tree's key
		leftT.addKeysToCollection(c);	//adds left subtree values recursively
		rightT.addKeysToCollection(c);	//adds right subtree values recursively
	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
				
		int compareFromKey = this.keyT.compareTo(fromKey);
		int compareToKey = this.keyT.compareTo(toKey);
		
		Tree<K, V> newSubTree = EmptyTree.getInstance();
		
		if(compareToKey <= 0 && compareFromKey >= 0) {	//greater than or equal to min and smaller than or equal to max
			
			newSubTree.insert(this.keyT, this.data);
			
			if(compareToKey < 0) {	//check further right 
				
				newSubTree = rightT.subTree(fromKey, toKey);
			}
			else if(compareFromKey > 0) {
				
				newSubTree = leftT.subTree(fromKey, toKey);
			}

		}
		else if(compareToKey > 0) {
			
			return leftT.subTree(fromKey, toKey);	//check further left for smaller keys
		}
		else if(compareFromKey < 0) {
			
			return rightT.subTree(fromKey, toKey);	//check further right for larger keys
		}
		
		return this;
	}

	public int height() {
		
		if(leftT.height() > rightT.height()) {
			
			return leftT.height() + 1;	//count original node as well
		}
		else {	//if they're equal it doesn't matter
			
			return rightT.height() + 1;	
		}
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		
		leftT.inorderTraversal(p);	//left subtree then root then right subtree
		p.performTask(this.keyT, this.data);
		rightT.inorderTraversal(p);
	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		
		rightT.rightRootLeftTraversal(p);
		p.performTask(this.keyT, this.data);
		leftT.rightRootLeftTraversal(p);
	}
}