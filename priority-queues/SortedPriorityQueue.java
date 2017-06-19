import java.util.LinkedList;
import java.util.ListIterator;

public class SortedPriorityQueue<T> extends PriorityQueueBase<T> { 

	// Sorted list of items in our priority queue
	LinkedList<Item<T>> data; 

	public SortedPriorityQueue() { 
		super(); 
		// Initialize the array of items
		data = new LinkedList<Item<T>>();
	}

	public String toString() { return data.toString(); }

	public void add(int k, T v) {
		// Make new item
		Item<T> newest = new Item<T>(k,v);

		// Handle empty list separately
		if(size()==0) { 
			data.add(newest);
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

		// Add new key
		iter.add(newest);

		//if(!iter.hasPrevious()) { 
		//	// New key is the smallest
		//}
		this.size++;
	}

	public static void main(String[] args) { 
		SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();
		q.add(1,  new Integer(100));
		q.add(2,  new Integer(200));
		q.add(3,  new Integer(300));
		q.add(5,  new Integer(500));
		q.add(8,  new Integer(800));
		q.add(90, new Integer(900));
		System.out.println(q);
	}
}


