import java.util.*;
import java.io.*;

class Empty extends ArrayIndexOutOfBoundsException{};
class Full extends ArrayIndexOutOfBoundsException{};

/**
 * Array-based implementation of a fixed-size queue.
 *
 * methods:
 *  - toString
 *  - isEmpty
 *  - size
 *  - add
 *  - remove
 *  - peek
 */
public class ArrayQueueFS<T> {



	/////////////////////////////////////////////



	public static void main(String[] args) { 
		ArrayQueueFS<Integer> a = new ArrayQueueFS<Integer>(15);
		int na = 15;
		for(int i=0; i<na; i++) { 
			a.add(i);
			System.out.println(a);
		}
		System.out.println(a);
		System.out.println("Added "+na+" items.");

		int nr = 13;
		System.out.println("Removing "+nr+" items.");
		for(int j=0; j<nr; j++) {
			a.remove();
			System.out.println(a);
		}
		System.out.println(a);
		System.out.println("Size: "+a.size());
		
		try {
			int many = 1000;
			System.out.println("Adding "+many+" items.");
			for(int j=0; j<many; j++) {
				System.out.println("Size of a is "+a.size()); 
				a.add(j);
				System.out.println(a);
			}
			System.out.println(a);
		} catch(Full e) {
			System.out.println("Caught full queue exception.");
		}

		try {
			int many = 1000;
			System.out.println("Removing "+many+" items.");
			for(int j=0; j<many; j++) {
				System.out.println("Size of a is "+a.size()); 
				a.remove();
				System.out.println(a);
			}
			System.out.println(a);
		} catch(Empty e) {
			System.out.println("Caught empty queue exception.");
		}


	}



	/////////////////////////////////////////////
	//
	// Struggled to keep some of the details straight
	// because I put the fields way up at the top,
	// and lost track of size.

	private Object[] data;
	private int N, head, tail;
	private boolean empty;
	private static int CAPACITY;


	public ArrayQueueFS(int capacity) {
		CAPACITY = capacity;

		this.head = 0;
		this.tail = -1;
		this.N = 0;
		this.empty = true;
		this.data = new Object[CAPACITY];
	}

	public boolean isEmpty() { 
		return empty; 
	}

	/** Return the size - distance from head to tail - with rotating head and tail positions. */
	public int size() { 
		return N; 
	}

	public String toString() { 
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if(isEmpty()) { 
			sb.append("]");
			return sb.toString();
		}

		// length >= 1
		int n = data.length;
		for(int i=this.head; i!=this.tail; i=(i+1)%n ) { 
			// fencepost
			if(i!=this.head) { 
				sb.append(", ");
			}
			sb.append(data[i].toString());
		}
		sb.append(" ]");
		return sb.toString();
	}

	/** Append an item to the back of the queue */
	public void add(T e) {

		if(N==CAPACITY) { 
			throw new Full();
		}
		tail = (tail+1)%(CAPACITY);
		data[tail] = e;
		N++;
		this.empty = false;
	}

	public Object remove() throws Empty {
		if(isEmpty()) {
			throw new Empty();
		}
		Object first = data[head];
		data[head]=null;
		head = (head+1)%(CAPACITY);
		N--;
		if(N==0) {
			this.empty = true;
		}
		return first;
	}

}
