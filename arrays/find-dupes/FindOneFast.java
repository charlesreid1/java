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


			dat = new ArrayList<Integer>();
			dat.addAll( Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,1,2,3,4,5) );
			Shuffle.shuffle(dat);
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
	public static <T> HashSet<T> findDupeFast(ArrayList<T> input) throws Empty { 
		if(input.isEmpty()) { 
			throw new Empty();
		}

		HashMap<T,Integer> hashcount = new HashMap<T,Integer>();
		HashSet<T> duplicates = new HashSet<T>();
		int n = input.size();
		for(int i = 0; i < n; i++ ) {
			T e = input.get(i);
			if( hashcount.containsKey(e) ) {
				// Have seen this before - this is the dupe we are looking for!
				duplicates.add(e);
				Integer count = hashcount.get(e);
				count += 1;
			} else {
				// Have not seen this before
				hashcount.put(e,new Integer(0));
				// this feels like being a millionaire that's super awkward getting in and out of their limo
			}
		}
		return duplicates;
	}




}

