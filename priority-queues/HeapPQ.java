import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Iterator;

/** Implement a heap-based priority queue.
 *
 * This utilizes an array to implement a complete binary tree
 * to hold sorted elements organized by priority. 
 *
 * Useful functionality:
 * Addition
 * Post-Addition Up-Heap Bubbling
 * Removal
 * Post-Removal Down-Heap Bubbling
 * Height
 */

// cmr: <K,V> syntax
// cmr: extends the abstract type, which implements the interface
public class HeapPQ<K,V> extends AbstractPriorityQueue<K,V> { 
	// cmr: Item<K,V> is from AbstractPriorityQueue class file, imported with extends above
	// protected b/c we are gonna extend this
	protected ArrayList<Item<K,V>> heap;

	// cmr: constructor calls super first. then makes list.
	public HeapPQ() { 
		super();
		heap = new ArrayList<Item<K,V>>();
	}

	// cmr: remember this was how we designed our abstract priority queue,
	// so that all PQ classes could use comparators and compare things however they want.
	public HeapPQ(Comparator<K> keyComparator) { 
		super(keyComparator);
	}

	// of course, this is always useful to do right after the constructors
	public String toString() { 
		return heap.toString();
	}

	// cmr: index math to turn a node into its parent/left/right nodes
	protected int parent(int j) { return (j-1)/2; }
	protected int left(int j) { return 2*j + 1; } 
	protected int right(int j) { return 2*j + 2; }

	// cmr: boolean checks if left/right exist in the heap 
	protected boolean hasLeft(int j) { return left(j) < heap.size(); }
	protected boolean hasRight(int j) { return right(j) < heap.size(); }




	// Utility:

	// swap
	protected void swap(int i, int j) {
		Item<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	// upheap
	protected void upheap(int j) { 
		// j==0 is root
		while(j>0) { 
			int p = parent(j);
			if(compare(heap.get(j), heap.get(p))>=0) {
				break; // heap order property satisfied
			}
			swap(j,p);
			j = p; // continue from parent
		}
	}

	// recursive upheap
	protected void upheap_r(int j) { 
		// now check for recursive case
		int p = parent(j);
		if(compare(heap.get(j), heap.get(p))<0) {
			swap(j,p);
			self.upheap_r(parent);
		}
		// else base case, return nothing
	}


	// downheap
	protected void downheap(int j) { 
		while(hasLeft((j))) {
			int leftIndex = left(j);
			// assume left child is smaller
			int smallChildIndex = leftIndex;
			// if right child exists, check if right child is smaller
			if(hasRight(j)) { 
				int rightIndex = right(j);
				if(compare(heap.get(leftIndex), heap.get(rightIndex))>0) {
					smallChildIndex = rightIndex;
				}
			}
			if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) { 
				break; // heap order property satisfied
			}
			swap(j, smallChildIndex);
			j = smallChildIndex; // continue from child
		}
	}

	// recursive downheap
	protected void downheap_r(int j) { 
		if(hasLeft(j)) { 
			int leftIndex = left(j);
			// assume left child is smaller
			int smallChildIndex = leftIndex;
			// if right child exists, check if right child is smaller
			if(hasRight(j)) { 
				int rightIndex = right(j);
				if(compare(heap.get(leftIndex), heap.get(rightIndex))>0) {
					smallChildIndex = rightIndex;
				}
			}
			// now check for recursive case
			if(compare(heap.get(smallChildIndex), heap.get(j))<0) { 
				swap(j, smallChildIndex);
				downheap_r(smalllChildIndex);
			}
			// else base case, return nothing
		}
	}





	// Really, the main content of this class
	// (other than the upheap/downheap/swap methods):

	// insert
	public void insert(K key, V value) { 
		checkKey(key);
		Item<K,V> newitem = new Item<K,V>(key, value);
		heap.add(newitem);
		upheap( heap.size() - 1 ); // upheap the newest entry (last item in array)
	}

	// remove min
	public V removeMin() { 
		if(heap.isEmpty()) { 
			throw new Empty();
		}
		// before you lose the reference to the minimum item,
		// get a reference to it.
		Item<K,V> expunge = heap.get(0);
		// swap the minimum item with the item that will replace it 
		swap(0, heap.size()-1); // swap the base of the heap with the last item in the tree
		heap.remove(heap.size()-1); // remove the last item, which no one cares about anymore
		downheap(0); // restore heap order priority (HOP)
		return expunge.getValue(); // return the value of the item we grabbed before removing.
	}

	public V peekMin() {
		if(heap.isEmpty()) { 
			throw new Empty();
		}
		return heap.get(0).getValue();
	}



	// Priority queue interface:

	// get size
	public int size() { 
		return heap.size();
	}

	// protected iterator class for making this class iterable (PriorityQueue extends Iterable)
	protected class ItemIterator implements Iterator<K> {
		Iterator<Item<K,V>> it;
		public ItemIterator() { 
			it = heap.iterator();
		}
		public boolean hasNext() { return it.hasNext(); }
		public K next() { return it.next().getKey(); }
		public void remove() { it.remove(); }
	}

	public Iterator<K> iterator() { 
		return new ItemIterator();
	}





	// main method and tests

	public static void main(String[] args) { 

		System.out.println("***********************");
		System.out.println("**** small test *******");

		HeapPQ<Integer,Integer> q = new HeapPQ<Integer,Integer>();
		q.insert(new Integer(55), new Integer(15555));
		q.insert(new Integer(22), new Integer(12222));
		q.insert(new Integer(33), new Integer(13333));
		q.insert(new Integer(11), new Integer(11111));
		q.insert(new Integer(91), new Integer(19191));
		q.insert(new Integer(81), new Integer(18181));
		q.insert(new Integer(72), new Integer(17272));

		System.out.println("Full queue:");
		System.out.println(q);
		
		Integer min;
		min = q.removeMin();
		min = q.removeMin();
		min = q.removeMin();

		System.out.println("After removing three minimum items:");
		System.out.println(q);

		System.out.println("Empty the remaining items with an iterator."); // iterator() returns a <key> iterator, not a <key,value> iterator
		Iterator<Integer> k = q.iterator();
		while(k.hasNext()) {
			k.next();
			k.remove();
		}
		System.out.println(q);
	}

}

