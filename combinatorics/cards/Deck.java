import java.util.Iterator;
import java.util.Random;
import java.util.Arrays;

/**
 * Card Deck.
 *
 * A deck of Card objects.
 */
public class Deck implements Iterable<Card> {

	public final char[] SUIT = {'S','C','D','H'};
	public final char[] FACE = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};

	protected Card[] deck;
	protected int NCARDS = 52;
	protected Random r;
	int top;

	public static void main(String[] args) {
		//testShuffle();
		testIterator();
	}

	/** Test the shuffle method. */
	public static void testShuffle() { 
		Deck d = new Deck();
		System.out.println(d);
		System.out.println("\nShuffling...\n");
		d.shuffle();
		System.out.println(d);
	}

	/** Test the iterator functionality. */
	public static void testIterator() {
		// Test deck iterator
		System.out.println("Testing foreach...");
		int i = 0;
		Deck d = new Deck();
		for(Card c : d) { 
			System.out.println(c);
			if(i>10) { break; }
			i++;
		}
	}


	/** A deck of cards. */
	public Deck() {
		top = 0;
		r = new Random();
		deck = new Card[NCARDS];

		for(int f=0; f<FACE.length; f++) { 
			//System.out.println("f = "+f);
			for(int s=0; s<SUIT.length; s++) { 
				Card c = new Card(FACE[f],SUIT[s]);

				// This is a bit tricky: face index * SUIT length, not times face length.
				deck[f*SUIT.length+s] = c;
			}
		}
	}

	public Iterator iterator() {
		Iterator i = new DeckIterator();
		return i;
	}

	protected class DeckIterator implements Iterator {
		int index;
		public DeckIterator() { 
			index = 0;
		}
		public boolean hasNext() { 
			return index<(deck.length-1);
		}
		public Card next() {
			Card result = deck[index];
			index++;
			return result;
		}
	}

	/** String representation of card deck. */
	public String toString() {
		return Arrays.toString(deck);
	}

	/** Shuffle the array with a Knuth shuffle. */
	public void shuffle() {
		top = 0;
		int j;
		// Another bit of trickiness - should be i > 0, 
		// remember to skip the 0th item.
		for(int i=deck.length-1; i>0; i--) { 
			j = r.nextInt(i);
			swap(deck,i,j);
		}
	}

	/** Swap two items. */
	public <T> void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public Hand hand(int n) { 
		Hand h = new Hand(5);
		for(int i=0; i<5; i++) { 
			h.add(deck[top]);
			top = (top+1)%(deck.length);
		}
		return h;
	}
}
