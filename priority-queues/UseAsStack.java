public class UseAsStack { 
	public static void main(String[] args) { 
		StackPQ<String> q = new StackPQ<String>();
		q.push("hello");
		q.push("world");
		q.push("this");
		q.push("is");
		q.push("the");
		q.push("earth");
		System.out.println(q);
		while(!q.isEmpty()) {
			System.out.println(q.pop());
			System.out.println(q);
		}
	}
}

// NOTE: extends should go before implements
class StackPQ<V> extends SortedPQ<Integer,V> implements PriorityQueue<Integer,V> { 

	// Stack can implement a priority queue
	// by adding items and assigning keys 
	// based on order of insertion.
	// Keys should be a negative counter,
	// to keep them in reverse order added.
	int counter;

	/** Start with an empty stack. */
	public StackPQ(){ counter = 0; }

	// Stack ADT methods:

	public void push(V v) {
		counter++; // start at 1
		this.insert(new Integer(-1*counter), v);
	}

	public V pop() {
		counter--;
		return removeMin();
	}

	// Can actually just use this.size()-1 instead of counter.
	// How do we communicate that insert, removeMin, peekMin, etc 
	// should probably not be used with this ADT? 
	// Don't sweat it. Just an exercise.
}
