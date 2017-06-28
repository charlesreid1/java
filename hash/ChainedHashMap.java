import java.util.*;

public class ChainedHashMap<K extends Comparable<K>,V> 
	extends AbstractHashMap<K,V> {

	public static void main(String[] args) { 
		ChainedHashMap<String,String> hm = new ChainedHashMap<String,String>();
		hm.put("documentation","That which we know nothing about, we must pass over in silence.");
		hm.put("models","All models are wrong, some models are useful.");
		hm.put("buddha","You will not be punished for your anger, you will be punished by your anger.");
		hm.put("knowledge","All the evolution we know of proceeds from the vague to the definite.");

		System.out.println("Hash map size: "+hm.size());
		System.out.println("Hash map entry for buddha: "+hm.get("buddha"));

		System.out.println("Removing an item...");
		hm.remove("knowledge");
		System.out.println("Hash map size: "+hm.size());

		System.out.println("Iterating over items: ");
		for(MapItem<String,String> item : hm.itemSet()) {
			String i = item.getKey();
			String j = item.getValue();
			System.out.println(i + " --> " + j);
		}
		System.out.println("Done.");

	}



	// Fields:
	private ArrayList<UnsortedArrayMap<K,V>> bukkits;

	/////////////////////////////
	// Question: how do you use an ArrayList
	// like an array? Specifying initial capacity
	// does not seem to change anything for the user.
	// Calling add(2,x) will add x to location 3,
	// but even if we declare an init capacity of 5,
	// and then call add(2,x), it throws 
	// IndexOutOfBounds exception
	/////////////////////////////

	// Constructors:
	public ChainedHashMap() {
		super();
	}
	public ChainedHashMap(int cap) {
		super(cap);
	}
	public ChainedHashMap(int cap, int p) {
		super(cap, p);
	}



	// Protected methods:

	/** Create a table with internally-specified capacity. 
	 * Note that capacity is not number of items, capacity is number of buckets. */
	protected void createTable() {
		// Create the specified number of bukkits
		bukkits = new ArrayList<UnsortedArrayMap<K,V>>(capacity);
	}

	/** Get the value of the item corresponding to key k in the hash code bucket corresponding to hashFunction(k). 
	 * This returns null of the specified key is not found. */
	protected V bucketGet(int hashCode, K k) { 
		// We store the UnsortedArrayMaps in an ArrayList.
		// When we need one, we say ArrayList.get() and pass it the index or object (?).
		// Once we have the bucket, we make sure it is not empty.
		// If it is, we return null.
		// If it is not, we return the value corresponding to this key.
		UnsortedArrayMap<K,V> mah_bukkit;
		try {
			mah_bukkit = bukkits.get(hashCode);
		} catch (IndexOutOfBoundsException e) { 
			return null;
		}
		// Return the value corresponding to the key in this bukkit
		return mah_bukkit.get(k);
	}

	/** Remove the item corresponding to key k in the hash code bucket corresponding to hashFunction(k).
	 * This returns null of the specified key is not found. */
	protected V bucketRemove(int hashCode, K k) { 
		// Remove method will first try to get the right bucket.
		UnsortedArrayMap<K,V> mah_bukkit;
		try {
			mah_bukkit = bukkits.get(hashCode);
		} catch (IndexOutOfBoundsException e) { 
			return null;
		}
		mah_bukkit = bukkits.get(hashCode);
		// Next, it will remove the item with key k from the bucket.
		// It will update the size of the map.
		// (No need to resize.) 
		int oldsize = mah_bukkit.size();
		V result = mah_bukkit.remove(k);
		size -= (oldsize - mah_bukkit.size());
		return result;
	}

	/** Put the item corresponding to key k and value v into the bucket corresponding to hashFunction(k), and resize. */
	protected void bucketPut(int hashCode, K k, V v) {
		// Put method will first try to get the right bucket.
		// (If no bucket, create one.)
		UnsortedArrayMap<K,V> mah_bukkit;
		try {
			mah_bukkit = bukkits.get(hashCode);
		} catch (IndexOutOfBoundsException e) { 
			// Create new bukkit
			mah_bukkit = new UnsortedArrayMap<K,V>();
			bukkits.add(mah_bukkit);
		}
		// Next, put the item with key k into the bucket.
		// (No need to resize.) 
		int oldsize = mah_bukkit.size();
		mah_bukkit.put(k,v);
		size += (mah_bukkit.size() - oldsize);
	}

	/** Chained hash map does not need to implement resize. */
	protected void resize() {
		// Questionable...
		throw new UnsupportedOperationException("Not implemented, now.");
	}



	// Public methods:

	/** Get an item from the map. */
	public V get(K key) { 
		V result = bucketGet(hashValue(key), key);
		return result;
	}

	/** Put an item into the map. */
	public void put(K key, V value) {
		// Put it in the map using the private put method.
		// Resize if needed.
		bucketPut(hashValue(key), key, value);
	}

	/** Remove an item from the map. */
	public V remove(K key) { 
		V result = bucketRemove(hashValue(key), key);
		return result;
	}

	/** Clear all items in this ChainedHashMap. */
	public void clear() { 
		bukkits.clear();
	}

	/** Iterator over items in this map. */
	public Iterable<MapItem<K,V>> itemSet() { 
		ArrayList<MapItem<K,V>> buffer = new ArrayList<MapItem<K,V>>();
		for(UnsortedArrayMap<K,V> bukkit : bukkits) { 
			for(MapItem<K,V> item : bukkit.itemSet()) {
				if(item!=null) {
					buffer.add(item);
				}
			}
		}
		return buffer;
	}

}

