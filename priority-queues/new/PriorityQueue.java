/** Prioirity Queue interface.
 *
 * This defines the two simplest methods a priority queue can get aaaway with.
 * This is essentially a barebones list of methods. 
 */
public interface PriorityQueue<K,V> { 

	/** Returns true if the priority queue was changed. */
	public abstract boolean insert(k,v);

	/** Remove and return the minimum element in this queue. */
	public abstract V removeMin() throws Empty;

	/** Return, but do not remove, the minimum element in this queue. */
	public abstract V min() throws Empty;

	/** Returns the number of elements in this queue. */
	public abstract int size();

	/** Returns true if there are no elements in this queue. */
	public boolean isEmpty();
}

interface Entry<K,V> {
	K getKey(); 
	V getValue();
}

