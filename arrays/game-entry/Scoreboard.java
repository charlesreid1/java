/** class for storing high scores in an array, descending order */
public class Scoreboard {
	private int numEntries = 0;
	private GameEntry[] board;

	/** make empty scoreboard with given capacity */
	public Scoreboard(int capacity) { 
		board = new GameEntry[capacity];
	}

	/** add a GameEntry to the Scoreboard */
	public void add(GameEntry g) {
		int newScore = g.getScore();

		// check if there is room on the scoreboard,
		// and if not, if this is a new high score
		if(numEntries < board.length || newScore > board[numEntries-1].getScore()) { 
			if(numEntries < board.length) {
				numEntries++;
			}

			// now keep shifting scores to the right 
			// to make room for the new entry
			int j = numEntries - 1; // start at the back
			// go til you are at the front,
			// or until the next score is higher
			while(j>0 && board[j-1].getScore() < newScore) {
				// Neighbor is a lower score.
				// Keep moving this score up.
				board[j] = board[j-1];
				j--;
			}
			board[j] = g;
		}
	}


	public String toString() {
		StringBuffer b = new StringBuffer();
		for(GameEntry g : board) {
			b.append(g.toString()+"\n");
		}
		return b.toString();
	}




	/** main method: create 1000 random game scores */
	public static void main(String[] args) { 
		Scoreboard sb = new Scoreboard(10);

		int n = 10;
		int items = 1000;
		GameEntry[] ga = new GameEntry[items];
		for(int i=0; i<items; i++) { 
			String name = Player.randName(n);
			int score = Player.randScore();
			GameEntry g = new GameEntry(name,score);

			sb.add(g);
		}
		
		System.out.println(sb);
	}
}