import java.util.*;
import java.io.*;

public class CharDigit { 
	public static void main(String[] args) { 

		///////////////////////////////////////////////////////////
		// Create a container of digit-characters
		String n = "2432902008176640000";
		char[] narr = n.toCharArray();
		
		
		
		///////////////////////////////////////////////////////////
		// Populate a queue with characters
		Queue<Character> q = new LinkedList<Character>();
		for(int i=0; i<narr.length; i++) { 
		    q.add(narr[i]);
		}
		
		
		///////////////////////////////////////////////////////////
		// Print out the square of each character interpreted as a digit
		StringBuilder sb = new StringBuilder();

		int j = 0;
		for(Character c : q) {
		    int z = -10000;
			// Character.isDigit() and Character.digit() are static methods 
		    if( Character.isDigit(c) ) { 
		        z = Character.digit(c,10);
				sb.append(z*z);
				if(j<q.size()-1) {
		    	    sb.append(", ");
		    	}
		    } else {
		        throw new IllegalArgumentException("Malformed input. Numeric strings only.");
		    }
		}


		System.out.println(sb.toString());
	}
}