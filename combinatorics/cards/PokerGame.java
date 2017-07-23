import java.util.*;
import java.io.*;

/** PokerGame class.
 *
 * Utilize a Deck to deal hands for 
 * games of poker and count outcomes.
 */

public class PokerGame { 

	public static void main(String[] args) {
		test();
	}

	//////////////////////////////////////
	// Tests 

	public static void test() { 
		Deck d = new Deck();

		for(int c=0; c<3; c++) { 

			int M = 1000000;
			int player1 = 0;
			for(int i=0; i<M; i++) { 
				Hand h;
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

