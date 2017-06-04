class Illegal extends IllegalArgumentException {}
class EmptyQueue extends ArrayIndexOutOfBoundsException {}



/**
 * Queue implementation using an expandable circular linked list.
 *
 * Implementation:
 *  - size() - get the number of elements in the queue
 *  - isEmpty() - returns true if queue is empty
 *  - enqueue()/add() - add the element to the back of the queue
 *  - dequeue()/remove() - remove and return the element at the front of the queue
 *  - peek() - return a reference to the first item in the queue, but do not remove it
 *  - rotate() - rotate the queue
 *
 *  Note that we really should just wrap the circular linked list class's methods,
 *  it is a better idea to practice a ground-up implementation and improve as we go.
 *
 */
public class LinkedQueue<E> { 



	public static void main(String[] args) {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		System.out.println(q);

		q.enqueue(100);
		q.enqueue(100);
		q.enqueue(100);
		System.out.println(q);

		for(int i=0; i<10; i++) { 
			q.enqueue(i);
		}
		System.out.println(q);

		q.dequeue();
		q.dequeue();
		q.dequeue();
		System.out.println(q);

		for(int i=0; i<3; i++) { 
			q.dequeue();
		}
		System.out.println(q);

		System.out.println("Rotate:");
		q.rotate();
		System.out.println(q);
		q.rotate();
		System.out.println(q);
		q.rotate();
		System.out.println(q);
		
		System.out.println("Size = " + q.size());

	}


	/////////////////////////////////////////////////


	private class Node<E> {
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

	//////////////////////////////////////////////////


	// This data structure utilizes the idea that queues 
	// will probably be roughly constant in size,
	// and used repeatedly.
	// This avoids constant sizing and resizing, creating and destroying.
	// No clogging the Garbage (disposal) Collector 
	
	Node<E> tail;
	int size;

	public LinkedQueue() {
		size = 0;
	};

	public int size() { return size; }
	public boolean isEmpty() { return (size==0); }

	public String toString() { 
		StringBuffer sb = new StringBuffer();
		if(isEmpty()) {
			return "[ EMPTY QUEUE ]";
		} else {
			sb.append("[ ");
			Node<E> runner = tail.getNext();
			int k = 0;
			while(k<size && runner != null) {
				sb.append(runner);
				sb.append(" ");
				k++;
				runner = runner.getNext();
			}
			sb.append(" ]");
			return sb.toString();
		}
	};

	/** Add item e to the back of the linked list. */
	public void enqueue(E e) { 
		if(isEmpty()) {		

			Node<E> newtail = new Node<E>(e);

			// everybody point at the new node
			tail = newtail;

			// tail will link to itself in a 1 item list
			tail.setNext(tail);

		} else {

			Node<E> newtail = new Node<E>(e, tail.getNext());

			// link the new tail 
			tail.setNext(newtail);

			// advance tail pointer
			tail = tail.getNext();

		}
		size++;
	}

	/** Remove item e from the front of the linked list. */
	public E dequeue() { 

		if(isEmpty()) {
			throw new EmptyQueue();

		} else if(size==1) { 
			E res = tail.getData();
			tail = null;
			size--;
			return res;

		} else {

			// length of list is at least 2 items.
			// if length = 2, we will end up linking tail to itself, which is fine.
			Node<E> rm = tail.getNext();
			Node<E> newhead = rm.getNext();

			tail.setNext(newhead);
			size--;
			return rm.getData();
		}
	}


	/** Rotate the deque by skipping the next item in line and putting it in back. */
	public void rotate() { 
		if(isEmpty()) { 
			throw new EmptyQueue();
		}

		// The whole point of using a circular linked list is to avoid creating 
		// a new node to do this kind of operation.
		// So don't call enqueue(dequeue()) !!!
		// 
		// Simply advance the tail pointer forward by one.
		tail = tail.getNext();
	}


}





