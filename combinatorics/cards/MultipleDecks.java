import java.util.Arrays;

/**
 * Multi-Deck Poker Games.
 *
 * This investigates asymptotic behavior of 
 * poker hand probabilities as the number 
 * of decks increases.
 */

public class MultipleDecks { 

	public static void main(String[] args) { 
		countMulti();
	}


	/** Test: count the number of occurrences of a particular hand. */
	public static void countMulti() { 

		String label = "two";
		int fh = Arrays.asList(PokerHand.OUTCOMES).indexOf(label);

		// Double the size of the deck each time.
		for(int ds=1; ds<=32; ds*=2) { 

			System.out.printf("Using %d decks...\n",ds);
			for(int c=0; c<5; c++) { 
				Deck d = new Deck(ds);
				int M = 1000000;
				int fhcount = 0;
				for(int i=0; i<M; i++) { 
					d.shuffle();
					PokerHand h = new PokerHand(d.hand(5));
					if(h.getOutcome()==fh) {
						fhcount++;
					}
				}
				System.out.printf("Player 1 got %s %d / %d = %.8f \n",label,fhcount,M,(1.0*fhcount)/M);
			}
			System.out.println("\n");
		}
	}

}