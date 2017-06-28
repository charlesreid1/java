import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Unsorted Array Map.
 *
 * A concrete implementation of the map type that stores
 * key-value items in an arraylist. 
 *
 * This class is terribly inefficient, requiring O(N) lookups each time.
 */
public class UnsortedArrayMap<K extends Comparable<K>,V> 
	extends AbstractMap<K,V> {

	public static void main(String[] args) { 
		UnsortedArrayMap<String,String> m = new UnsortedArrayMap<>();
		m.put("WAT","monkey");
		m.put("WHO","tree");
		m.put("WHY","sticks");
		System.out.println(m.size());
		System.out.println(m.get("WHO"));
	}



	//////////////////////////////////////////////////
	// Utility classes

	/** Item iterator object to iterate through key-value items in the map. */
	private class ItemIterator implements Iterator<MapItem<K,V>> {
		private int j = 0;
		public boolean hasNext() {
			return j < table.size();
		}
		public MapItem<K,V> next() { 
			// This is not wrapping a nice easy tipe.
			// Check for errors!
			if(j==table.size()) { 
				throw new NoSuchElementException();
			}
			// Return and increment at the same time
			return table.get(j++);
		}
		public void remove() { 
			throw new UnsupportedOperationException();
		}
	}

	/** Return a new Iterable object over all the map items. */
	private class ItemIterable implements Iterable<MapItem<K,V>> {
		public Iterator<MapItem<K,V>> iterator() { 
			return new ItemIterator();
		}
	}


	//////////////////////////////////////////////////


	// Table stores our keys
	private ArrayList<MapItem<K,V>> table;

	// Construct initial table map
	public UnsortedArrayMap() { 
		table = new ArrayList<>();
	}

	/** Return an iterable collection of all key-value items in map. */
	public Iterable<MapItem<K,V>> itemSet() { 
		return new ItemIterable();
	}

	/** Find the index of an entry with thatKey. 
	 * Returns -1 if key is not in map. */
	protected int findIndex(K thatKey) { 
		// Walk through the table looking for our key
		int n = table.size();
		for(int j=0; j<n; j++ ) {
			K thisKey = table.get(j).getKey();
			if(thisKey.equals(thatKey)) {
				return j;
			}
		}
		return -1; // not in map
	}

	/** Return the number of elements in the map. */
	public int size(){
		return table.size();
	}

	/** Clear all elements from map. */
	public void clear() { 
		table.clear();
	}

	/** Get the value corresponding to a given key in our table. 
	 * The findIndex O(N) lookup makes this really inefficient.
	 * This gets the index corresponding to the key, then returns the item value.
	 * */
	public V get(K key) {
		int j = findIndex(key);
		if(j==-1) {
			return null;
		}
		return table.get(j).getValue();
	}

	/** Put the key-value pair in our table.
	 * If a value already exists, replace the old value in the map.
	 */
	public void put(K key, V value) { 
		// First check if this is already in the map
		int j = findIndex(key);
		if(j==-1) { 
			// Add it to the list - err, map
			MapItem<K,V> item = new MapItem<K,V>(key,value);
			table.add(item);
		} else {
			// Replace it
			table.get(j).setValue(value);
		}
	}

	/** Remove the item with the given key from the map.
	 * Returns null if key is not found.
	 */
	public V remove(K key) { 
		int n = size();
		int j = findIndex(key);
		if(j==-1) { 
			// Item not found
			return null;
		}
		V answer = table.get(j).getValue();
		if(j!=(n-1)) {
			// Replace the removed item (at j) with the last entry (at n-1)
			table.set(j, table.get(n-1));
		}
		// Now remove it
		table.remove(n-1);
		return answer;
	}
}

