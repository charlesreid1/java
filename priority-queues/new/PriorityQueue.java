/** Priority Queue interface.
 *
 * This defines the two simplest methods a priority queue can get away with.
 *
 * This is essentially a barebones list of methods. 
 *
 * Note that Item<K,V> is not defined here, it is defined in your concrete implementation.
 */
public interface PriorityQueue<K,V> extends Iterable<K> { 

	/** Returns true if the priority queue was changed. */
	public boolean insert(k,v);

	/** Remove and return the minimum element in this queue. */
	public V removeMin() throws Empty;

	/** Return, but do not remove, the minimum element in this queue. */
	public V peekMin() throws Empty;

	/** Returns a key-based iterator. */
	public Iterator<K> iterator();

	/** Returns an iterable container with all of the Items in this queue. */
	public Iterable<Item<K,V>> items();

	/** Returns the number of elements in this queue. */
	public int size();

	/** Returns true if there are no elements in this queue. */
	public isEmpty();
}

