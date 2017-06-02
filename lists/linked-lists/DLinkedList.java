class Empty extends Exception{};
class Illegal extends IllegalArgumentException{};

public class DLinkedList<E> {

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


	//////////////////////////////////


	private DNode<E> header = null;
	private DNode<E> trailer = null;
	private int size;

	public DLinkedList() { }

	// Access methods:
	public int size() { return size; } 
	public boolean isEmpty() { return size==0; }



	///////////////////////////////////////////////////////

	public static void main(String[] args) { 


		DLinkedList<String> d = new DLinkedList<String>();
		/*
		for(int i=0; i<10; i++) {
			d.addLast("JFK");
			d.addLast("PVD");
			d.addLast("SFO");
		}
		*/


	}
}



