import java.util.Random;

/**
 * a simple object for holding game scores.
 * @class GameEntry
 */
public class GameEntry {
	private String name;
	private int score;

	/** Construct game entry with given parameters */
	public GameEntry(String n, int s) {
		name = n;
		score = s;
	}
	/** return the name field */
	public String getName() { return name; }
	/** return the score field */
	public int getScore() { return score; }
	/** return string representation */
	public String toString() { 
		return "(" + name + ", " + score + ")";
	}

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
			
	/** main method: create 1000 random game scores */
	public static void main(String[] args) { 
		int n = 10;
		int items = 1000;
		GameEntry[] ga = new GameEntry[items];
		for(int i=0; i<items; i++) { 
			String name = randName(n);
			int score = randScore();
			GameEntry g = new GameEntry(name,score);
			ga[i] = g;
		}
		System.out.println(ga[0]);
		System.out.println(ga[10]);
		System.out.println(ga[ga.length-1]);
	}
}