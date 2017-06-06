import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

class Empty extends IndexOutOfBoundsException {}

public class FindOneFast {

	public static void main(String[] args) { 

		ArrayList<Integer> dat;


		// ArrayList<Integer> dat = Arrays.asList(1,2,3,4,5,6,7,8,9,4);
		// actually, that isn't gonna work.
		// also, neither is <T> in a static context.
		// Also, static references ignore type T

		// This uses generics and does things "the right way."
		// But Java sure makes it a pain in the rear to do.
		// No way to initialize with one-liners, convert PT to wrappers,
		// switch PT[] to Object[], deal with T[], or anything.
		//
		// You will become hopelessly mired in type nonsense,
		// to the point where writing and maintaining code 
		// is O(N^2) whatever else you might run.
		//
		// There you go... Java in a Nutshell.
		//
		System.out.println("Testing findDupe()");
		try {
			// Should print
			// 4
			// 1
			// 0
			dat = new ArrayList<Integer>();
			dat.addAll( Arrays.asList(1,2,3,4,5,6,7,8,9,4) );
			Shuffle.shuffle(dat);
			System.out.println(findDupeFast(dat));

			dat = new ArrayList<Integer>();
			dat.addAll( Arrays.asList(1,1) );
			Shuffle.shuffle(dat);
			System.out.println(findDupeFast(dat));

			dat = new ArrayList<Integer>();
			dat.addAll( Arrays.asList(1) );
			Shuffle.shuffle(dat);
			System.out.println(findDupeFast(dat));

			dat = new ArrayList<Integer>();
			for(int i=0; i<10000; i++) { 
				dat.add(new Integer(i));
			}
			dat.add(new Integer(435));
			System.out.println(findDupeFast(dat));

		} catch(Empty e) { 
			System.out.println("Caught unexpected empty error.");
		}


		try {
			dat = new ArrayList<Integer>();
			Shuffle.shuffle(dat);
			System.out.println(findDupeFast(dat));
		} catch(Empty e) { 
			System.out.println("Caught an expected empty error.");
		}
	}





	/** Find duplicates in an unsorted array using HashMap (general approach).
	 *
	 * This uses a HashMap (underlying, hash table) to store element values by value.
	 * This is O(1) but expensive memory-wise, and is a good general approach.
	 * The special case can be done with a pre-allocated array, faster.
	 * */
	public static HashSet<Integer> findDupe(ArrayList<Integer> input) throws Empty { 
		if(input.isEmpty()) { 
			throw new Empty();
		}

		HashMap<Integer,Integer> hashcount = new HashMap<Integer,Integer>();
		HashSet<Integer> duplicates = new HashSet<Integer>();
		int n = input.size();
		for(int i = 0; i < n; i++ ) {
			Integer e = input.get(i);
			if( hashcount.containsKey(e) ) {
				// Have seen this before - this is the dupe we are looking for!
				duplicates.add(e);
				Integer count = hashcount.get(e);
				count += 1;
			} else {
				// Have not seen this before
				hashcount.put(e,new Integer(0));
			}
		}
		return duplicates;
	}



	/** Find duplicates in an unsorted array using ArrayList (faster specific approach).
	 *
	 * This uses a pre-allocated array. 
	 * The integers 1 through (n-1) appear in the array, 
	 * with one element per integer, 1 through n.
	 * Indexes run from 0 through n-1.
	 * We can find duplicates in an ArrayList by using these integers.
	 * Access an underlying array of fixed size at least n-1.
	 * Counters contained in each, and if counter > 0, add item to dupes.
	 * 
	 * In reality, we leave the 0th element open.
	 */
	public static HashSet<Integer> findDupe2(int[] input) throws Empty { 
		HashSet<Integer> duplicates = new HashSet<Integer>();
		int n = input.length;

		// list of counters: fixed size n
		// accessed with index = 0 to n-1
		int[] counters = new int[n];
		// Note, if we don't follow the specs, 
		// then there will be an integer greater than its index
		// somewhere in the list, unless we increase the space above
		// or make our algorithm careless.

		// i is input iterator
		for(int i=0; i<n; i++) { 

			// input k will be 1 through n-1
			int k = input[i];

			// counters[0] is dead space
			// check if already there (counters[k] > 0)
			// if so, k is dupe
			try {
				if(counters[k] > 0) {
					duplicates.add(k);
				}
				// always increment
				counters[k]++;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Passed a malformed input.");
			}
		}

		return duplicates;
	}


	/** Find duplicates in an unsorted array using HashMap (general approach).
	 *
	 * This uses a HashMap (underlying, hash table) to store element values by value.
	 * This is O(1) but expensive memory-wise, and is a good general approach.
	 * The special case can be done with a pre-allocated array, faster.
	 * */
	public static HashSet<Integer> findDupe(ArrayList<Integer> input) throws Empty { 
		if(input.isEmpty()) { 
			throw new Empty();
		}

		HashMap<Integer,Integer> hashcount = new HashMap<Integer,Integer>();
		HashSet<Integer> duplicates = new HashSet<Integer>();
		int n = input.size();
		for(int i = 0; i < n; i++ ) {
			Integer e = input.get(i);
			if( hashcount.containsKey(e) ) {
				// Have seen this before - this is the dupe we are looking for!
				duplicates.add(e);
				Integer count = hashcount.get(e);
				count += 1;
			} else {
				// Have not seen this before
				hashcount.put(e,new Integer(0));
			}
		}
		return duplicates;
	}


}

