/** Priority Queue ADT.
 *
 * Model elements and their priorities as a key-value composite Entry.
 *
 * The ADT is where we define any constructors or methods
 * that are possible to define without knowing the concrete
 * implementation of our priority queue.
 */
public abstract class AbstractPriorityQueue<K,V> 
			implements PriorityQueue<K,V> {

	protected static class PQEntry<K,V> implements Entry<K,V> {
		private K k;
		private V v;
		public PQEntry(K key, V value) { 
			this.k = key; this.v = value;
		}

		/* Methods associated with the Entry interface: */

		/** Return the key associated with this element. */
		public K getKey() { return k; }
		/** Return the value associated with this element. */
		public V getValue() { return v; }

		/* Protected utility methods to modify the values (get/set): */ 

		/** Set the key associated with this element. */
		protected void setKey(K k) { this.k = k; }

		/** Set the value associated with this element. */
		protected void setValue(V v) { this.v = v; }

	}



}
