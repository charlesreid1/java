import java.util.*;
import java.io.*;

class Empty extends ArrayIndexOutOfBoundsException{};

/**
 * Array based implementation of a queue.
 *
 * methods:
 *  - toString
 *  - isEmpty
 *  - size
 *  - add
 *  - remove
 *  - peek
 */
public class ArrayDeque<T> {



	/////////////////////////////////////////////



	public static void main(String[] args) { 
		ArrayDeque<Integer> a = new ArrayDeque<Integer>();
		int na = 17;
		for(int i=0; i<na; i++) { 
			a.addFront(i);
		}

		for(int i=0; i<na; i++) { 
			a.addBack(100*i);
		}
		System.out.println(a);
		System.out.println("Added "+2*na+" items.");

		int nr = 5;
		System.out.println("Removing "+nr+" items from back.");
		for(int j=0; j<nr; j++) {
			a.removeBack();
			System.out.println(a);
		}

		System.out.println("Removing "+nr+" items from front.");
		for(int j=0; j<nr; j++) {
			a.removeFront();
			System.out.println(a);
		}
		System.out.println(a);
		System.out.println("Size: "+a.size());

		try {
			int many = 1000;
			System.out.println("Removing "+many+" items.");
			for(int j=0; j<many; j++) {
				System.out.println("Size of a is "+a.size()); 
				a.removeFront();
			}
			System.out.println(a);
		} catch(Empty e) {
			System.out.println("Caught empty stack exception.");
		}


	}



	/////////////////////////////////////////////
	//
	// Struggled to keep some of the details straight
	// because I put the fields way up at the top,
	// and lost track of size.

	private Object[] data;
	private int N, head, tail;
	private static final int INIT_CAPACITY = 1;


	public ArrayDeque() {
		this.head = 0;
		this.tail = 0;
		this.N = 0;
		this.data = new Object[INIT_CAPACITY];
	}

	public boolean isEmpty() { return N==0; }
	public int size() { return N; }

	/** Return string. */
	public String toString() { 

		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if(isEmpty()) { 
			sb.append("]");
			return sb.toString();
		}

		// we know length >= 1
		for(int i=this.head; i!=this.tail; i=(i+1)%data.length ) { 
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
	public void addBack(T e) {
		if(N==data.length) {
			resize(data.length*2);
		}
		tail = (tail+1)%(data.length);
		data[tail] = e;
		N++;
	}

	/** Append an item to the front of the queue */
	public void addFront(T e) { 
		if(N==data.length) {
			resize(data.length*2);
		}
		head = (head-1 + data.length)%(data.length);
		data[head] = e;
		N++;
	}

	/**Remove an item from the back of the queue */
	public Object removeBack() throws Empty {
		if(isEmpty()) {
			throw new Empty();
		}
		Object last = data[tail];
		data[tail]=null;
		tail = (tail-1 + data.length)%(data.length);
		N--;
		if(N>1 && N<=data.length/4) {
			resize(data.length/2);
		}
		return last;
	}

	/**Remove an item from the front of the queue */
	public Object removeFront() throws Empty {
		if(isEmpty()) {
			throw new Empty();
		}
		Object first = data[head];
		data[head]=null;
		head = (head+1)%(data.length);
		N--;
		if(N>1 && N<=data.length/4) {
			resize(data.length/2);
		}
		return first;
	}

	/** Resize array dynamically. */
	private void resize(int newcap) { 
		if(newcap>0) { 
			int n = this.data.length;

			Object[] newdat = new Object[newcap];
			Object[] olddat = this.data;
			int k = 0;

			for(int i=this.head; i!=this.tail; i=(i+1)%n ) { 
			    newdat[k] = olddat[i];
				k++;
			}
			newdat[k] = olddat[this.tail];

			this.data = newdat;
			this.head = 0;
			this.tail = k;
			this.N = k+1;
		}
	}
}
