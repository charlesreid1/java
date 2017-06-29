import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class BuiltinHashFunction {
	public static void main(String[] args) throws FileNotFoundException { 
		//shortTest();
		manyCollisions();
	}

	
	public static void manyCollisions() throws FileNotFoundException { 

		Scanner s = new Scanner(new BufferedReader(new FileReader("14oxenofthesun.txt")));

		// Populate word list
		Set<String> wordset = new HashSet<>();
		while(s.hasNext()) { 
			wordset.add(s.next());
		}

		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for(String word : wordset) { 
			int next = hash(word.hashCode());
			list.add(next);
			set.add(next);
		}
		int diff = list.size() - set.size();
		System.out.printf("Builtin Hash Function: \tHash codes: %d\t\tCollisions: %d\n", list.size(), diff);
	}



	public static void shortTest() { 
		int n = 100;
		for(int i=0; i<n; i++) {
			String s = randomString();
			System.out.println(s);
			System.out.println(s.hashCode());
			System.out.println(hash(s.hashCode()));
			System.out.println();
		}
	}

	public static String randomString() { 
		Random r = new Random();
		char[] rand = new char[2+r.nextInt(7)];
		for(int i=0; i<rand.length; i++) { 
			rand[i] = (char)('A' + r.nextInt(26));
		}
		return new String(rand);
	}

    public static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

}
