import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Random;

class Empty extends ArrayIndexOutOfBoundsException {}
class Illegal extends IllegalArgumentException {}

public class FindOne {

	public static void main(String[] args) { 

		ArrayList<Integer> dat;


		// This is all primitive types, straight the whole way.
		// Ugly and bad practice, but the only thing that works, 
		// thanks to incomprehensible Java design decisions.
		//
		System.out.println("Testing findDupe()");

		try {
			int[] a = {1,2,3,4,5,6,7,8,9,4};
			shuffle(a);
			System.out.println(findDupe(a));

			int[] ab = {1,1};
			shuffle(ab);
			System.out.println(findDupe(ab));

			int[] ad = new int[100000];
			for(int i=1; i<10000; i++) { 
				ad[i] = i+1;
			}
			ad[0] = 435;
			shuffle(ad);
			System.out.println(findDupe(ad));

		} catch (Illegal e) { 
			System.out.println("Caught unexpected illegal exception.");
		} catch (Empty e) { 
			System.out.println("Caught unexpected empty exception.");
		}

		try {
			int[] ac = {1};
			shuffle(ac);
			System.out.println(findDupe(ac));
		} catch (Illegal e) { 
			System.out.println("Caught expected illegal exception.");
		}

		try {
			int[] ac = {1,2,3,4,5,6,7,8,9};
			shuffle(ac);
			System.out.println(findDupe(ac));
		} catch (Illegal e) { 
			System.out.println("Caught expected illegal exception.");
		}

		try {
			int[] ac = {0,88,44,92,1};
			shuffle(ac);
			System.out.println(findDupe(ac));
		} catch (Illegal e) { 
			System.out.println("Caught expected illegal exception.");
		}


	}



	/** Find duplicates in an unsorted array using an int[] (faster specific approach).
	 *
	 * This uses a pre-allocated array. 
	 * The integers 1 through (n-1) appear in the array, 
	 * with one element per integer, 1 through n.
	 * Indexes run from 0 through n-1.
	 * We can find duplicates in an int[] by using these integers.
	 * Access an array of fixed size at least n-1.
	 * Counters contained in each, and if counter > 0, add item to dupes.
	 * 
	 * In reality, we leave the 0th element open.
	 */
	public static HashSet<Integer> findDupe(int[] input) throws Empty, Illegal { 
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
				// Raised because illegal argument, i.e., a number greater than its own index
				throw new Illegal();
			}
		}

		return duplicates;
	}



	/** Shuffle an array of ints. */
	public static void shuffle(int[] inputs) { 
		Random r = new Random();
		int n = inputs.length;
		for(int j=n-1; j>0; j--) {
			int k = r.nextInt(j+1);
			swap(inputs, j, k);
		}
	}

	/** Swap two ints. */
	private static void swap(int[] arr, int x, int y){
		if(x != y) {
			int temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp; 
		}
	}

}

