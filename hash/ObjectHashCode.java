import java.util.*;
import java.io.*;

/** Object Hash Code demo class.
 *
 * This modifies the method by which each Word object is hashed.
 */
public class ObjectHashCode {

	/** Run the driver method. */
	public static void main(String[] args) throws FileNotFoundException { 
		populateWords();
	}


   	/** Populate words, then check for hash code collisions. */
   	public static void populateWords() throws FileNotFoundException { 
   		Scanner s = new Scanner(new BufferedReader(new FileReader("14oxenofthesun.dat")));

   		// create list of unique words as a string set
   		Set<String> strings = new HashSet<String>();
   		Set<Word> words = new HashSet<Word>();
   		while(s.hasNext()) { 
   			String word = s.next();
   			strings.add(word);
   			words.add(new Word(word));
   		}

   		// count number of hash collisions for:
   		// strings
   		List<Integer> stringHashes = new LinkedList<Integer>();
   		Set<Integer> uniqStringHashes = new HashSet<Integer>();
   		// words
   		List<Integer> wordHashes = new LinkedList<Integer>();
   		Set<Integer> uniqWordHashes = new HashSet<Integer>();

   		for(String st : strings) { 
   			stringHashes.add( st.hashCode() );
   			uniqStringHashes.add( st.hashCode() );
		}

   		for(Word w : words) {
   			wordHashes.add( w.hashCode() );
   			uniqWordHashes.add( w.hashCode() );
   		}

   		int stringCollisions = stringHashes.size() - uniqStringHashes.size();
   		int wordCollisions = wordHashes.size() - uniqWordHashes.size();

   		System.out.printf("String class hash code had %d strings, %d collisions.\n", stringHashes.size(), stringCollisions);
   		System.out.printf("Word class hash code had %d strings, %d collisions.\n",   wordHashes.size(),   wordCollisions);
   	}
}

/** Word class.
 * We modify the hash function of the Word class.
 */
class Word {
	private String data;
	public Word(String s) { 
		data = s;
	}
	public String getData() { return data; }
	public String toString() { return data; }

	@Override
	public boolean equals(Object w) { 
		// See https://stackoverflow.com/questions/15722485/hashset-storing-equal-objects#15722565
		//
		// Verify this is a Word object
		if(!(w instanceof Word)) { 
			return false;
		}
		// Cast as Word object
		Word ww = (Word) w; 
		// Access Word attributes
		if(data.equals(ww.data)) {
			return true;
		} else { 
			return false;
		}
	}

	@Override
	public int hashCode() { 
		int h = 1;
		int shift = 5;
		for(int i=0; i<data.length(); i++) { 
			h ^= (int)(data.charAt(i)); // bitwise xor with h
			h = (h << shift) | (h >>> (32-shift)); // n-bit cyclic shift of h 
		}
		return h;
	}
}

