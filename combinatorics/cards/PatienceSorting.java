import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/** Solitaire: Count Runs.
 *
 * Count the runs (sequences of increasing cards).
 */
public class Solitaire {


	public static void main(String[] args) { 
		countPiles();
	}


	/** Face Comparator: compares cards by face, then suit.
	 * */
	public static class FaceFirstComparator implements Comparator<Card> {
		public FaceFirstComparator() { }
		public int compare(Card left, Card right) {
			return ( left.getFaceIndex()==right.getFaceIndex() )?( left.getSuitIndex() - right.getSuitIndex() ):( left.getFaceIndex() - right.getFaceIndex() );
		}
	}


	/** Suit Comparator: compares cards by suit, then face.
	 * */
	public static class SuitFirstComparator implements Comparator<Card> {
		public SuitFirstComparator() { }
		public int compare(Card left, Card right) {
			return ( left.getSuitIndex()==right.getSuitIndex() )?( left.getFaceIndex() - right.getFaceIndex() ):( left.getSuitIndex() - right.getSuitIndex() );
		}
	}



	/** Patience sorting algorithm.
	 *
	 * Scan each card in the deck and pile them into a linear array of piles.
	 *
	 * Rule 1: Each new card is placed at the top of an existing stack,
	 * 			or it forms a new stack by itself positioned to the right of all other stacks.
	 *
	 * Rule 2: The stack on which a new card is placed is the left-most stack 
	 * 		   from among those with a top number larger than the current card.
	 * 		   If there are no such stacks, the card forms a new stack.
	 */
	public static void patienceSorting() {
	}







	public static void countPiles() { 
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		Deck d = new Deck();
		for(int c=0; c<3; c++) { 
			double avgPiles = 0;
			int MAX = 1000000;
			//SuitFirstComparator comp = new SuitFirstComparator();
			FaceFirstComparator comp = new FaceFirstComparator();
			for(int i=0; i<MAX; i++) {
				d.shuffle();
				int piles = 0;
				Iterator<Card> iter = d.iterator();
				Card priorCard = iter.next();
				Card nextCard;
				while(iter.hasNext()) { 
					nextCard = iter.next();
					if(comp.compare(priorCard,nextCard)>0) {
						piles++;
					}
					priorCard = nextCard;
				}
				avgPiles += piles;
			}
			avgPiles /= MAX;
			System.out.printf("Average Number of Piles in %d Trials: %.2f \n",MAX, avgPiles);
		}
	}

}