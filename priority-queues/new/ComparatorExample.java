/**
 * Comparator - Concrete Example.
 *
 * This illustrates a String comparator object
 * that allows comparing strings by length
 * rather than lexicographical order.
 */
public class StringLengthComparator 
	implements Comparator<String> {

	/** This method returns a negative if a comes before b,
	 * a positive if b comes before a, and a zero if they are equal.
	 */
	public int compare(String a, String b) { 
		if(a.length() < b.length()) { 
			return -1;
		} else if( b.length() < a.length()) { 
			return 1;
		} else {
			return 0;
		}
	}
}