class Empty extends ArrayIndexOutOfBoundsException {}

/** 
 * Deque implemented with a linked list.
 *
 * Interface:
 *	 - enqueue front/back
 *   - dequeue front/back
 *   - peek front/back
 *   - size
 *   - isempty
 *   - rotate
 */
public class LinkedDeque<E> {

	public static void main(String[] args) { 
		LinkedDeque<Integer> d = new LinkedDeque<Integer>();
		System.out.println("New deque:");
		System.out.println(d);

		System.out.println("enqueue from front:");
		d.enqueueFront(100);
		d.enqueueFront(200);
		d.enqueueFront(300);
		d.enqueueFront(400);
		d.enqueueFront(500);
		System.out.println(d);
		System.out.println("done.");

		System.out.println("enqueue from back:");
		d.enqueueBack(25);
		d.enqueueBack(50);
		d.enqueueBack(75);
		System.out.println(d);
		System.out.println("done.");

		System.out.println("size:");
		System.out.println(d.size());

		try {
			System.out.println("dequeue from front");
			d.dequeueFront();
			d.dequeueFront();
			System.out.println(d);
			System.out.println("done.");

			System.out.println("dequeue from back");
			d.dequeueBack();
			d.dequeueBack();
			System.out.println(d);
			System.out.println("done.");
		} catch(Empty e) { 
			System.out.println("Handled an unexpected Empty exception.");
		}

		try {
			for(int i=0; i<1000; i++) { 
				System.out.printf("Popping %d. %d to go! \n", d.dequeueFront(), d.size());
			}
		} catch(Empty e) { 
			System.out.println("Handled an expected Empty exception."); 
		}

	}


	////////////////////////////////////////////


	/** Node class for doubly-linked list. */
	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev; 
		/** Empty pointer/orphan node constructor */
		public Node(E data) { 
			this(data,null,null); 
		}
		/** New list node constructor */
		public Node(E data, Node<E> next, Node<E> prev) { 
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		/** String representation of node. */
		public String toString() { return this.data.toString(); }
		/** Accessor methods for next/previous node pointers/links. */
		public Node<E> getNext() { return this.next; }
		public Node<E> getPrev() { return this.prev; }
		public void setNext(Node<E> next) { this.next = next; }
		public void setPrev(Node<E> prev) { this.prev = prev; }
		/** Return data stored by this node. */
		public E getData() { return this.data; }
	}


	////////////////////////////////////////////


	Node<E> trailer;
	int size;

	/** Constructor makes the trailer (dummy node at beginning and end of list. */
	public LinkedDeque() {
		// Empty node that points to nothing
		trailer = new Node<E>(null);
	}

	/** Return size */
	public int size() { return this.size; }

	/** Return boolean - true if empty */
	public boolean isEmpty() { return (this.size==0); }

	/** String representation of the deque */
	public String toString() { 
		if(isEmpty()) { 
			return "[ ]";
		}
		// size must be at least 1
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");

		// Running through the link list is always awkward.
		// Get this nailed down so you don't get nailed.
		int k = 0;
		Node<E> runner = trailer;
		while(k<size && runner.getNext() != null) {
			runner = runner.getNext();
			sb.append(runner);
			sb.append(" ");
			k++;
		}
		sb.append("]");
		return sb.toString();
	}

	/** Link first to second. */
	private void link(Node<E> first, Node<E> second) {
		first.setNext(second);
		second.setPrev(first);
	}

	/** Add an element to an empty queue */
	private void enqueueEmpty(E e) {
		// first case is unusual:
		// link trailer, front and back, to new node
		Node<E> newnode = new Node<E>(e);
		link(trailer,newnode);
		link(newnode,trailer);
	}

	/** Add to the queue by pushing from the front. */
	public void enqueueFront(E e) { 
		if(isEmpty()) {
			enqueueEmpty(e);
		} else {
			// insert new node at the front,
			// between the trailer and the trailer's next item
			Node<E> newhead = new Node<E>(e);
			Node<E> oldhead = trailer.getNext();
			link(trailer,newhead);
			link(newhead,oldhead);
		}
		size++;
	}

	/** Add to the queue by adding to the back. */
	public void enqueueBack(E e) {
		if(isEmpty()) { 
			enqueueEmpty(e);
		} else {
			// insert new node at the back,
			// between trailer and trailer's old tail
			Node<E> newtail = new Node<E>(e);
			Node<E> oldtail = trailer.getPrev();
			link(oldtail,newtail);
			link(newtail,trailer);
		}
		size++;
	}

	/** Remove from the queue by popping in the front */
	public E dequeueFront() throws Empty { 
		if(isEmpty()) {
			throw new Empty();
		} else {

			Node<E> doomed = trailer.getNext();
			E ret = doomed.getData();

			// unlink trailer and front
			if(size==1) { 
				trailer.setNext(null);
				trailer.setPrev(null);
			} else { 
				Node<E> nexthead = doomed.getNext();
				link(trailer,nexthead);
			}

			size--;
			return ret;
		}
	}

	/** Remove from the queue by accessing the back */
	public E dequeueBack() throws Empty { 
		if(isEmpty()) {
			throw new Empty();
		} else {

			Node<E> doomed = trailer.getPrev();
			E ret = doomed.getData();

			// unlink trailer and back
			if(size==1) { 
				trailer.setNext(null);
				trailer.setPrev(null);
			} else { 
				Node<E> newtail = doomed.getPrev();
				link(newtail,trailer);
			}

			size--;
			return ret;
		}
	}


}

