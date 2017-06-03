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
	private Object[] data;
	private int size;
	private int head, tail;

	private static final int INIT_CAPACITY = 10;



	/////////////////////////////////////////////



	public static void main(String[] args) { 
		ArrayQueue<Integer> a = new ArrayQueue<Integer>();
		for(int i=0; i<42; i++) { 
			a.add(i);
		}
		for(int j=0; j<10; j++) {
			a.remove();
		}
		System.out.println(a);

	}



	/////////////////////////////////////////////



	public ArrayQueue() {
		this.head = 0;
		this.tail = 0;
		this.data = new Object[INIT_CAPACITY];
	}

	public boolean isEmpty() { 
		return(size()==0);
	}

	public int size() { 
		return (tail - head)%(data.length);
	}

	public String toString() { 
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if(isEmpty()) {
			sb.append("] ");
			return sb.toString();
		}
		// fencepost
		int i = head;
		sb.append(data[0].toString());
		while(i!=tail){
			// this ordering increments i immediately after the test,
			// ensuring this runs for the last element in data.
			i = (i+1)%(data.length);
			sb.append(", ");
			sb.append(data[i].toString());
		}
		sb.append(" ]");
		return sb.toString();
	}

	public void add(T e) {
		if(size==data.length) {
			resize(data.length*2);
		}
	}

	public T remove() {
		T first = data[head];
		data[head]=null;
		head++;
		return first;
	}

	private void resize(int newcap) { 
		int n = this.data.length;
		int length = (this.tail - this.head)%n;
		Object[] newdat = new Object[newcap];
		Object[] olddaat = this.data;
		int k = 0;
		for(int i=this.head; i!=this.tail; i=(i+1)%n ) { 
		    newdat[k] = olddata[i];
			k++;
		}
		this.head = 0;
		this.tail = length;
	}


}
