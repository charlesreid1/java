import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

class Empty extends IllegalStateException {}

//public class UnsortedPriorityQueue<K,V> extends PriorityQueueBase<K,V>
//    Entry<K,V>
public class UnsortedPriorityQueue<T> extends PriorityQueueBase<T> { 

	// Unsorted list of items in our priority queue
	LinkedList<Item<T>> data; 

	/** Constructor of sorted priority queue initializes list of key-value items. */
	public UnsortedPriorityQueue() { 
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

	/** Add a new item to the end of the priority queue. */
	public void add(int k, T v) { 
		Item<T> newest = new Item<T>(k,v);
		data.add(newest);
		this.size++;
	}


	/** Remove the minimum item to the sorted priority queue.
	 *
	 * The priority queue is maintained in sorted order so this is an O(1) operation.
	 */
	private T remove(boolean peek) throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}
		
		ListIterator<Item<T>> iter = data.listIterator(data.size());
		ListIterator<Item<T>> minIter = data.listIterator(data.size());
		Item<T> walker;
		Item<T> min;

		// fencepost 
		walker = iter.previous();
		min = walker;
		while(iter.hasPrevious()) { 
			walker = iter.previous();
			if(walker.compareTo(min)<0) {
				// Set new min
				min = walker;
				minIter = iter;
			}
		}
		min = minIter.next();
		if(peek) { 
			// just peeking
		} else {
			minIter.remove();
		}
		this.size--;
		return min.getValue();
	}

	/** Remove the smallest element in the priority queue. */
	public T removeMin() throws Empty { 
		boolean peek = false;
		return remove(peek);
	}

	/** Peek at, but do not remove, the smallest element in the priority queue. */
	public T peekMin() throws Empty { 
		boolean peek = true;
		return remove(peek);
	}






	///////////////////////////////////////////////

	// Static methods


	/** Main method: test sorted priority queue workings. */
	public static void main(String[] args) { 
		addTest();
		rmTest();
		//smallTest();
		//emptyTest();
	}


	/** Run through tests with a big priority queue. */
	public static void addTest() { 
		System.out.println("***********************");
		System.out.println("**** add test *********");

		Random r = new Random();

		int N = 1000000;
		int F = N/10;

		UnsortedPriorityQueue<Integer> q = new UnsortedPriorityQueue<Integer>();

		for(int i=0; i<N; i++) { 

			if((i+1)%F==0) { 
				System.out.println("Processing add "+(i+1)+" of "+N);
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

		//int N = 1000000; // flippin slow
		int N = 1000; // flippin fast
		int F = N/10;

		// Our main data "payload" (value) is an Integer. Nothing too exciting. Key is implicitly int.
		UnsortedPriorityQueue<Integer> q = new UnsortedPriorityQueue<Integer>();

		System.out.println("Running add test.");

		for(int i=0; i<N; i++) { 

			if((i+1)%F==0) { 
				System.out.println("Processing add "+(i+1)+" of "+N);
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
			if((i+1)%F==0) { 
				System.out.println("Processing rm "+(i+1)+" of "+(N/2));
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


