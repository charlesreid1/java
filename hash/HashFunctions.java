import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class HashFunctions {

	public static void main(String[] args) throws FileNotFoundException { 
		manyCollisions();
	}

	public static void shortTest() {
		System.out.println("AAA");
		System.out.println(hashCodeLoud("AAA"));
	}


	public static void manyCollisions() throws FileNotFoundException { 
		Scanner s = new Scanner(new BufferedReader(new FileReader("14oxenofthesun.dat")));

		// Populate word list
		Set<String> wordset = new HashSet<>();
		while(s.hasNext()) { 
			wordset.add(s.next());
		}

		for(int shift=0; shift<32; shift++) { 

			List<Integer> list = new ArrayList<>();
			Set<Integer> set = new HashSet<>();
			for(String word : wordset) { 
				int next = paramHashCode(word,shift);
				list.add(next);
				set.add(next);
			}
			int diff = list.size() - set.size();
			System.out.printf("Shift: %d \tHash codes: %d\tCollisions: %d\n", shift, list.size(), diff);
		}

	}

	public static int paramHashCode(String s, int shift) { 
		int h = 0;
		for(int i=0; i<s.length(); i++) { 
			h = (h<<shift) | (h>>>(32-shift));
			h += (int)(s.charAt(i));
		}
		return h;
	}


	public static void countCollisions() throws FileNotFoundException { 
		Scanner s = new Scanner(new BufferedReader(new FileReader("14oxenofthesun.dat")));

		// Populate word list
		Set<String> wordset = new HashSet<>();
		while(s.hasNext()) { 
			wordset.add(s.next());
		}

		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for(String word : wordset) { 
			int next = hashCode(word);
			list.add(next);
			set.add(next);
		}
		int diff = list.size() - set.size();
		System.out.printf("Out of %d unique words/hash codes, found %d duplicates.\n", wordset.size(), list.size(), diff);
	}

	public static int hashCodeLoud(String s) { 
		int h = 0;
		for(int i=0; i<s.length(); i++) { 
			System.out.print(h);
			h = (h<<5) | (h>>>27);
			System.out.print(" --> ");
			System.out.print(h);
			h += (int)(s.charAt(i));
			System.out.print(" --> ");
			System.out.println(h);
		}
		System.out.println("Finally: "+h);
		return h;
	}

	public static int hashCode(String s) { 
		int shift = 5;
		int h = 0;
		for(int i=0; i<s.length(); i++) { 
			h = (h<<(shift)) | (h>>>(32-shift));
			h += (int)(s.charAt(i));
		}
		return h;
	}
}