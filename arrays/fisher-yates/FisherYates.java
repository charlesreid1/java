import java.io.*;
import java.util.*;

/**
 * Fisher Yates Shuffle class.
 *
 * This class implements the Fisher-Yates shuffle
 * as a static method.
 *
 * This accepts an array as input, and shuffles it in-place.
 */
public class FisherYates {

	/** swap two "Objects" */
	private static void swap(Object[] arr, int x, int y){
		if(x != y) {
			Object temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}
	}

	/**
	 * Fisher Yates shuffle : this is getting complicated. 
	 * How to implement this with arrays of generic types?
	 */
	public static void shuffleFY(String[] inputs) {
		Random r = new Random();
		int n = inputs.length;
		for(int j=n-1; j>0; j--) {
			// don't want to do the case of j==0.
			// if you do, you have have a 1 in n chance of un-sorting,
			// or un-doing or leaving out two pairs from the shuffle. 
			//
			// Fisher Yates shuffle:
			//   j starts at n-1
			//   swap with random element between 0 and j (INCLUSIVE)
			int k = r.nextInt(j+1);
			swap(inputs, j, k);
		}
	}

	public static void main(String[] args) { 
		Deck d = new Deck();
		System.out.println(d);
		System.out.println("-------------------");
		shuffleFY(d.toArray());
		System.out.println(d);
	}

}

/** Hope you like cards.
 *
 * This implements a standard deck of 52 cards.
 */
class Deck {
	private String[] deck;

	// define how your cards work
	private final int capacity = 52;
	private final String[] SUITS = {"SPADES","CLUBS","HEARTS","DIAMONDS"};
	private final String[] FACES = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

	/** constructor don't need no arguments */
	public Deck(){
		deck = new String[capacity];

		int card = 0;
		for(String suit : SUITS) { 
			for(String face : FACES) { 
				deck[card] = face + " of " + suit;
				card++;
			}
		}
	}

	/** turn into string array */
	public String[] toArray() { return deck; }

	/** turn into string */
	public String toString() {
		StringBuffer s = new StringBuffer();
		for(String this_card : deck) { 
			s.append(this_card+"\n");
		}
		return s.toString();
	}
}



