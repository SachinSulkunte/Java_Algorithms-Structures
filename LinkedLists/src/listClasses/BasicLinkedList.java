package listClasses;
import java.util.Iterator;
import java.util.ArrayList;

public class BasicLinkedList<T> implements Iterable<T> {

	class Node {
		
		protected T data;
		protected Node next;
		
		public Node(T data) {
			
			this.data = data;
			next = null;
		}
	}
	
	protected Node head, tail;	//defines beginning and end of list
	protected int size;	//size of the list
	
	public BasicLinkedList() {
		
		head = null;
		tail = null;
		size = 0;
	}
	
	public int getSize() {
		
		return size;
	}
	
	public BasicLinkedList<T> addToEnd(T data){
		
		if(head == null) {
			
			size--;	//to avoid double counting because both methods add
			addToFront(data);
		}
		else {
			
			Node end = new Node(data);
			tail.next = end;	//changes tail to point to the new node
			tail = end;		//redefines tail
		}
		
		size++;
		return this;
	}
	
	public BasicLinkedList<T> addToFront(T data){
		
		Node first = new Node(data);
		first.next = head;
		head = first;	//redefines head as beginning
		
		if(tail == null) {
			
			tail = first;	//tail is the same as head if only one element
		}
		
		size++;
		return this;
	}
	
	public T getFirst() {
		
		if(head == null) {	//if list is empty
			
			return null;
		}
		
		return head.data;
	}
	
	public T getLast() {
		
		if(head == null) {	//if list is empty
			
			return null;	
		}
		
		return tail.data;
	}
	
	public T retrieveFirstElement() {
		
		if(head == null) {	//if list is empty
			
			return null;	
		}
		
		T data = head.data;	//saves data
		head = head.next;	//redefines head node
		
		size--;
		return data;
	}
	
	public T retrieveLastElement() {
		
		if(head == null) {	//if list is empty
			
			return null;	
		}
		
		if(head.next == null) {	//if only one element
			
			T headData = head.data;
			head = null;
			
			return headData;
		}
		
		Node beforeLast = head;
		while(beforeLast.next.next != null) {
			
			beforeLast = beforeLast.next;
		}
		
		T data = tail.data;
		
		beforeLast.next = null;	//removes pointer to tail
		tail = beforeLast;	//redefines tail position
		
		size--;
		return data;
	}

	public BasicLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator){
		
		Node currentNode = head;
		Node prev = null;
		
		while(currentNode != null) {
			
			Node after = currentNode.next;
			
			if(comparator.compare(currentNode.data, targetData) == 0) {
				
				if(prev == null) {
					
					head = after;	//removes original head
					size--;
				}
				
				else {
					
					prev.next = currentNode.next;
					size--;
				}
			}
			
			else {
				
				prev = currentNode;
			}
			
			currentNode = currentNode.next;	//moves pointer to next node
		}
		
		return this;
	}
	
	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {

			private Node current = head;
			
			@Override
			public boolean hasNext() {
				
				return current != null;
			}

			@Override
			public T next() {

				Node holder = current;
				current = current.next;	//moves pointer to next node
				return holder.data;
			}
			
			@Override
			public void remove() {
				
				throw new UnsupportedOperationException();
			}
			
		};
	}
	
	public ArrayList<T> getReverseArrayList() {
		
		ArrayList<T> arrList = new ArrayList<T>();
		
		reverseArray(head, arrList);
		
		return arrList;
	}
	
	public BasicLinkedList<T> getReverseList(){
		
		BasicLinkedList<T> newList = new BasicLinkedList<T>();

		reverse(head, newList);
		
		return newList;
	}
	
	private BasicLinkedList<T> reverse(Node current, BasicLinkedList<T> list) { //auxiliary function
		
		if(current != null) {
			
			Node following = current;
			current = current.next;
			reverse(current, list);	//recursive call for next node
			return list.addToEnd(following.data);	//puts nodes in reverse order
		}
		
		return list;
	}
	
	private ArrayList<T> reverseArray(Node current, ArrayList<T> list){
		
		if(current != null) {
			
			Node following = current;
			current = current.next;
			reverseArray(current, list);	//recursive call
			list.add(following.data);	//adds data to array list
			return list;
		}
		
		return list;
	}
}
