import java.util.*;
import java.io.*;

/**
 * makin copies, demo of copy behavior/best practices with arrays.
 */
public class MakinCopies {

	public static String randomString() { 
		Random r = new Random();
		int n = 5;
		char[] ret = new char[n];
		for(int i=0; i<n; i++){
			ret[i] = (char)('A' + r.nextInt(26));
		}
		return new String(ret);
	}

	public static String[] makeRandomArray(int n) { 
		Random r = new Random();
		String[] ret = new String[n];
		for(int i=0; i<n; i++) { 
			ret[i] = randomString();
		}
		return ret;
	}

	public static void main(String[] args) { 
		int ns = 3;
		String[] array1 = makeRandomArray(ns);
		String[] array2 = array1;

		System.out.println("The object hashes these variables point to are identical:");
		System.out.println("array1 object hash: " + array1);
		System.out.println("array2 object hash: " + array2);

		System.out.println();

		// The cool, hip way to do it: clone()
		String[] array3 = array1.clone();

		//// The dorky indexed way
		//String[] array4 = Arrays.copyOf(array1, array1.length);

		System.out.println("Use Arrays.copyOf(arr, arr.length) to make a new copy in memory:");
		System.out.println("array1 object hash: " + array1);
		System.out.println("array3 object hash: " + array3);

		System.out.println();

		System.out.println("array1 and array3 point to different objects.");
		array1[0] = "sprinkles".toUpperCase();
		array3[0] = "moondust".toUpperCase();
		System.out.println( Arrays.toString(array2) );
		System.out.println( Arrays.toString(array3) );

	}
}