import java.util.*;

/** Abstract Hash Map implementation.
 *
 * This implements the Map interface and extends the 
 * AbstractMap abstract base type.
 *
 * The AbstractHashMap provides mathematical support 
 * for hash compression function, 
 * 
 * The AbstractHashMap also provides support for automatically
 * resizing the hash table.
 */
public abstract class AbstractHashMap<K extends Comparable<K>,V> 
	extends AbstractMap<K,V> { 

	// We need to *explicitly* require keys be comparable.

	// Re-use the MapItem class from AbstractMap



	// Fields:
	protected int size; // number of elements in map
	protected int capacity; // capacity of map
	protected int prime; // prime number
	protected int shift, scale; // hash function parameters



	// Public constructors:
	/** Default constructor, initializes map with capacity 11. */
	public AbstractHashMap() { 
		// Default constructor starts with capacity of 11
		this(11); 
	} 
	/** Constructor taking initial capacity of map. */
	public AbstractHashMap(int cap) { 
		// Constructor taking only init capacity as arg
		this(cap, 1789); 
	}
	/** Constructor taking initial capacity of map and prime p for hash function. */
	public AbstractHashMap(int cap, int p) { 
		prime = p;
		capacity = cap;
		size = 0;

		// Initialize the hash function parameter values
		Random r = new Random();
		scale = r.nextInt(prime-1)+1;
		shift = r.nextInt(prime);

		// Create the initial hash table
		createTable();
	}


	// Public methods:
	/** Returns the number of elements in this map. */
	public int size() { return size; }

	public boolean isEmpty() { return size==0; }

	public abstract void clear();

	/** Public method to get the value corresponding to this key. */
	public V get(K key) {
		return bucketGet( hashValue(key), key );
	}
	/** Public (abstract) method to remove the item with this key from the map. */
	public abstract V remove(K key);

	/** Public (abstract) method to put the item with this key and value into the map. */
	public abstract void put(K key, V value);


	// Semi-private methods:
	/** Compute the hash code for a key object. */
	protected int hashValue(K k) { 
		// Universal (MAD) hash function:
		//       ( (ax + b) mod p ) mod n
		int h = (int)((Math.abs(k.hashCode()*scale + shift) % prime) % capacity);
		return h; 
	}

	/** Create a new hash table with capacity this.capacity. */
	protected abstract void createTable();
	

	// Also need to implement a table resize method,
	// but not every concrete implementation will need one. 
	//
	// Resize procedure:
    // Copies map items to a list, 
    // sets the new internal capacity, 
    // creates a new table/array,
    // copies map items back into the map.


	/** Get the value corresponding to this key, and look in the bucket corresponding to this hash code. */
	protected abstract V bucketGet(int hashCode, K key);
	
	/** Remove the value corresponding to this key from the bucket corresponding to this hash code. */
	protected abstract V bucketRemove(int hashCode, K key);
	
	/** Put the key-value pair into the map bucket corresponding to this hash code. */
	protected abstract void bucketPut(int hashCode, K key, V value);

}
