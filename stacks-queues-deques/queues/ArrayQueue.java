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
public class ArrayQueue<T> {



	/////////////////////////////////////////////



	public static void main(String[] args) { 
		ArrayQueue<Integer> a = new ArrayQueue<Integer>();
		int na = 17;
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
			System.out.println("Removing "+many+" items.");
			for(int j=0; j<many; j++) {
				System.out.println("Size of a is "+a.size()); 
				a.remove();
				System.out.println(a);
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
	private int size, head, tail;
	private boolean empty;
	private static final int INIT_CAPACITY = 1;


	public ArrayQueue() {
		this.head = 0;
		this.tail = -1;
		this.size = 0;
		this.empty = true;
		this.data = new Object[INIT_CAPACITY];
	}

	public boolean isEmpty() { 
		return empty; 
	}

	/** Return the size - distance from head to tail - with rotating head and tail positions. */
	public int size() { 
		return size; 
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

		if(size==data.length) {
			resize(data.length*2);
		}
		tail++;
		data[tail] = e;
		size++;
		this.empty = false;
	}

	public Object remove() throws Empty {
		if(isEmpty()) {
			throw new Empty();
		}
		Object first = data[head];
		data[head]=null;
		head++;
		size--;
		if(size==0) {
			this.empty = true;
		} else if(size==data.length/4) {
			resize(data.length/2);
		}
		return first;
	}

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
			this.size = k+1;
		}
	}
}
