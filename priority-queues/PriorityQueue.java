/**
 * Priority queue base class.
 */
public class PriorityQueueBase {


    //////////////////////////

	//Item key-value class used to implement priority queue internally

	protected class Item implements Comparable<T> {
	    private int key;
	    private String value;

		/** Public item constructor. */
		public Item(int key, String value) { 
			this.key = key;
			this.value = value;
		}

	    /** Returns < 0 if this Item is less than the Item passed in. */
	    public int compareTo(Item i2) {
	        return this.key < i2.key;
	    }
		public int getKey() { return key; }
		public String getValue() { return value; }
	}


    //////////////////////////

    // Priority queue class

    private int size;

    public PriorityQueue() {
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
	public abstract void add(int k, String v) {

    /** Remove the minimum item from the priority queue. */
    public abstract String removeMin();

    /** Peek at the minimum item in the priority queue
     * w/o removing it.*/
    public abstract String peekMin();


}
