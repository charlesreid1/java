import java.util.*;

/**
 * Sorted Array Map.
 *
 * A concrete implementation of the map type that stores
 * key-value items in an arraylist. 
 *
 * This class is terribly inefficient, requiring O(N) lookups each time.
 */
public class SortedArrayMap<K extends Comparable<K>,V> 
	extends AbstractMap<K,V> {

	public static void main(String[] args) { 
		SortedArrayMap<String,String> m = new SortedArrayMap<>();
		m.put("graft","A shoot or twig inserted into a slit on the trunk or stem of a living plant, from which it receives sap.");
		m.put("signal","An indication of a situation.");
		m.put("island","A piece of land surrounded by water.");
		m.put("electron","A stable subatomic particle with a charge of negative electricity, found in all atoms and acting as the primary carrier of electricity in solids.");
		System.out.println("Size of map: " + m.size());
		System.out.println("Entry for signal: " + m.get("signal"));
	}


	//////////////////////////////////////////////////
	// Utility classes

	/** Item iterator object to iterate through key-value items in the map. 
	 *
	 * This is not different from the UnsortedArrayMap.
	 *
	 * This class must be in the concrete implementation - note the 
	 * references below to table, the list of key-value items.
	 *
	 * This is linked to the concrete implementation, but applies to both classes.
	 * For better organization, we should have an ArrayMap parent class,
	 * and then the child classes implement details.  
	 * */
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

	private Comparator<MapItem<K,V>> comp;

	// Construct initial table map
	public SortedArrayMap() { 
		table = new ArrayList<>();

		comp = new Comparator<MapItem<K,V>>() {
			public int compare(MapItem<K,V> i1, MapItem<K,V> i2) {
				return i1.getKey().compareTo(i2.getKey());
			}
		};
	}

	/** Return an iterable collection of all key-value items in map. */
	public Iterable<MapItem<K,V>> itemSet() { 
		return new ItemIterable();
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
	 * Returns null if key is not in map.
	 * O(log N)
	 * */
	public V get(K key) {
		MapItem<K,V> sentinel = new MapItem<>(key,null);
		int j = Collections.binarySearch(table, sentinel, comp); 
		if(j<0) { 
			return null;
		}
		return table.get(j).getValue();
	}

	/** Put the key-value pair in our table.
	 * If a value already exists, replace the old value in the map.
	 */
	public void put(K key, V value) { 
		// First check if this is already in the map
		MapItem<K,V> item = new MapItem<>(key,value);
		int j = Collections.binarySearch(table, item, comp);
		if(j<0) { 
			// Add it to the list - err, map
			// insertion index returned is j = -(i+1)
			// this becomes i = -(j+1)
			int i = -(j+1);
			table.add(i,item);
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
		MapItem<K,V> sentinel = new MapItem<>(key,null);
		int j = Collections.binarySearch(table, sentinel, comp);
		if(j<0) { 
			// Item not found
			return null;
		}
		V answer = table.get(j).getValue();
		// Now remove it
		table.remove(j);
		return answer;
	}
}










