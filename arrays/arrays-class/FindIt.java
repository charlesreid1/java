import java.util.Arrays;
import java.util.Random;

/**
 * Find a needle in a haystack, in O(n log n) time.
 *
 * Runs with several randomized inputs,
 * all unique elements, and outputs a csv file.
 *
 * java FindIt > data.csv
 *
 */
public class FindIt {
	
	public static int[] makeRandomArray(int n) {
		Random r = new Random();
		int[] a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = r.nextInt(n*n);
		}
		return a;
	}
	

	public static void main(String[] args) { 
		Random r = new Random();

		int[] set_n = {100,1000,10000,100000};

		System.out.printf("Index Found,N,Duration (ms)\n");
		for(int n : set_n) {
			long start = System.nanoTime();

			int[] haystack = makeRandomArray(n);
			int needle = haystack[r.nextInt(n)];
			Arrays.sort(haystack); // O(n log n) sort (returns void)
			int loc = Arrays.binarySearch(haystack,needle); // O(log n) binary search 

			long end = System.nanoTime();
			double TO_MS = 1e6;
			double duration = (end - start)/(TO_MS);

			System.out.printf("%d, %d, %.3f\n", loc, n, duration);
		}

	}
}

