import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

class Empty extends IllegalStateException {}

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

	/** Get number of elements in the queue. */
	public int size() { return this.size; }

	/** Return true if there are no elements in this queue. */ 
	public boolean isEmpty() { return this.size()==0; }


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

		//System.out.println(data.size());
		//Tim dum = new Tim();
		//dum.tic();

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

		//dum.toc();
		//System.out.println("One walk took "+dum.elapsedms()+" ms");

		// Add new key
		iter.add(newest);
		this.size++;

	}


	/** Remove the minimum item to the sorted priority queue.
	 *
	 * The priority queue is maintained in sorted order so this is an O(1) operation.
	 */
	public T peekMin() throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}
		Item<T> top = data.getFirst();
		return top.getValue();
	}

	public T removeMin() throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}
		Item<T> top = data.removeFirst();
		this.size--;
		return top.getValue();
	}




	///////////////////////////////////////////////

	// Static methods


	/** Main method: test sorted priority queue workings. */
	public static void main(String[] args) { 
		//smallTest();
		bigTest();
		//emptyTest();
	}


	/** Run through tests with a big priority queue. */
	public static void bigTest() { 
		System.out.println("***********************");
		System.out.println("**** big test *********");

		Random r = new Random();

		int N = 100000;

		SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();

		for(int i=0; i<N; i++) { 

			if(i%20000==0) { 
				System.out.println("Processing add "+i+" of "+N);
			}

			// Create random priority queue item
			Integer k = new Integer( r.nextInt(N) );
			Integer v = new Integer( r.nextInt() );
			q.add(k,v);

		}

		for(int i=0; i<1000; i++) { 
			if(i%50000==0) { 
				System.out.println("Processing remove "+i+" of "+ (N/2) );
			}
			q.removeMin();
		}

		System.out.println("Done.");
		System.out.println("Size is now "+q.size());

	}




	/** Run through tests with a small priority queue. */
	public static void smallTest() {
		System.out.println("***********************");
		System.out.println("**** small test *******");

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


	/** Test the empty exception. */
	public static void emptyTest() { 
		System.out.println("***********************");
		System.out.println("**** empty test *******");

		System.out.println("Populating priority queue.");

		SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();

		q.add(3, new Integer(300));
		q.add(2, new Integer(200));
		q.add(1, new Integer(100));
		q.add(8, new Integer(800));
		System.out.println("Emptying priority queue.");
		q.removeMin();
		q.removeMin();
		q.removeMin();
		q.removeMin();
		try {
			q.removeMin();
			q.removeMin();
		} catch(Empty e) { 
			System.out.println("Successfully caught empty exception.");
		} catch(Exception e) {
			System.out.println("Unexpected exception:");
			System.out.println(e.getMessage());
		}
	}

}


