import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.Arrays;

/**
 * Card Deck.
 *
 * A deck of Card objects.
 */
public class Deck implements Iterable<Card> {

	public static final char[] SUIT = {'S','C','D','H'};
	public static final char[] FACE = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};

	protected List<Card> elements;
	protected Card[] deck;
	protected int NCARDS = 52;
	protected Random r;
	int top;


	//////////////////////////////
	// Tests

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




	//////////////////////////////////////////
	// Deck class

	/** A deck of cards. */
	public Deck() {
		top = 0;
		r = new Random();
		elements = new ArrayList<Card>();

		for(int f=0; f<FACE.length; f++) { 
			//System.out.println("f = "+f);
			for(int s=0; s<SUIT.length; s++) { 
				Card c = new Card(FACE[f],SUIT[s]);
				elements.add(c);
			}
		}
	}


	public Iterator<Card> iterator() {
		return elements.iterator();
	}


	///// //////////////////////
	///// // AAARRRRRRRGGGG
	///// public Iterator<Card> iterator() {
	///// 	DeckIterator<Card> i = new DeckIterator<Card>();
	///// 	return (Iterator<Card>)(i);
	///// }

	///// protected class DeckIterator<Card> implements Iterator<Card> {
	///// 	int index;
	///// 	public DeckIterator() { 
	///// 		index = 0;
	///// 	}
	///// 	public boolean hasNext() { 
	///// 		return index<(elements.size()-1);
	///// 	}
	///// 	public Card next() {
	///// 		Card result = elements.get(index);
	///// 		index++;
	///// 		return result;
	///// 	}
	///// }

	/** String representation of card deck. */
	public String toString() {
		return elements.toString();
	}

	/** Shuffle the array with a Knuth shuffle. */
	public void shuffle() {
		top = 0;
		int j;
		// Another bit of trickiness - should be i > 0, 
		// remember to skip the 0th item.
		for(int i=elements.size()-1; i>0; i--) { 
			j = r.nextInt(i);
			swap(elements,i,j);
		}
	}

	/** Swap two items. */
	public <T> void swap(List<T> arr, int i, int j) {
		T temp = arr.get(i);
		arr.set(i,arr.get(j));
		arr.set(j,temp);
	}

	/** Create a new hand using cards from this deck. */
	public Hand hand(int n) { 
		Hand h = new Hand(n);
		for(int i=0; i<n; i++) { 
			h.add(elements.get(top));
			// Loop the counter back to the beginning
			top = (top+1)%(elements.size());
		}
		return h;
	}
}
