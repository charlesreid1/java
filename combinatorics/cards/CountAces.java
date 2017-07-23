/**
 * Count Aces.
 *
 * This counts the number of Aces in a random poker hand.
 */

public class CountAces { 
	public static void main(String[] args) { 
		minOneAceEmpirical();
		//minOneAceTheoretical();
		exactlyOneAceEmpirical();
		//exactlyOneAceTheoretical();
	}


	/** Count the number of hands with at least one ace (empirically). */
	public static void minOneAceEmpirical() { 
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


	/** Count the number of hands with exactly one ace (empirically). */
	public static void exactlyOneAceEmpirical() { 
		final int NACES = 2;
		Deck d = new Deck();
		Hand h;
		for(int c=0; c<3; c++) { 
			int twoaces = 0;
			int MAX = 1000000;
			for(int i=0; i<MAX; i++) {
				d.shuffle();
				h = d.hand(5);
				if(h.countFace('A')==NACES) {
					twoaces++;
				}
			}
			System.out.printf("N_twoaces / N_cards = %d / %d = %.4f \n",twoaces,MAX,(1.0*twoaces)/MAX);
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
