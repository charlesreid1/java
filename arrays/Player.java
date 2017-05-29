import java.util.Random; 

public class Player {
	/** static method: construct a random player name */
	public static String randName(int n) { 
		Random r = new Random();
		char[] ca = new char[n];
		for(int i=0; i<n; i++) { 
			ca[i] = (char)('A' + r.nextInt(26));
		}
		return new String(ca);
	}

	/** static method: construct a random player score */
	public static int randScore() { 
		Random r = new Random();
		return r.nextInt((int)(1E6));
	}
}
