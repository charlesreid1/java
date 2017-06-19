/**
 * Priority queue base class.
 */
public abstract class PriorityQueueBase<T> {


    //////////////////////////

	//Item key-value class used to implement priority queue internally

	protected class Item<T> implements Comparable<Item<T>> {
	    private int key;
	    private T value;

		/** Public item constructor. */
		public Item(int key, T value) { 
			this.key = key;
			this.value = value;
		}

	    /** Returns < 0 if this Item is less than the Item passed in. */
	    public int compareTo(Item<T> i2) {
	        return (this.key - i2.key);
	    }
		public int getKey() { return key; }
		public T getValue() { return value; }
		public String toString() { return " ("+key+") "+value.toString(); }
	}


    //////////////////////////

    // Priority queue class

    protected int size;

    public PriorityQueueBase() {
        this.size = 0; // meh
        // zzz....
    }

    /** Returns true if there are no elements in this priority queue. */
    public boolean isEmpty() { return this.size==0; }

    /** Returns the number of elements in this priority queue. */
    public int size() { return this.size; }

    /////////////////////////////////////////////////

    // Abstract methods - unfinished business.

    /** Add an item to the priority queue. */
	public abstract void add(int k, T v);

    ///** Remove the minimum item from the priority queue. */
    //public abstract T removeMin();

    ///** Peek at the minimum item in the priority queue
    // * w/o removing it.*/
    //public abstract T peekMin();


}
