import java.io.*;
import java.util.*;

/** SIMPLE integer linked list.
 *
 * LinkedList ADT implements:
 *   - size
 *   - isEmpty
 *   - first 
 *   - last 
 *   - addFirst
 *   - addLast
 *   - removeFirst
 *
 * Nothing fancy - no exception handling.
 */
public class IntList {

	// ------------- linked node class --------------
	/**
	 * Linked list node class.
	 *
	 * Implements:
	 *   - getData
	 *   - getNext
	 *   - setNext
	 * */
	class IntNode {
		public int data;
		public IntNode next;
		/** New node with specified data and no next pointer. */
		public IntNode(int data) {
			this(data,null);
		}
		/** New node with specified data and specified next pointer. */
		public IntNode(int data, IntNode next) {
			this.data = data;
			this.next = next;
		}
		public String toString() { return Integer.toString(this.data); }
		public int getData(){ return data; }
		public IntNode getNext(){ return next; }
		public void setNext(IntNode next){ this.next = next; }
		public boolean equals(int i) { return data==i; }
	}

	// ------------- end linked node class ----------

	private IntNode head;
	private IntNode tail;
	private int size;
	
	/** Make an empty new IntList. */
	public IntList() { 
		head = null;
		tail = null;
	}

	public String toString() throws IxException { 
		StringBuffer result = new StringBuffer();
		IntNode next = head;
		result.append("[  ");
		while(next != null) { 
			result.append(next.toString());
			result.append("  ");
			next = next.getNext();
		}
		result.append("]");
		return result.toString();
	}

	/** Get size of list. */
	public int size() { return size; }

	/** Returns true if empty. */
	public boolean isEmpty() { return size==0; }

	/** Return the first integer in the list. */
	public int first() { return head.getData(); }

	/** Return the last integer in the list. */
	public int last() { return tail.getData(); }

	/** Add an integer at the beginning of the list. */
	public void addFirst(int x) {
		addFirst(new IntNode(x));
	}

	/** Add a node at the beginning of the list. */
	private void addFirst(IntNode newHead) { 
		newHead.setNext(this.head);
		this.head = newHead;
		size++;
	}

	/** Add an integer at the end of the list. */
	public void addLast(int x) { 
		addLast(new IntNode(x));
	}

	/** Add a node at the end of the list. */
	public void addLast(IntNode newLast) {
		this.tail.setNext(newLast);
		this.tail = newLast;
		size++;
	}

	/** Remove the item at index rmi from the list. */
	public void remove(int rmi) throws IxException {
		if(isEmpty()) {
			return;
		} else if(rmi>=size || rmi<0) { 
			throw new IxException();
		} else if(rmi==0) {
			// Zero case: remove first item in list
			IntNode newhead = this.head.getNext();
			this.head = newhead;
			size--;
		} else {
			IntNode thisOne = this.head;
			IntNode lagger = this.head;
			for(int j=0;j<rmi;j++){
				thisOne = thisOne.getNext();
				if(j>=1) {
					lagger = lagger.getNext();
				}
			}
			// Forget thisOne
			IntNode next = thisOne.getNext();
			lagger.setNext(thisOne.getNext());
		}
	}




	/** Remove the item at the specified index. */
/*
	public void remove(int rmi) throws IxException { 
		// What could possibly go wrong?
		// - empty list
		// - size specified is too big/too small
		// - special case (remove front of list) specified
		if(isEmpty()) {
			return;
		} else if(rmi>=size || i<0) { 
			throw new IxException();
		} else {

			// List index counter
			int ix = 0;
			
			if(rmi==0) { 
				// Zero index case
				IntNode newhead = this.head.getNext();
				this.head = newhead;
				size--;

			} else {
				// list index counter goes from 
				// 1 <= ix <= size-1
                for(int ix=0; 








			// c=0 is taken care of above
			int c=1;
			IntNode previous = head;
			c++;
			// Loop until the next node is the one we want to remove
			while(c<i) { 
				// Increment "previous" pointer by 1
				previous = previous.getNext();
				c++;
			}

			// Pointer "previous" now points to previous node in list
			// Get pointer to new next node
			IntNode next = previous.getNext().getNext();
			// Forget about old next
			previous.setNext(next);

			// Update size of list
			size--;

			// Update tail pointer if we just removed last item in list
			if(i==(size-1)) {
				// We just removed last item from list
				// New tail is previous, previous.getNext() is null
				this.tail = previous;
			}
		}
	}
	*/

	/** Remove item at beginning of list. */
	public void removeFirst() { 
		if(isEmpty()) {
			return;
		} else {
			IntNode newhead = this.head.getNext();
			this.head = newhead;
		}
	}

	/** Remove item at end of list. */
	public void removeLast() { 
		if(isEmpty()) {
			return;
		} else {
			remove(size-1);
		}
	}




	public static IntList getIntList() {
		IntList l = new IntList();
		l.addFirst(3);
		l.addFirst(2);
		l.addFirst(1);
		l.addFirst(0);
		return l;
	}
		

	// Main method
	public static void main(String[] args) throws IxException { 

		IntList list;
		
		System.out.println("Start:");
		list = getIntList();
		System.out.println(list);

		System.out.println("Remove at 0 4 times:");
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		System.out.println(list);

		list = getIntList();
		System.out.println("Start:");
		System.out.println(list);
		System.out.println("Removing index 1...");
		list.remove(1);
		System.out.println(list);

		list = getIntList();
		System.out.println("Start:");
		System.out.println(list);
		System.out.println("Removing index 2...");
		list.remove(2);
		System.out.println(list);

		list = getIntList();
		System.out.println("Start:");
		System.out.println(list);
		System.out.println("Removing index 3...");
		list.remove(3);
		System.out.println(list);

		list = getIntList();
		System.out.println("Start:");
		System.out.println(list);
		System.out.println("Adding stuff");
		list.addFirst(105);
		list.addFirst(104);
		list.addFirst(103);
		list.addFirst(102);
		list.addFirst(101);
		list.addFirst(100);
		System.out.println(list);

		// should not throw a null pointer exception.
		for(int i=0; i<1000; i++) { 
			try {
				list.remove(0);
			} catch(IxException e) { 
				System.out.println("list is emptied out");
				break;
			}
		}
		System.out.println("Finished with tests of IntList.");
	}

}


