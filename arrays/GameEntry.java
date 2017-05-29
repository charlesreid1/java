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

	/** main method: create 1000 random game scores */
	public static void main(String[] args) { 
		int n = 10;
		int items = 1000;
		GameEntry[] ga = new GameEntry[items];
		for(int i=0; i<items; i++) { 
			String name = Player.randName(n);
			int score = Player.randScore();
			GameEntry g = new GameEntry(name,score);
			ga[i] = g;
		}
		System.out.println(ga[0]);
		System.out.println(ga[10]);
		System.out.println(ga[ga.length-1]);
	}
}