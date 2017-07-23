/**
 * A hand stores Card objects.
 *
 * The Deck manages all of the cards,
 * the hand is just an array of pointers
 * to Card objects.
 */
public class Hand {
	Card[] hand;
	int top = 0;
	int size;

	/** Constructor just takes a size - add cards using the add() method. */
	public Hand(int size) { 
		this.size = size;
		hand = new Card[size];
	}

	public int size() { return this.size; }

	/** Boolean: does this hand contain a card of this suit? */
	public boolean hasSuit(char f) { 
		for(int i=0; i<hand.length; i++) { 
			if(hand[i].getFace()==f) { 
				return true;
			}
		}
		return false;
	}

	/** Boolean: does this hand contain a card of this face value? */
	public boolean hasFace(char f) { 
		for(int i=0; i<hand.length; i++) { 
			if(hand[i].getFace()==f) { 
				return true;
			}
		}
		return false;
	}

	/** Integer: count the number of occurrences of this suit in the hand. */
	public int countSuit(char s) { 
		int count = 0;
		for(int i=0; i<hand.length; i++) { 
			if(hand[i].getSuit()==s) { 
				count++;
			}
		}
		return count;
	}

	/** Integer: count the number of occurrences of this face value in the hand. */
	public int countFace(char f) { 
		int count = 0;
		for(int i=0; i<hand.length; i++) { 
			if(hand[i].getFace()==f) { 
				count++;
			}
		}
		return count;
	}

	/** String representation of this hand. */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("< ");
		for(int i=0; i<hand.length; i++) { 
			sb.append(hand[i]);
			if(i!=hand.length-1) { 
				sb.append(", ");
			}
		}
		sb.append(" >");
		return sb.toString();
	}

	/** Add a card to this hand. */
	public void add(Card k) { 
		if(top<size) { 
			hand[top] = k;
			top++;
		} else {
			throw new IllegalStateException("Full hand of size "+this.size);
		}
	}
}
