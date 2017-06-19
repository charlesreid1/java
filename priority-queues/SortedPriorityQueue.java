import java.util.LinkedList;
import java.util.ListIterator;

public class SortedPriorityQueue<T> extends PriorityQueueBase<T> { 

	// Sorted list of items in our priority queue
	LinkedList<Item<T>> data; 

	/** Constructor of sorted priority queue initializes list of key-value items. */
	public SortedPriorityQueue() { 
		super(); 
		// Initialize the array of items
		data = new LinkedList<Item<T>>();
	}


	/** Return string representation of the items. */
	public String toString() { return data.toString(); }


	/** Add a new item, with key k and value v, to this sorted priority queue. 
	 *
	 * This maintains sorted order and is an O(N) operation. 
	 * */
	public void add(int k, T v) {

		// Make new item
		Item<T> newest = new Item<T>(k,v);

		// Handle empty list separately
		if(size()==0) { 
			data.add(newest);
			size++;
			return;
		}

		// If non-empty list:
		// Set walk to end of data list
		ListIterator<Item<T>> iter = data.listIterator(data.size());
		Item<T> walk = iter.previous();

		// While walk is not at beginning and newest < walk
		while(iter.hasPrevious() && newest.compareTo(walk)<0) { 
			walk = iter.previous();
		}

		// Don't ignore dupes. 
		if(newest.compareTo(walk)>0) { 
			// Insert item at end of list
			walk = iter.next();
		}

		// Add new key
		iter.add(newest);
		this.size++;

	}


	/** Remove the minimum item to the sorted priority queue.
	 *
	 * The priority queue is maintained in sorted order so this is an O(1) operation.
	 */
	public T peekMin() { 
		Item<T> top = data.getFirst();
		return top.getValue();
	}

	public T removeMin() { 
		Item<T> top = data.removeFirst();
		return top.getValue();
	}


	/** Main method: test sorted priority queue workings. */
	public static void main(String[] args) { 

		SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();
		
		q.add(5,  new Integer(500));
		q.add(2,  new Integer(200));
		q.add(3,  new Integer(300));
		q.add(1,  new Integer(100));
		q.add(99, new Integer(900));
		q.add(8,  new Integer(800));
		q.add(7,  new Integer(700));

		System.out.println("Full queue:");
		System.out.println(q);
		
		Integer min;
		Integer onehundred = new Integer(100);

		min = q.removeMin();
		if(min.equals(onehundred)) {
			System.out.println("Removed expected minimum of 100.");
		} else {
			System.out.println("Removed unexpected minimum. Expected 100, got "+min);
		}

		min = q.removeMin();
		if(min.equals(2*onehundred)) {
			System.out.println("Removed expected minimum of 200.");
		} else {
			System.out.println("Removed unexpected minimum. Expected 200, got "+min);
		}

		min = q.removeMin();
		if(min.equals(3*onehundred)) {
			System.out.println("Removed expected minimum of 300.");
		} else {
			System.out.println("Removed unexpected minimum. Expected 300, got "+min);
		}

		System.out.println("After removing three minimum items:");
		System.out.println(q);

	}
}


