import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

class Empty extends IllegalStateException {}

//public class SortedPriorityQueue<K,V> extends PriorityQueueBase<K,V>
//    Entry<K,V>
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



	public void add(int k, T v) { 

		Item<T> newest = new Item<T>(k,v);
		ListIterator<Item<T>> iter = data.listIterator(data.size());

		// Deal with the empty list case
		if(!iter.hasPrevious()) { 
			data.addFirst(newest);
		} else {

			Item<T> walk = iter.previous();
			int size = data.size();
			// inner check
			while(iter.hasPrevious() && newest.compareTo(walk) < 0) { 
				walk = iter.previous();
				size--;
			}

			// Reached the end - check which condition it was
			if(!iter.hasPrevious()) {
				// Iterator is emptied out
				// Add to front
				// Highest priority
				data.addFirst(newest);
			} else {
				// Add at hurrr
				iter.add(newest);
			}
		}

		// Finally, always increment
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
		addTest();
		rmTest();
		//emptyTest();
	}


	/** Run through tests with a big priority queue. */
	public static void addTest() { 
		System.out.println("***********************");
		System.out.println("**** add test *********");

		Random r = new Random();

		int N = 10000000;
		int F = N/10;

		SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();

		for(int i=0; i<N; i++) { 

			if(i%F==0) { 
				System.out.println("Processing add "+i+" of "+N);
			}

			// Create random priority queue item
			Integer k = new Integer( r.nextInt() );
			Integer v = new Integer( r.nextInt() );
			q.add(k,v);

		}
		System.out.println("Done.");
		System.out.println("Size is now "+q.size());

	}



	/** Run through tests with a big priority queue. */
	public static void rmTest() { 
		System.out.println("***********************");
		System.out.println("***** rm test *********");

		Random r = new Random();

		int N = 10000000;
		int F = N/10;

		// Our main data "payload" (value) is an Integer. Nothing too exciting. Key is implicitly int.
		SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();

		System.out.println("Running add test.");

		for(int i=0; i<N; i++) { 

			if(i%F==0) { 
				System.out.println("Processing add "+i+" of "+N);
			}
			// Create random priority queue item
			Integer k = new Integer( r.nextInt() );
			Integer v = new Integer( r.nextInt() );
			q.add(k,v);
		}
		System.out.println("Done.");
		System.out.println("Size is now "+q.size());

		System.out.println("Running remove test.");
		for(int i=0; i<(N/2); i++) { 
			if(i%F==0) { 
				System.out.println("Processing rm "+i+" of "+(N/2));
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


