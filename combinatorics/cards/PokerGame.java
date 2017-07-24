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
		testFullHouse();
	}


	public static void testFullHouse() {
		Hand h = new Hand(5);
		h.add(new Card('2','H'));
		h.add(new Card('2','S'));
		h.add(new Card('2','D'));
		h.add(new Card('2','C'));
		h.add(new Card('A','D'));
		PokerHand p = new PokerHand(h);
		System.out.println(p);
		System.out.println( Arrays.asList(PokerHand.OUTCOMES).indexOf(   p.getOutcome()   ));
	}

	public static void countFullHouse() { 
		Deck d = new Deck();
		String label = "four";
		int fh = Arrays.asList(PokerHand.OUTCOMES).indexOf(label);
		for(int c=0; c<3; c++) { 
			int M = 100000;
			int fhcount = 0;
			for(int i=0; i<M; i++) { 
				PokerHand h = new PokerHand(d.hand(5));
				if(i%10000==0) {
					System.out.println(h);
				}
				if(h.getOutcome()==fh) {
					fhcount++;
				}
			}
			System.out.printf("Player 1 got %s %d / %d = %.8f \n",label,fhcount,M,(1.0*fhcount)/M);

		}
	}


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

