/** Templated linked list class. */
public class TList<E> {

	/** Linked list node class. 
	 *
	 * Implements:
	 *   - getData
	 *   - getNext
	 *   - setNext
	 */
	private static class Node<E> {
		private E data;
		private Node next;
		/** Constructor with specified value that points to nobody. */
		public Node(E data) {
			this(data,null);
		}
		/** Constructor with specified value that points to target. */
		public Node(E data, Node next) { 
			this.data = data;
			this.next = next;
		}
		/** Get data stored by node. */
		public E getData() { return data; }
		/** Get next node pointer. */
		public Node getNext() { return next; }
	}

}
