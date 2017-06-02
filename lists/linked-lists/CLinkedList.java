class Illegal extends IllegalArgumentException {}
class Empty extends ArrayIndexOutOfBoundsException {}

/** Circular linked list implementation. 
 *
 * Round robin scheduling is a process that could use a traditional 
 * linked list, but it is more efficient to perform the actions
 * with a circular linked list.
 * process p = L.removeFirst()
 * Give a time slice to processs p
 * L.addLast(p)
 *
 * GIVEN an application where we are doing this kind of action,
 * we can modify our data structure to be more efficient:
 * waiting process has a "waiting pool" that consists of a head, tail,
 * and list of linked nodes, 
 * but the last node points to the first node.
 *
 * Additional method: rotate()
 *
 * Further improvements can be gained by only pointing to the tail of the list,
 * not the head and tail separately.
 * To get head: tail.getNext()
 *
 *
 * Circular linked list data type implements:
 *		- isEmpty
 *		- size
 *		- toString 
 *		- first
 *		- last
 *		- addfirst
 *		- addlast
 *		- removefirst
 *		- rotate
 *
 * Additional:
 *		- add
 *		- remove
 *		- remove(i)
 *		- removeFirst
 *		- removeLast
 *
 *
 * */
public class CLinkedList<E> {

	//////////////////////////////////

	/** Templated linked list node class. 
	 *
	 * Implements:
	 *   - getData
	 *   - getNext
	 *   - setNext
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next;

		/** Constructor with specified value that points to nobody. */
		public Node(E data) {
			this(data,null);
		}

		/** Constructor with specified value that points to target. */
		public Node(E data, Node<E> next) { 
			this.data = data;
			this.next = next;
		}
		public E getData() { return this.data; }
		public Node<E> getNext() { return this.next; }
		public void setNext(Node<E> node) { this.next = node; }
		public String toString() { return this.data.toString(); }
	}



	// Round-Robin Scheduling
	// No head stored - because you can move forward, but not backward
	private Node<E> tail = null;
	private int size = 0;

	public CLinkedList() { }

	// Access methods:
	public int size() { return size; } 
	public boolean isEmpty() { return size == 0; }

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if(isEmpty()) {
			sb.append("]");
			return sb.toString();
		} else {
			Node<E> runner = tail.getNext();
			sb.append(runner);
			for(int i=1; i<size; i++) {
				runner = runner.getNext();
				sb.append(", ");
				sb.append(runner);
			}
			sb.append(" ]");
			return sb.toString();
		}
	}

	// Update methods:
	public void rotate() {
		if(tail != null) {
			tail = tail.getNext();
		}
	}

	public void addFirst(E e) { 
		if( size==0 ) {
			// Circular (one-element) list
			tail = new Node<E>(e);
			tail.setNext(tail);
		} else {
			Node<E> newest = new Node<E>(e, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}

	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}

	public E removeFirst() { 
		if(isEmpty()) {
			return null;
		}

		// Remove head
		Node<E> head = tail.getNext();
		size--;
		if(head.equals(tail)) {
			E value = tail.getData();

			// One element list - just set to null
			tail = null;
			return value;

		} else {
			tail.setNext(head.getNext());

			// Return head's value
			return head.getData();
		}
	}

	public E first() {
		if(isEmpty()) { 
			return null;
		}
		// head is tail.getNext()
		return tail.getNext().getData();
	}

	public E last() {
		if(isEmpty()) { 
			return null;
		}
		return tail.getData();
	}



	/** Main method. */
	public static void main(String[] args) throws Illegal, Empty { 

		CLinkedList<Integer> c = new CLinkedList<Integer>();

		for(int i=4; i<10; i++) { 
			c.addFirst(i);
		}
		System.out.println(c);
		System.out.println("------");

		for(int i=100; i<106; i++) { 
			c.addLast(i);
		}
		System.out.println(c);
		System.out.println("------");


	}
}

