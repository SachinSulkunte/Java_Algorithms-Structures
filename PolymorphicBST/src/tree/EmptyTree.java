package tree;

import java.util.Collection;

 public class EmptyTree<K extends Comparable<K>,V> implements Tree<K,V> {
	/**
	 * This static field references the one and only instance of this class.
	 */
	private static EmptyTree SINGLETON = new EmptyTree();


	public static  <K extends Comparable<K>, V> EmptyTree<K,V> getInstance() {
		return SINGLETON;
	}

	/**
	 * Constructor is private to enforce it being a singleton
	 *  
	 */
	private EmptyTree() {
		
	}
	
	public V search(K key) { //node is empty
		
		return null;
	}
	
	public NonEmptyTree<K, V> insert(K key, V value) {
		
		return new NonEmptyTree<K,V>(key, value, SINGLETON, SINGLETON);	//creates new instance of non-empty tree
	}

	public Tree<K, V> delete(K key) {
		
		return SINGLETON;	//returns empty tree
	}
	
	public K max() throws TreeIsEmptyException {
		
		throw new TreeIsEmptyException();
	}

	public K min() throws TreeIsEmptyException {

		throw new TreeIsEmptyException();
	}

	public int size() {

		return 0;	//empty
	}

	public void addKeysToCollection(Collection<K> c) {
		
		return;	//empty so no keys to be added
	}

	public Tree<K,V> subTree(K fromKey, K toKey) {

		return SINGLETON;	//returns empty tree instance
	}
	
	public int height() {

		return 0; //height of empty tree is undefined
	}
	
	public void inorderTraversal(TraversalTask<K,V> p) {
		
		return;
	}
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {

		return;
	}
}
