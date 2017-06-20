import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

class Empty extends IllegalStateException {}

public class UnsortedPriorityQueue<T> extends PriorityQueueBase<T> { 

	// Unsorted list of items in our priority queue
	LinkedList<Item<T>> data; 

	public UnsortedPriorityQueue() { 
		super(); 
		// Initialize the array of items
		data = new LinkedList<Item<T>>();
	}

	/** Return string representation of the items. */
	public String toString() { return data.toString(); }

	/** Get number of elements in the queue. */
	public int size() { return this.size; } /* alt: keep track of it ourselves. */
	public boolean isEmpty() { return size()==0; }


	/** Add a new item, with key k and value v, to this sorted priority queue. */
	public void add(int k, T v) {
		// Make new item
		Item<T> newest = new Item<T>(k,v);

		// Put it anywhere, as long as it's fast
		data.addFirst(newest);

		size++;
	}


	/** Remove the minimum item from the priority queue. */
	public T removeMin() throws Empty { 
		if(isEmpty()) { 
			throw new Empty();
		}

		// If non-empty list:
		// Set walk to end of data list

		System.out.println(data.size());
		Tim dum = new Tim();
		dum.tic();

		ListIterator<Item<T>> iter = data.listIterator();
		Item<T> walk = iter.next();
		Item<T> min = walk;
		int c = 0;
		int mindex = 0;
		while(iter.hasNext()) { 
			walk = iter.next();
			if(walk.compareTo(min)<0) { 
				min = walk;
				mindex = c;
			}
			c++;
		}

		dum.toc();
		System.out.println("One walk took "+dum.elapsedms()+" ms");

		this.size--;
		data.remove(mindex);
		return min.getValue();
	}


	/** Peek the minimum item in the priority queue, but do not remove it. */
	public T peekMin() throws Empty { 
		//if(isEmpty()) { 
		//	throw new Empty();
		//
		//int mindex = findMin();
		//Item<T> item = data.get(mindex);
		//return item.getValue(); 
		return null;
	}



	///** Return index of minimum item in priority queue. */
	//private int findMin() throws Empty { 
	//	if(isEmpty()) { 
	//		throw new Empty();
	//	}

	//	// We are executing a while loop 
	//	// with a cumulative variable 
	//	// to find the location of the minimum.
	//	// Assertion: at least 1 item in the list.
	//	Item<T> minValue = data.get(0);
	//	int c = 0;
	//	int mindex = c;
	//	
	//	for(Item<T> canaryValue : data) { 
	//		if(canaryValue.compareTo(minValue)<0) { 
	//			mindex = c;
	//		}
	//		c++;
	//	}
	//	return mindex;
	//}


	/** main method */
	public static void main(String[] args) { 
		runTest();
	}


	/** Run through tests with an unsorted priority queue. */
	public static void runTest() { 
		System.out.println("****************************");
		System.out.println("**** unsorted test *********");

		Random r = new Random(10);

		int N = 1000000;

		UnsortedPriorityQueue<Integer> q = new UnsortedPriorityQueue<Integer>();

		System.out.println("Created new unsorted priority queue. Testing addition and removal...");
		System.out.println("Size is now "+q.size());

		for(int i=0; i<N; i++) { 

			if(i%50000==0) { 
				System.out.println("Processing add "+i+" of "+N);
			}

			// Create random priority queue items
			Integer k = new Integer( r.nextInt() );
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
}

