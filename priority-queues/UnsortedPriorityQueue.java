import java.util.LinkedListed;

public class UnsortedPriorityQueue<T> extends PriorityQueueBase<T> { 

	// Unsorted list of items in our priority queue
	LinkedList<Item<T>> data; 

	public UnsortedPriorityQueue() { 
		super(); 
		// Initialize the array of items
		data = new LinkedList<Item<T>>();
	}

	public void add(int k, T v) {
		// Make new item
		Item<T> newest = new Item<T>(k,v);

		// Put it anywhere
		data.add(newest);
	}

	public static void main(String[] args) { 
	}
}

