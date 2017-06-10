import java.io.*;
import java.util.*;

class Oops extends ArrayIndexOutOfBoundsException {}

public class StringPermutations { 

	public static void main(String[] args) { 
		testScaling();
	}

	public static void testScaling() {
		String all = "ABCDEFGHIJKL";
		System.out.printf("N, Nperms, Walltime (ms)\n");
		for(int i=5; i<all.length()+1; i++) { 
			long start = System.nanoTime();
			int permutations = stringPermutations(all.substring(0,i));
			long stop = System.nanoTime();
			double duration = (stop - start)/1E6; // time in ms
			System.out.printf("%d, %d, %.1f\n", i, permutations, duration);
		}
	}

	/*
	public static void testOne() {
		// Start with strings, then move to generics
		String theString = "CHARLES";
		LinkedQueue<String> permutations = stringPermutations(theString);
		System.out.println("Found "+permutations.size()+" permutations");
	}
	*/

	public static void swap(char[] input, int i, int j) {
		char t = input[i];
		input[i] = input[j];
		input[j] = t;
	}

	public static void reverseFrom(char[] input, int startIndex) { 
		// prolly shoulda used a stack.

		int len = input.length;
		int n = len - startIndex - 1;
		// if n odd, ignores middle index
		// if n even, swaps exactly half
		for(int k=0; k<(n/2); k++) { 
			int ix = startIndex + k;
			int jx = len-1-k;
			swap(input,ix,jx);
		}
	}


	//public static LinkedQueue<String> stringPermutations(String s_input) { 
	public static int stringPermutations(String s_input) { 
		//LinkedQueue<String> q = new LinkedQueue<String>();
		int count = 0;

		// Save useful info
		int n = s_input.length();

		char[] input = s_input.toCharArray();
		Arrays.sort(input);

		//// First is sorted string
		//q.enqueue(new String(input));
		count++;

		boolean done = false;

		try {

			// Loop until we reach end
			while(!done) {
				// Step 1:
				// let i be the last index such that input[i] < input[i+1]
				// (if no such index, i=0 and we are done)
				int i = -1;
				for(int k=0; k<(n-1); k++) { 
					if(input[k] < input[k+1]) {
						i = k;
					}
				}
				if(i<0) {
					// No such index, we are done
					done = true;
					break;
				}

				// Step 2:
				// let j be the last index such that input[i] < input[j]
				int j = -1;
				for(int k=i+1; k<n; k++) { 
					if(input[i] < input[k]) {
						j = k;
					}
				}

				// Step 3:
				// swap i and j
				swap(input,i,j);

				// Step 4:
				// reverse i+1 thru length-1
                reverseFrom(input, i+1);

				//q.enqueue(new String(input));
				count++;
			}

		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("OOPSIE");
		}
		//return q;
		return count;
	}
}
