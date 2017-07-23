import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * A hand stores Card objects.
 *
 * The Deck manages all of the cards,
 * the hand is just an array of pointers
 * to Card objects.
 */
public class Hand implements Iterable<Card> {

	// The list of five Cards that are dealt to a given player
	List<Card> hand; 

	// Final cards that matter to the outcome of the game
	List<Card> finallyhand; 

	int top = 0;
	int size;

	/** Constructor just takes a size - add cards using the add() method. */
	public Hand(int size) { 
		this.size = size;
		hand = new ArrayList<Card>();
		finallyhand = new ArrayList<Card>();
	}

	public int size() { return this.size; }

	/** Boolean: does this hand contain a card of this suit? */
	public boolean hasSuit(char f) { 
		for(int i=0; i<hand.size(); i++) { 
			if(hand.get(i).getFace()==f) { 
				return true;
			}
		}
		return false;
	}

	/** Boolean: does this hand contain a card of this face value? */
	public boolean hasFace(char f) { 
		for(int i=0; i<hand.size(); i++) { 
			if(hand.get(i).getFace()==f) { 
				return true;
			}
		}
		return false;
	}

	/** Integer: count the number of occurrences of this suit in the hand. */
	public int countSuit(char s) { 
		int count = 0;
		for(int i=0; i<hand.size(); i++) { 
			if(hand.get(i).getSuit()==s) { 
				count++;
			}
		}
		return count;
	}

	/** Integer: count the number of occurrences of this face value in the hand. */
	public int countFace(char f) { 
		int count = 0;
		for(int i=0; i<hand.size(); i++) { 
			if(hand.get(i).getFace()==f) { 
				count++;
			}
		}
		return count;
	}

	/** String representation of this hand. */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("< ");
		for(int i=0; i<hand.size(); i++) { 
			sb.append(hand.get(i));
			if(i!=hand.size()-1) { 
				sb.append(", ");
			}
		}
		sb.append(" >");
		return sb.toString();
	}

	/** Add a card to this hand. */
	public void add(Card k) { 
		hand.add(k);
	}

	public Iterator iterator() {
		return hand.iterator();
	}
}
