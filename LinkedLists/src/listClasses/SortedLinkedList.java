package listClasses;

import java.util.Comparator;

public class SortedLinkedList<T> extends BasicLinkedList<T> implements Iterable<T>{
	
	private Comparator<T> c;
	
	public SortedLinkedList(java.util.Comparator<T> comparator){
		
		super();
		c = comparator;
	}
	
	public SortedLinkedList<T> add(T element){
		
		Node current = head;
		Node prev = null;
		
		if(head == null) {
			
			size++;
			current = new Node(element);
			current.next = null;
			head = current;
			tail = current;
			return this;
		}
		else {
			
			while(current != null) {
			
				Node newNode = new Node(element);

				if(c.compare(element, current.data) <= 0) {	//belongs before the element
										
					if(current == head) {
						
						newNode.next = head;
						head = newNode;
						size++;
						return this;
					}
					else {
						
						prev.next = newNode;
						newNode.next = current;
						size++;
						return this;
					}
				}
				else if(c.compare(element, current.data) > 0){	//if it belongs at some point after
					
					prev = current;
					current = current.next;
					
					if(current == null) {	//if reached end of list
						
						size++;
						prev.next = newNode;
						tail = newNode;
						return this;
					}
				}
			}	
		}
		return this;
	}
	
	
	public SortedLinkedList<T> remove(T targetData){
		
		super.remove(targetData, c);
		
		return this;
	}
	
	@Override
	public BasicLinkedList<T> addToFront(T data){
		
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	@Override
	public BasicLinkedList<T> addToEnd(T data){
		
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

}