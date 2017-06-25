import java.util.PriorityQueue;
import java.util.Comparator;

/** 
 * String Comparison Priority Queue.
 *
 * This class demonstrates the use of a comparator 
 * with the built-in PriorityQueue class
 * to allow sorting of data according to 
 * aribtrary criteria.
 *
 * Note that the built-in PriorityQueue class
 * uses a single templated type <E> as both 
 * the key and the value, so you can pass in a 
 * comparator and it will sort each element value
 * (which is also its own key) using that comparator.
 */
public class StringComparisonPQ {
	public static void main(String[] args) { 

		System.out.println("Length comparator:");

		// the initial capacity (first integer argument) does not seem to matter at all
		PriorityQueue<String> q = new PriorityQueue<String>(1,new StringLengthComparator());
		q.add("aaaaaaaaaaaaaaaaaaaaaaa");
		q.add("cccccccccccccccc");
		q.add("bbbbbbbbbbbbbbbbbbb");
		q.add("eeeeeeeeeee");
		q.add("ddddddddddddddd");
		q.add("yyyyyy");
		q.add("wwwwwwww");
		q.add("z");
		while(!q.isEmpty()) { 
			System.out.println(q.remove());
		}
		
		System.out.println();

		System.out.println("No comparator:");

		PriorityQueue<String> q2 = new PriorityQueue<String>(1);
		q2.add("aaaaaaaaaaaaaaaaaaaaaaa");
		q2.add("ddddddddddddddd");
		q2.add("bbbbbbbbbbbbbbbbbbb");
		q2.add("cccccccccccccccc");
		q2.add("z");
		q2.add("yyyyyy");
		q2.add("wwwwwwww");
		q2.add("eeeeeeeeeee");
		while(!q2.isEmpty()) { 
			System.out.println(q2.remove());
		}

	}
}

class StringLengthComparator implements Comparator<String> {
	public int compare(String s1, String s2) { 
		if(s1.length() < s2.length()) {
			// arg 22 greater than arg 1
			return -1;
		} else if(s1.length() > s2.length()) {
			// arg 1 greater than arg 2
			return 1;
		} else {
			// equals case
			return 0;
		}
	}
}