public class MaxOriented {
	public static void main(String[] args) { 

		// Your mission:
		// You have numeric keys,
		// and you wish to have a priority queue that is max oriented.
		// Use a standard min-oriented sorted queue for this purpose.
		//
		// 1 -> 100
		// 2 -> 200
		// 3 -> 300
		// 4 -> 400
		// 5 -> 500

		System.out.println("Populating priority queue.");

		SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();

		q.add(3, new Integer(300));
		q.add(1, new Integer(100));
		q.add(2, new Integer(200));
		q.add(5, new Integer(500));
		q.add(4, new Integer(400));

		System.out.println("Removing items in max-oriented order.");
		while(!q.isEmpty()) { 
			System.out.println(q.removeMin());
		}

	}
}
