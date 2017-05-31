import java.util.*;
import java.io.*;

/** Array-based implementation of stack ADT. 
 *
 * Uses a dynamic array to keep track of the stack.
 * Top of stack is at last element of array.
 * 
 * Stack ADT:
 *   - push
 *   - pop
 *   - peek
 *   - size
 */
public class ArrayStack { 
	private int capacity; // Capacity of stack
	private Object[] data; // Stack data
	private static final int INIT_CAP = 10; // Initial capacity of stack

	/** Test the ArrayStack. */
	public static void main(String[] args) { 

		// New empty stack
		ArrayStack st = new ArrayStack();

		// Read ints from file
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		while(s.hasNextInt()) {
			st.push(s.nextInt());
		}
	}

	/** Create a stack and an Object array for the Stack */
	public ArrayStack() {
		capacity = 0;
		data = new Object[INIT_CAP];
	}

	/** Return size of stack: O(1). */
	public int size() {
		return capacity;
	}

	/** Push an object onto the top of the stack: O(1). */
	public void push(Object o) { 
		// Check if full
		if(capacity==len(data)) {
			resize(capacity*2);
		}
		// Increment and set
		data[capacity++] = o;
	}

	/** Pop an object from the top of the stack: O(1). */
	public Object pop() { 
		// Remove item from end of array
		// (top of stack)
		Object o = data[capacity];
		data[capacity] = null;
		capacity--;
		// Check if one quarter empty
		if(capacity<=(len(data)/4)) {
			resize(capacity/2);
		}
		return o;
	}

	/** Return reference to object at top of stack: O(1). */
	public Object peek() { 
		// Return reference to top of stack
		return data[capacity];
	}

	/** Resize the array: O(n). */
	private void resize(int newcap) { 
		Object[] newdata = new Object[newcap];
		int i = 0;
		for(Object o : data) {
			newdata[i] = o;
			i++;
		}
	}
}
