class Empty extends Exception{};
class Illegal extends IllegalArgumentException{};

public class DLinkedList<E> {


	public static void main(String[] args) throws Empty { 


		DLinkedList<String> d = new DLinkedList<String>();
		try {
			d.get(5);
		} catch(Empty e) { 
			System.out.println("Caught Empty exception correctly.");
		}

		d.addLast("JFK");
		d.addLast("PVD");
		d.addLast("SFO");
		d.addFirst("Fortyseven");
		d.addFirst("Thirtysix");
		d.addFirst("Eighteen");
		System.out.println(d);

		System.out.printf("Peeking first %s...\n", d.first());
		System.out.printf("Peeking last %s...\n", d.last());

		System.out.println("Before empty: " + d);
		while(!d.isEmpty()) { 
			System.out.printf("Removing %s... \n", d.removeFirst());
		}
		System.out.println("After empty: " + d);

		d.addFirst("SLC");
		d.addLast("PDX");
		d.addLast("SEA");
		d.addLast("LAX");
		System.out.println(d);

	

	}



	//////////////////////////////////



	private DNode<E> alpha = null;
	private DNode<E> omega = null;
	private int size;

	public DLinkedList() { 
		alpha = new DNode<E>(null);
		omega = new DNode<E>(null);
		
		// In the beginning, there was darkness...
		link(alpha,omega);

		size = 0;
	}

	// Linked list adt: 
	// - size
	// - isEmpty
	// - first
	// - last 
	// - addFirst
	// - addLast
	// - removeFirst
	// - removeLast
	// - get(i)
	public int size() { return size; } 
	public boolean isEmpty() { return size==0; }

	public String toString() { 
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if(isEmpty()) {
			sb.append("]");
			return sb.toString();
		}

		int c = 1;

		DNode<E> iter = alpha.getNext();
		sb.append(iter.getData().toString());
		c++;
		while(c<=size) {
			iter = iter.getNext();
			sb.append(", ");
			sb.append(iter.getData().toString());
			c++;
		}
		sb.append(" ]");
		return sb.toString();
	}

	public E first() throws Empty {
		if(isEmpty()) {
			throw new Empty();
		}
		return alpha.getNext().getData();
	};

	public E last() throws Empty {;
		if(isEmpty()) {
			throw new Empty();
		}
		return omega.getPrev().getData();
	}

	public E get(int ix) throws Empty {
		if(isEmpty()) {
			throw new Empty();
		}

		int n = size();

		DNode<E> runner;
		int irunner, cmax;
		if(ix > n/2) {

			// ix is in second half: start from the back
			cmax = (n-ix-1); // plus-one shift to index, or minus 1 shift to n
			irunner = 0;
			runner = omega;
			while(irunner < cmax) { 
				runner = runner.getPrev();
				irunner++;
			}

		} else {
			// ix is in first half: start from zero
			cmax = n/2;
			irunner = 0;
			runner = alpha;
			while(irunner < cmax) {
				runner = runner.getNext();
				irunner++;
			}

		}
		return runner.getData();
	}

	public void link(DNode<E> a, DNode<E> b) {
		a.setNext(b);
		b.setPrev(a);
	}

	public void addFirst(E e) { 
		DNode<E> oldnext = alpha.getNext();
		DNode<E> addme = new DNode<E>(e);

		link(alpha,addme);
		link(addme,oldnext);

		size++;
	}

	public void addLast(E e) { 
		DNode<E> oldlast = omega.getPrev();
		DNode<E> lastme = new DNode<E>(e);

		link(lastme, omega);
		link(oldlast, lastme);
		
		size++;
	}

	public E removeFirst() throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}
		E ret = alpha.getNext().getData();
		DNode<E> newhead = alpha.getNext().getNext();
		link(alpha, newhead);
		size--;
		return ret;
	}


	public E removeLast() throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}
		E ret = omega.getPrev().getData();
		DNode<E> newtail = omega.getPrev().getPrev(); 
		link(newtail, omega);
		size--;
		return ret;
	}









	//////////////////////////////////

	/** Templated linked list node class. 
	 *
	 * Implements:
	 *   - getData
	 *   - getNext/getPrev
	 *   - setNext/setPrev
	 */
	private static class DNode<E> {
		private E data;
		private DNode<E> next;
		private DNode<E> prev;

		/** Constructor with specified value that points to nobody. */
		public DNode(E data) {
			this(data,null);
		}

		/** Constructor with specified value that points to target. */
		public DNode(E data, DNode<E> next) { 
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		public E getData() { return this.data; }

		public DNode<E> getNext() { return this.next; }
		public DNode<E> getPrev() { return this.prev; }

		public void setNext(DNode<E> node) { this.next = node; }
		public void setPrev(DNode<E> node) { this.prev = node; }

		public String toString() { return this.data.toString(); }
	}




}



