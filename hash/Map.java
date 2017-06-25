/** 
 * Formal Map ADT Interface.
 *
 * This defines an interface for any map types.
 */
public interface Map<K,V> {
	int size();
	boolean isEmpty();
	V get(K key);
	V put(K key, V value);
	V remove(K key);

	/** entrySet is the principal iterator. */
	Iterable<Item<K,V>> entrySet();

	/** keySet is an iterable wrapping entrySet. */
	Iterable<K> keySet();

	/** valueSet is an iterable wrapping entrySet. */
	Iterable<V> valueSet();
}
