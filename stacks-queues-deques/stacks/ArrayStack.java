import java.util.*;
import java.io.*;

/** Empty exception */
class Empty extends Exception {
	// Note, subclass does not inherit any constructor.
	// We can define our constructor and call the super constructor,
	// or we can define no constructor, and always use the default constructor.
	public Empty(String msg) { 
		super(msg);
	}
}

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
 *
 * Exceptions:
 *	 - Empty exception if empty
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

		System.out.println("Pushing...");
		while(s.hasNextInt()) {
			st.push(s.nextInt());
		}
		System.out.println("Done.");

		try {
			System.out.println("Popping one item...");
			System.out.println(st.pop());

			System.out.println("Popping ten items...");
			for(int i=0; i<10; i++) { 
				System.out.println(st.pop());
			}

			System.out.println("Emptying stack...");
			while(st.size()>2) {
				st.pop();
			}

			System.out.println("Popping stack with two items...");
			System.out.println(st.pop());
			System.out.println(st.pop());

		} catch (Empty e) { 
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(st.pop());
		} catch (Empty e) {
			System.out.println("What is the sound of an empty stack popping?");
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
		if(capacity==data.length) {
			resize(capacity*2);
		}
		// Increment and set
		data[capacity++] = o;
	}

	/** Pop an object from the top of the stack: O(1). */
	public Object pop() throws Empty { 
		if(capacity<=1){
			throw new Empty("Popping an empty stack.");
		} 

		// Remove item from end of array
		// (top of stack)
		Object o = data[capacity-1];
		data[capacity-1] = null;
		capacity--;

		// Check if one quarter empty
		if(capacity<=(data.length/4)) {
			resize(capacity/2);
		}
		return o;
	}

	/** Return reference to object at top of stack: O(1). */
	public Object peek() throws Empty { 
		if(capacity<=1) {
			throw new Empty("Popping an empty stack.");
		}

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
		data = newdata;
	}
}
