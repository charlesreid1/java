class Illegal extends IllegalArgumentException {}
class Empty extends ArrayIndexOutOfBoundsException {}

/** Templated linked list class. 
 * 
 * LinkedList ADT:
 *
 * */
public class TList<E> {

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
	//
	// basic:
	// - size
	// - isEmpty
	// - first
	// - last
	// - addfirst
	// - addlast
	// - removefirst
	//
	// additional:
	// - add
	// - remove
	// - remove(i)
	// - removeFirst
	// - removeLast

	private Node<E> head; // pointer to head of list (first element)
	private Node<E> tail; // pointer to tail of list (last element)
	private int size; // size of list (number of elements)

	public TList() { 
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
		sb.append( runner.getData().toString() );
		while(runner.getNext()!=null) {
			sb.append(", ");
			sb.append( runner.getData().toString() );
			runner = runner.getNext();
		}
		sb.append("]");
		return sb.toString();
	}

	/** Size of this thang */
	public int size() { return size; }
	
	/** Returns true if empty. */
	public boolean isEmpty() { return this.size==0; }

	/** Returns reference to first item in the list. */
	public E first() { return head.getData(); }

	/** Returns reference to last item in the list. */
	public E last()  { return tail.getData(); }

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


	//////////////////////////////////


	/** Construct a static dummy list. */
	public static TList<Integer> getIntList() {
		TList<Integer> l = new TList<Integer>();
		l.addFirst(3);
		l.addFirst(2);
		l.addFirst(1);
		l.addFirst(0);
		return l;
	}

	/** Main method. */
	public static void main(String[] args) throws Illegal, Empty { 

		TList<Integer> list;
		
		System.out.println("Start:");
		list = getIntList();
		System.out.println(list);
		System.out.println("Size: "+list.size());

		/*
		System.out.println("Remove at 0 4 times:");
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		System.out.println(list);
		System.out.println("Size: "+list.size());

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

		list = getIntList();
		System.out.println("Start:");
		System.out.println(list);
		System.out.println("Removing index 3...");
		list.remove(3);
		System.out.println(list);

		list = getIntList();
		System.out.println("Start:");
		System.out.println(list);

		*/

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
		System.out.println(list);

		/*
		// should not throw a null pointer exception.
		for(int i=0; i<1000; i++) { 
			try {
				list.remove(0);
			} catch(Illegal e) { 
				System.out.println("Illegal index");
				break;
			} catch(Empty e) { 
				System.out.println("Empty list");
				break;
			}

		}
		System.out.println("Finished with tests of IntList.");
		*/
	}
}