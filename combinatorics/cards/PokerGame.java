import java.util.*;
import java.io.*;

/** PokerGame class.
 *
 * Driver class.
 *
 * This assembles decks and Poker Hands to 
 * count the odds of various outcomes 
 * and probabilities.
 *
 */
public class PokerGame { 

	public static void main(String[] args) {
		countHands();
	}


	/** Test: count the number of occurrences of a particular kind of hand. */
	public static void countHands() { 
		Deck d = new Deck();
		String label = "straight flush";
		int fh = Arrays.asList(PokerHand.OUTCOMES).indexOf(label);
		for(int c=0; c<5; c++) { 
			int M = 1000000;
			int fhcount = 0;
			for(int i=0; i<M; i++) { 
				d.badShuffle();
				PokerHand h = new PokerHand(d.hand(5));
				if(h.getOutcome()==fh) {
					fhcount++;
				}
			}
			System.out.printf("Player 1 got %s %d / %d = %.8f \n",label,fhcount,M,(1.0*fhcount)/M);

		}
	}




	/** Test: create a hand with four of a kind, and test that we correctly count it as four of a kind. */
	public static void testStraightFlush() {
		Hand h = new Hand(5);
		h.add(new Card('3','D'));
		h.add(new Card('4','D'));
		h.add(new Card('7','D'));
		h.add(new Card('6','D'));
		h.add(new Card('5','D'));
		PokerHand p = new PokerHand(h);
		System.out.println(p);
		System.out.println( Arrays.asList(PokerHand.OUTCOMES).indexOf(   p.getOutcome()   ));
	}




	/** Test: create a hand with four of a kind, and test that we correctly count it as four of a kind. */
	public static void testFour() {
		//// Block 1
		//Hand h = new Hand(5);
		//h.add(new Card('2','H'));
		//h.add(new Card('2','S'));
		//h.add(new Card('2','D'));
		//h.add(new Card('2','C'));
		//h.add(new Card('A','D'));
		//PokerHand p = new PokerHand(h);
		//System.out.println(p);
		//System.out.println( Arrays.asList(PokerHand.OUTCOMES).indexOf(   p.getOutcome()   ));

		// Block 2
		Hand h = new Hand(5);
		h.add(new Card('T','D'));
		h.add(new Card('T','C'));
		h.add(new Card('T','H'));
		h.add(new Card('8','C'));
		h.add(new Card('8','D'));
		PokerHand p = new PokerHand(h);
		System.out.println(p);
		System.out.println( Arrays.asList(PokerHand.OUTCOMES).indexOf(   p.getOutcome()   ));
	}


	/** Test: create a deck, generate poker hands, and count who wins how often. */
	public static void test() { 
		Deck d = new Deck();

		for(int c=0; c<3; c++) { 

			int M = 1000000;
			int player1 = 0;
			for(int i=0; i<M; i++) { 
				d.shuffle();
				PokerHand h1 = new PokerHand(d.hand(5));
				PokerHand h2 = new PokerHand(d.hand(5));
				if(h1.compareTo(h2)>0) { 
					player1++;
				}
			}
			System.out.printf("Result of experiment: Player 1 wins %d / %d = %.5f \n",player1,M,(1.0*player1)/M);

		}
	}
}

