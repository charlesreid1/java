import java.util.LinkedList;
import java.util.Comparator;

class Empty extends IndexOutOfBoundsException {};

// unsorted priority queue
public class UnsortedPQ<K,V> extends AbstractPriorityQueue<K,V> { 
	
	// primary collection
	private LinkedList<Item<K,V>> data; 

	// constructor calls the super
	public UnsortedPQ() { 
		super(); 
		data = new LinkedList<>();
	}
	public UnsortedPQ(Comparator<K> c) { 
		super(c); 
		data = new LinkedList<Item<K,V>>();
	}

	// return position of item with minimal key
	// needs to return some kind of *position*
	protected int findMin() { 
		if(isEmpty()) { 
			throw new Empty();
		}
		Item<K,V> mymin = data.getFirst();
		int minix = 0;
		int c = 0;
		for(Item<K,V> walker : data) { 
			if(compare(walker, mymin) < 0) {
				// current walker is smaller than minimum
				mymin = walker;
				minix = c;
			}
			c++;
		}
		return minix;
	}

	// insert
	public void insert(K key, V value) { 
		Item<K,V> ni = new Item<K,V>(key, value);
		data.add(ni);
	}

	// peek min 
	// needs to return some kind of *position*
	public V peekMin() { 
		if(isEmpty()) { 
			throw new Empty();
		}
		return data.get( findMin() ).getValue();
	}

	// rm min 
	// needs to return some kind of *position*
	public V removeMin() { 
		if(isEmpty()) { 
			throw new Empty();
		}
		return data.remove( findMin() ).getValue();
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

		UnsortedPQ<Integer,Integer> q = new UnsortedPQ<Integer,Integer>();
		
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
