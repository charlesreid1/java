import java.util.Arrays;
import java.util.Random;

/**
 * Remove random items from an array, one at a time, until the array is empty.
 *
 * This class is answering Goodrich Question R-3.2,
 * and implementing a shuffle test to ensure our shuffle method is fair.
 *
 * Procedure for removing items:
 * 1. Create a String array.
 * 2. Create an indices array to generate original sequential order.
 * 3. Shuffle indices array to generate a random removal order.
 *
 * Procedure for testing shuffle method:
 * 1. Decide on a large number of trials, and a particular String in an array of Strings.
 * 2. Initialize an array to zero, with number of elements equal to number of Strings in String array.
 * 3. For each trial, re-initialize the string array (this is important), and shuffle it.
 * 4. Find the index location of your particular String in the array.
 * 5. Increment the index counter at that particular index location.
 * 6. When finished, print the value of the index location as incremented, divided by total number of trials.
 * 7. Repeat.
 *
 */
public class SequentialRemove {

	public static void main(String[] args) { 
		//shuffleTest();
		selectAndRemove();
	}

	public static void selectAndRemove() { 
		String[] a = {"A","B","C","D","E","F","G"};
		Integer[] indices = new Integer[a.length];
		
		// Start by populating an array of index values
		for(int i=0; i<indices.length; i++) { 
			indices[i] = i;
		}
		
		// Create new shuffled order
		shuffle(indices);

		// Remove each item, one at a time.
		for(int i=0; i<indices.length; i++) { 
			int rmi = indices[i];
			a[rmi] = null;
			System.out.printf("Removed item %d. New String array: %s \n", rmi, Arrays.toString(a));
		}
	}

	/** Test our shuffle method to ensure it is fair. */
	public static void shuffleTest() { 
		// Number of trials
		int t = 500000;
		int[] thereCount = new int[7];

		// Repeat this a large number of times:
		// shuffle a String array, and find where the word "there" ends up.
		for(int i=0; i<t; i++) { 
			String[] a = {"there","is","something","funny","going","on","here"};
			shuffle(a);
			for(int j=0; j<a.length; j++) { 
				thereCount[j] += a[j]=="there"?1:0;
			}
		}

		// Print this once:
		// If our shuffle method is "fair," these should all be about the same order of magnitude.
		// Note that even for a large number of trials, these probabilities won't be *that* close.
		System.out.println("Stats of \"there\" in location:");
		for(int j=0; j<thereCount.length; j++) { 
			System.out.printf("    j = %2d   freq = %.4f \n",j,thereCount[j]/(t*1.0));
		}
	}

	/** Fisher Yates shuffle method. */
	public static void shuffle(Object[] arr) {
		int n = arr.length;
		Random r = new Random();
		for(int i=(n-1); i>0; i--) { 
			int j = r.nextInt(i+1);
			swap(arr,i,j);
		}
	}

	/** Swap utility method. */
	public static void swap(Object[] data, int i, int j) { 
		Object temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

}