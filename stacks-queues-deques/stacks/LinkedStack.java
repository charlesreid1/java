class Empty extends ArrayIndexOutOfBoundsException{}

/** 
 * Stack implemented with a linked data structure.
 *
 * Interface:
 *	 - push (add to front)
 *   - pop (remove from front)
 *   - peek (return front)
 *   - size
 *   - isempty
 */
public class LinkedStack<E> {


	public static void main(String[] args) { 
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		System.out.println("New stack:");
		System.out.println(s);
		System.out.println("Is empty? " + s.isEmpty());
 
		try {
			s.push(100);
			s.push(200);
			s.push(300);
			System.out.println("Size = " + s.size());
			System.out.println("Peek = " + s.peek());
			for(int i=0; i<10; i++) { 
				s.push(i*i);
			}
			System.out.println("Is empty? " + s.isEmpty());
			System.out.println("Size before pop = " + s.size());
			System.out.println("Peek = " + s.peek());

			System.out.println("Popping: "+s.pop());
			System.out.println("Size after pop = " + s.size());
		} catch(Empty e) { 
			System.out.println("Handled an unexpected Empty exception!"); 
		}



		try {
			for(int i=0; i<1000; i++) { 
				System.out.printf("Popping %d. %d to go! \n", s.pop(), s.size());
			}
		} catch(Empty e) { 
			System.out.println("Handled an expected Empty exception."); 
			System.out.println("Is empty? " + s.isEmpty());
		}
	}

	
	//////////////////////////////////////


	/** Node class for singly-linked list. */
	private class Node<E> {
		private E data;
		private Node<E> next; 
		/** Empty pointer/orphan node constructor */
		public Node(E data) { 
			this(data,null); 
		}
		/** New list node constructor */
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
		/** String representation of node. */
		public String toString() { return this.data.toString(); }
		/** Accessor methods for next node pointer. */
		public Node<E> getNext() { return this.next; }
		public void setNext(Node<E> next) { this.next = next; }
		/** Return data stored by this node. */
		public E getData() { return this.data; }
	}


	////////////////////////////////

    Node<E> head;
	int size;

	public LinkedStack() {
		this.size = 0;
	}

	/** Return number of elements in the linked stack */
	public int size(){ return this.size; }

	/** Return boolean: is the stack empty? */
	public boolean isEmpty() { return this.size==0; }

	/** Push an item onto the top of the stack.
	 * Add to front of linked list.
	 */
	public void push(E e) { 
		// if empty, head is null, so bottom of stack points to null 
		Node<E> oldhead = head;
		Node<E> newhead = new Node<E>(e,oldhead);
		head = newhead;
		size++;
	}

	/** Pop an item from the top of the stack and return it.
	 * Remove item from front of linked list.
	 */
	public E pop() throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}
		// assert size >= 1
		// get your return value from the top of the stack
		Node<E> chosen = head;
		E ret = chosen.getData();
		// set head pointer to next inn line
		head = chosen.getNext();
		// chosen is fed to the Sarlacc (a.k.a. the Java Garbage Collector)
		size--;
		return ret;
	}	

	/** Return reference to top item without removing it. */
	public E peek() throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}
		Node<E> chosen = head;
		E ret = chosen.getData();
		return ret;
	}


	/** String representation of this thing. 
	 * Technically, this is kind of cheating.
	 */
	public String toString() {
		if(isEmpty()) { 
			return "[ ]";
		} else {
			// assertion: size>=1
			StringBuffer sb = new StringBuffer();
			sb.append("[ ");
			int k=0;
			Node<E> runner = head;
			sb.append(runner);
			k++;
			while(k<size && runner.getNext() != null) {
				runner = runner.getNext();
				k++;
			}
			sb.append("]");
			return sb.toString();
		}
	}

}
