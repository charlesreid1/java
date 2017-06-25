import java.util.Iterator;

/**
 * Abstract implementation of Map ADT.
 *
 * This implements the Map interface.
 * It defines fields, methods, utility classes, etc.
 */
public abstract class AbstractMap<K,V> implements Map<K,V> {

	/////////////////////////////////////////
	// Utility classes

	/** 
	 * Map item.
	 * Represents a key-value pair in a map. 
	 * */
	protected static class MapItem<K,V> {
		private K k;
		private V v;
		public MapItem(K key, V value) { 
			this.k = key; this.v = value;
		}

		/** Return the key associated with this element. */
		public K getKey() { return k; }

		/** Return the value associated with this element. */
		public V getValue() { return v; }

		/** Set the key associated with this element. */
		protected void setKey(K k) { this.k = k; }

		/** Set the value associated with this element. */
		protected V setValue(V v) { 
			V old = this.v;
			this.v = v;
			return old;
		}

		public String toString() { return "("+v+")"; }
	}


	/** 
	 * Map key iterator.
	 * Creates an Iterator object over keys in the map.
	 * This wraps an iterator over the (iterable) entrySet.
	 * Implements Iterator<K>, obviously.
	 * */
	private class KeyIterator implements Iterator<K> {
		Iterator<MapItem<K,V>> entriesIterator; // key set iterator wraps entry set iterator
		/** Constructor initializes the entry iterator this key iterator wraps. */
		public KeyIterator() { 
			entriesIterator = entrySet().iterator();
		}
		/** Returns true if the iterator has a next key. */
		public boolean hasNext() {
			return entriesIterator.hasNext();
		}
		/** Next key. */
		public K next() { 
			// Get the next item, and then get its value
			return entriesIterator.next().getKey();
		}
		/** Remove key. */
		public void remove() { 
			throw new UnsupportedOperationException();
		}
	}

	/** KeyIterable is an Iterable that wraps KeyIterator. 
	 * (Iterables are boring, but are what the keySet/valueSet methods return.)
	 * */
	private class KeyIterable implements Iterable<K> {
		public Iterator<K> iterator() {
			return new KeyIterator();
		}
	}
	




	/** 
	 * Map value iterator.
	 * */
	private class ValueIterator implements Iterator<V> {
		Iterator<MapItem<K,V>> entriesIterator; // key set iterator wraps entry set iterator
		/** Constructor initializes the entry iterator. */
		public ValueIterator() { 
			entriesIterator = entrySet().iterator();
		}
		/** Returns true if the iterator has a next value. */
		public boolean hasNext() {
			return entriesIterator.hasNext();
		}
		/** Next value. */
		public V next() { 
			// Get the next item, and then get its value
			return entriesIterator.next().getValue();
		}
		/** Remove value. */
		public void remove() { 
			throw new UnsupportedOperationException();
		}
	}

	/** ValueIterable is an Iterable that wraps ValueIterator. 
	 * (Iterables are boring, but are what the keySet/valueSet methods return.)
	 * */
	private class ValueIterable implements Iterable<V> {
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}

	// End utility classes
	/////////////////////////////////////////


	/////////////////////////////////////////
	// Begin abstract map class definition

	/** entrySet is the principal iterator. 
	 * Note that we define this abstract method here, 
	 * because this is the first place where MapItem class is defined .*/
	public abstract Iterable<MapItem<K,V>> entrySet();

	/** Returns true if there are no key-value pairs in the map. */
	public boolean isEmpty() { return size() == 0; }

	/** Get an iterable object for keys. */
	public Iterable<K> keySet() { 
		return new KeyIterable();
	}

	/** Get an iterable object for values. */
	public Iterable<V> valueSet() {
		return new ValueIterable();
	}

}
