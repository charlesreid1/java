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
		d.shuffle();

		Hand h;
		
		h = d.hand(5);
		PokerHand h1 = new PokerHand(h);

		h = d.hand(5);
		PokerHand h2 = new PokerHand(h);

		System.out.println("Hand 1: "+h1);
		System.out.println("Hand 2: "+h2);

		System.out.println(h1.compareTo(h2));
	}
}

