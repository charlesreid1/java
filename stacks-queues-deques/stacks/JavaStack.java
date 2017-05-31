import java.util.*;
import java.io.*;

/** Class to test the Java Stack ADT */
public class JavaStack {
	public static void main(String[] args) { 
		// Create an empty stack
		Stack<Integer> st = new Stack<Integer>();

		// Populate the stack with integers from a file
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		while(s.hasNextInt()) { 
			st.push(s.nextInt());
		}

		// Print info
		System.out.println("Size of stack: "+st.size());
	}
}