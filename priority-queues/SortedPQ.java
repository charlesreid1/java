import java.util.LinkedList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Empty extends IndexOutOfBoundsException {};

// unsorted priority queue
public class SortedPQ<K,V> extends AbstractPriorityQueue<K,V> { 
	
	// primary collection
	private LinkedList<Item<K,V>> data; 

	// constructor calls the super
	public SortedPQ() { 
		super(); 
		data = new LinkedList<>();
	}
	public SortedPQ(Comparator<K> c) { 
		super(c); 
		data = new LinkedList<Item<K,V>>();
	}

	public void insert(K key, V value) { 
		checkKey(key);

		Item<K,V> newest = new Item<K,V>(key,value);

		if(isEmpty()) { 
			data.addFirst(newest);
		} else {
			
			Iterator<Item<K,V>> iter = data.descendingIterator();
			Item<K,V> walker = iter.next();
			// keep moving left while newest is less than walker
			int ix = data.size();
			while(walker!=null && compare(newest, walker)<0 ) {
				// advance walker left (left = next, descending iterator)
				try {
				    walker = iter.next();
					ix--;
				} catch(NoSuchElementException e) { 
					walker = null;
				}
			}
			// check what caused the stop
			if(walker==null) {  
				data.addFirst(newest);
			} else {
				data.add(ix,newest);
			}
		}
	}

	public V peekMin() {
		return data.get(0).getValue();
	}

	public V removeMin() {
		Item<K,V> it = data.remove(0);
		return it.getValue();
	}



	public int size() { 
		return data.size();
	}

	public String toString() { 
		return data.toString();
	}




	public static void main(String[] args) { 

		System.out.println("***********************");
		System.out.println("**** small test *******");

		SortedPQ<Integer,Integer> q = new SortedPQ<Integer,Integer>();
		
		q.insert(new Integer(5 ), new Integer(500));
		q.insert(new Integer(2 ), new Integer(200));
		q.insert(new Integer(3 ), new Integer(300));
		q.insert(new Integer(1 ), new Integer(100));
		q.insert(new Integer(99), new Integer(900));
		q.insert(new Integer(8 ), new Integer(800));
		q.insert(new Integer(7 ), new Integer(700));

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
