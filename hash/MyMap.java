import java.util.Iterator;

/** 
 * Formal Map ADT Interface.
 *
 * This defines an interface for any map types.
 */
public interface MyMap<K,V> {

	/** Get the size of the map. */
	int size();

	/** Returns true of there are no elements left in the map. */
	boolean isEmpty();

	/** Get the item corresponding to key. */
	V get(K key);

	/** Put the key value pair into the map. */
	void put(K key, V value);

	/** Remove the item corresponding to key k into the map. */
	V remove(K key);


	// Note: we cannot define itemSet to get iterator over items,
	// because we do not have definition of Items class.


	/** keySet is an iterable wrapping itemSet. */
	Iterable<K> keySet();

	/** valueSet is an iterable wrapping itemSet. */
	Iterable<V> valueSet();
}
