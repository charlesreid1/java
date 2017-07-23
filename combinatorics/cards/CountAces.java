/**
 * Count Aces.
 *
 * This counts the number of Aces in a random poker hand.
 */

public class CountAces { 
	public static void main(String[] args) { 
		countAces();
	}


	/** Count hands with aces. */
	public static void countAces() { 
		Deck d = new Deck();
		Hand h;

		for(int c=0; c<3; c++) { 

			int aces = 0;
			int MAX = 1000000;
			for(int i=0; i<MAX; i++) {
				d.shuffle();
				h = d.hand(5);
				if(h.hasFace('A')) { 
					aces++;
				}
			}
			System.out.printf("N_aces / N_cards = %d / %d = %.4f \n",aces,MAX,(1.0*aces)/MAX);
		}
	}


	/** Simple test: can we detect an ace in a hand. */
	public static void findTheAce() { 
		Deck d = new Deck();
		d.shuffle();
		Hand h = d.hand(5);
		System.out.println(h);
		System.out.println(h.hasFace('A'));
	}
}
