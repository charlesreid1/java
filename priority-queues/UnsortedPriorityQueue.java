class UnsortedPriorityQueue extends PriorityQueueBase { 

	private LinkedList<Item> items;

	public UnsortedPriorityQueue() { super(); }

	public void add(int k, String v) {
		// uses doubly linked list
		//
		// make new item
		// walk = data last
		// while walk is not none and newest < wealk.element
		//        walk = data.before(walk)
		// if walk is none
		//        add first (special case)
		// else   
		//        add after

		Item newest = new Item(k,v);
		Iterator<Item> iter = items.descendingIterator();
		
		Item walk = iter.next();
		while(walk != null && newest < walk) { 
			walk = iter.next();
		}
	}

	public static void main(String[] args) { 
	}
}

