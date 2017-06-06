class Illegal extends IllegalArgumentException {}
class Empty extends ArrayIndexOutOfBoundsException {}

/** Templated singly-linked list class. 
 * 
 * LinkedList ADT:
 *
 * */
public class TLinkedList<E> {

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



	//////////////////////////////////

	private Node<E> head; // pointer to head of list (first element)
	private Node<E> tail; // pointer to tail of list (last element)
	private int size; // size of list (number of elements)

	public TLinkedList() { 
		head=null; tail=null;
		size = 0;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if(isEmpty()) {
			sb.append("]");
			return sb.toString();
		} 
		Node<E> runner = head;
		sb.append( runner.toString() );
		while(runner.getNext()!=null) {
			// Order is important here!
			runner = runner.getNext();
			sb.append(", ");
			sb.append( runner.toString() );
		}
		sb.append("]");
		return sb.toString();
	}

	/** Size of this thing */
	public int size() { return size; }
	
	/** Returns true if empty. */
	public boolean isEmpty() { return this.size==0; }

	/** Returns reference to first item in the list. */
	public E first() { return head.getData(); }

	/** Returns reference to last item in the list. */
	public E last()  { return tail.getData(); }


	/** Reverse the entire linked list in O(n) time. */
	public void reverse() { 
		if(this.size==0 || this.size==1) {
			return;
		}
		Node<E> runner = this.head;
		Node<E> prev = null;
		Node<E> next_runner = this.head.getNext();

		while(next_runner!=null){
			runner.setNext(prev);
			prev = runner;
			runner = next_runner;
			next_runner = next_runner.getNext();
		}
		this.head = runner;
	}


	/** Add item to front of list. */
	public void addFirst(E e)  { 
		Node<E> newhead = new Node<E>(e);
		Node<E> oldhead = this.head;
		if(isEmpty()) { 
			this.head = newhead;
			this.tail = newhead;
		} else {
			// Set inserted node pointer to old head
			newhead.setNext(oldhead);
			// Set head pointer to inserted node
			this.head = newhead; 
		}
		size++;
	}

	/** Add item to front of list. */
	public void addLast(E e) { 
		Node<E> newtail = new Node<E>(e);
		if(isEmpty()) { 
			this.head = newtail;
			this.tail = newtail;
		} else {
			// Set tail pointer to appended node
			this.tail.setNext(newtail);
			this.tail = newtail;
		}
		size++;
	}

	public E removeFirst() throws Empty {
		if(isEmpty()) { 
			throw new Empty();
		}
		E value = this.head.getData();

		// update head to point to head.next
		Node<E> newhead = this.head.getNext();
		this.head = newhead;
		size--;

		return value;
	}


	//////////////////////////////////


	/** Construct a static dummy list. */
	public static TLinkedList<Integer> getTLinkedList() {
		TLinkedList<Integer> l = new TLinkedList<Integer>();
		l.addFirst(3);
		l.addFirst(2);
		l.addFirst(1);
		l.addFirst(0);
		return l;
	}

	/** Main method. */
	public static void main(String[] args) throws Illegal, Empty { 

		TLinkedList<Integer> list;
		
		System.out.println("Start:");
		list = getTLinkedList();
		System.out.println(list);
		System.out.println("Size: "+list.size());

		System.out.println("Remove first 4 times:");
		list.removeFirst();
		list.removeFirst();
		list.removeFirst();
		list.removeFirst();
		System.out.println(list);
		System.out.println("Size: "+list.size());

		list = getTLinkedList();
		System.out.println("Start:");
		System.out.println(list);

		System.out.println("Adding first");
		list.addFirst(105);
		list.addFirst(104);
		list.addFirst(103);
		list.addFirst(102);
		list.addFirst(101);
		list.addFirst(100);
		System.out.println(list);
		System.out.println("Size: "+list.size());

		System.out.println("Adding last");
		list.addLast(997);
		list.addLast(998);
		list.addLast(999);
		list.addLast(1000);
		System.out.println(list);

		System.out.println("Reversing");
		list.reverse();
		System.out.println(list);

		for(int i=0; i<1000; i++) { 
			try {
				list.removeFirst();
			} catch(Illegal e) { 
				System.out.println("Illegal index");
				break;
			} catch(Empty e) { 
				System.out.println("Empty list");
				break;
			}

		}
		System.out.println("Finished with tests of TLinkedList.");
	}
}
