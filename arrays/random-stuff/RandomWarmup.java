import java.util.*;
import java.io.*;

public class RandomWarmup {
	private static final int MAX = 500;

	public static void main(String[] args) { 
		// Make two shuffled arrays,
		// check for equality before and after sorting.
		
		int n = 10;
		ArrayList<Integer> a1 = makeRandomArray(n);
		System.out.println(a1);
	}

	public static ArrayList<Integer> makeRandomArray(int n) {
		Random rand = new Random();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i=0; i<n; i++) { 
			arr.add(rand.nextInt(MAX));
		}
		return arr;
	}
}
