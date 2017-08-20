import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/** Poker Hand class.
 *
 * Utility class representing a hand for 5-card poker.
 * This implements a comparator that detects
 * the following types of hands:
 *
 * High Card: Highest value card.
 * One Pair: Two cards of the same value.
 * Two Pairs: Two different pairs.
 * Three of a Kind: Three cards of the same value.
 * Straight: All cards are consecutive values.
 * Flush: All cards of the same suit.
 * Full House: Three of a kind and a pair.
 * Four of a Kind: Four cards of the same value.
 * Straight Flush: All cards are consecutive values of same suit.
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 *
 */

public class PokerHand implements Iterable, Comparable<PokerHand> { 

	public static final String[] OUTCOMES = {"high","one","two","three","straight","flush","full house","four","straight flush","royal flush"};
	public static final char[] FACE = Deck.FACE;
	public static final char[] SUIT = Deck.SUIT;
	ArrayList<Card> finallyhand;
	ArrayList<Card> hand;


	// NOTE: Coupling between poker hand and deck classes
	// is a bit awkward.


	/////////////////////////////////////
	// Utility classes


	/** Card comparator: compare cards by face value. */
	private class ValueComparator implements Comparator<Card> { 
		public int compare(Card c1, Card c2) { 
			int c1outcome = indexOf(FACE,c1.getFace());
			int c2outcome = indexOf(FACE,c2.getFace());
			// swapping the normal order so bigger cards come first
			return (c2outcome-c1outcome);
		}
	}

	/** Card comparator: compare cards by suit. */
	private class SuitComparator implements Comparator<Card> { 
		public int compare(Card c1, Card c2) { 
			int c1outcome = indexOf(SUIT,c1.getSuit());
			int c2outcome = indexOf(SUIT,c2.getSuit());
			// swapping the normal order so bigger cards come first
			return (c2outcome-c1outcome);
		}
	}




	///////////////////////////////
	// Poker hand class

	// Keep track of various quantities in the hand
	int nSuits;
	int nValues;
	int nPairs;
	int nThrees;

	/** Initialize a hand. */
	public PokerHand() { 
		hand = new ArrayList<Card>();
		finallyhand = new ArrayList<Card>();
	}

	/** Initialize a hand from another hand. */
	public PokerHand(Hand h) { 
		hand = new ArrayList<Card>();
		finallyhand = new ArrayList<Card>();
		for(Card c : h) { 
			this.add(c);
		}
	}

	/** Add a card to the hand. */
	public void add(Card k) { 
		hand.add(k);
		if(hand.size()==5) { 
			countSuits();
			countValues();
			countPairs();
			countTriplets();
		}
	}

	/** Turn this hand into a string. */
	public String toString() { 
		StringBuffer sb = new StringBuffer();
		for(Card c : hand) { 
			sb.append(c.getFace());
			sb.append(c.getSuit());
			sb.append(" ");
		}
		sb.append( OUTCOMES[this.getOutcome()] );
		return sb.toString();
	}

	/** Iterator over the cards in this hand. */
	public Iterator iterator() { 
		return hand.iterator();
	}



	/////////////////////////////////////////
	// Everything below this is dedicated
	// to comparing poker hands.



	/** Compare two PokerHand objects based on outcome, or if tie, on face value. */
	public int compareTo(PokerHand other) { 

		int hand1outcome = this.getOutcome();
		int hand2outcome = other.getOutcome();

		if(hand1outcome==hand2outcome) { 
			// Resolve by high card.
			// First, resolve by high card in finallyhand,
			// the cards that were actually important to the 
			// final outcome.
			// If those are tied, resolve by high card in hand.

			ValueComparator comp = new ValueComparator();
			Collections.sort(this.finallyhand, comp);
			Collections.sort(other.finallyhand, comp);

			Iterator<Card> carditer1 = this.finallyhand.iterator();
			Iterator<Card> carditer2 = other.finallyhand.iterator();

			// Break ties by highest finallyhand cards
			while(carditer1.hasNext() && carditer2.hasNext()) { 
				Card c1 = carditer1.next();
				Card c2 = carditer2.next();
				if(c1.getFace()!=c2.getFace()) { 
					return comp.compare(c1,c2);
				}
			}

			// If we get here, it's something like
			// two pairs that are tied.
			// Use the rest of the hand.

			carditer1 = this.hand.iterator();
			carditer2 = other.hand.iterator();

			// Break ties by highest finallyhand cards
			while(carditer1.hasNext() && carditer2.hasNext()) { 
				Card c1 = carditer1.next();
				Card c2 = carditer2.next();
				if(c1.getFace()!=c2.getFace()) { 
					return comp.compare(c1,c2);
				}
			}

			// Well sheeeyut
			return 0;

		} else {
			// swapping the normal order so bigger cards come first
			return (hand2outcome-hand1outcome);
		}

	}


	///////////////////////////////////////////////
	// Utility counting methods



	/** Count number of unique suits. */
	protected void countSuits() { 
		this.nSuits = 0;
		Set<Character> suits = new HashSet<Character>();
		for(Card c : hand) { 
			suits.add(c.getFace());
		}
		this.nSuits = suits.size();
	}

	/** Count number of unique values. */
	protected void countValues() { 
		this.nValues = 0;
		Set<Character> values = new HashSet<Character>();
		for(Card c : hand) { 
			values.add(c.getFace());
		}
		this.nValues = values.size();
	}

	/** Count pairs. */
	protected void countPairs() { 
		this.nPairs = 0;

		// Sort by face value
		ValueComparator comp = new ValueComparator();
		Collections.sort(hand, comp);

		// Count pairs, not triples
		for(int i=0; i+1<hand.size(); i++) { 
			Card ci = hand.get(i);
			Card cip1 = hand.get(i+1);
			if( ci.getFace() == cip1.getFace() ) { 
				nPairs++;
				try {
					Card cip2 = hand.get(i+2);
					if( ci.getFace() == cip2.getFace() ) { 
						nPairs--;
					}
				} catch(IndexOutOfBoundsException e){
					// its cool
				}
				// Skip new pair
				i++;
			}
		}
	}

	/** Count three of a kind occurrences. */
	protected void countTriplets() { 
		nThrees = 0;

		// Sort by face value
		ValueComparator comp = new ValueComparator();
		Collections.sort(hand, comp);

		// Count pairs, not triples
		for(int i=0; i+2<hand.size(); i++) { 
			if( hand.get(i).getFace()==hand.get(i+1).getFace() 
			&& hand.get(i).getFace()==hand.get(i+2).getFace() ) {
				nThrees++;
				try { 
					if(hand.get(i).getFace()==hand.get(i+3).getFace()) {
						nThrees--;
					}
				} catch(IndexOutOfBoundsException e){
					// its cool
				}
				// Skip new triplet
				i++;
				i++;
			}
		}
	}





	/////////////////////////////////////////////////
	// Here is the meat of the class.
	//


	/** Get string indicating outcome. */
	public int getOutcome() {
		// Check in order of rank:
		// royal flush
		if(hasRoyalFlush()) { 
			return indexOf(OUTCOMES,"royal flush");
		} else if(hasStraightFlush()) { 
			return indexOf(OUTCOMES,"straight flush");
		} else if(hasFour()) { 
			return indexOf(OUTCOMES,"four");
		} else if(hasFullHouse()) { 
			return indexOf(OUTCOMES,"full house");
		} else if(hasFlush()) { 
			return indexOf(OUTCOMES,"flush");
		} else if(hasStraight()) { 
			return indexOf(OUTCOMES,"straight");
		} else if(hasThree()) { 
			return indexOf(OUTCOMES,"three");
		} else if(hasTwo()) {
			return indexOf(OUTCOMES,"two");
		} else if(hasOne()) { 
			return indexOf(OUTCOMES,"one");
		} else {
			finallyhand = new ArrayList<Card>(hand);
			return indexOf(OUTCOMES,"high");
		}
	}



	/** Check for royal flush. */
	protected boolean hasRoyalFlush() { 
		// Sort by face value
		ValueComparator comp = new ValueComparator();
		Collections.sort(hand, comp);

		// Each card should have same suit
		char whichSuit = hand.get(0).getSuit();
		// We need one of each 
		int na = 0, nk = 0, nq = 0, nj = 0, nd = 0;
		for(Card c : hand) { 
			if(c.getSuit()!=whichSuit) return false;
			if(c.getFace()=='A') na++;
			if(c.getFace()=='K') nk++;
			if(c.getFace()=='Q') nq++;
			if(c.getFace()=='J') nj++;
			if(c.getFace()=='T') nd++;
		}
		if( na==1 && nk==1 && nq==1 && nj==1 && nd==1 ) {
			finallyhand = new ArrayList<Card>(hand);
			return true;
		} else {
			return false;
		}
	}

	/** Check for straight flush - same suit cards in order. */
	protected boolean hasStraightFlush() { 
		// If we have a straight or a flush, these will take care of finallyhand for us.
		if( hasStraight() && hasFlush() ) {
			return true;
		} else {
			return false;
		}
	}

	/** Four of a kind */
	protected boolean hasFour() { 
		if(nValues==2 && nPairs==1 && nThrees==0){

			// Only four of a kind cards should go in finallyhand
			for(int i=0; i<hand.size(); i++) { 
				int duper = 0;
				for(int j=0; j<hand.size(); j++) { 
					if(i==j) { 
						continue;
					} 
					if(hand.get(i).getFace()==hand.get(j).getFace()) {
						duper++;
					}
				}
				if(duper==4) { 
					finallyhand.add(hand.get(i));
				}
			}

			return true;
		} else {
			return false;
		}
	}

	/** Full house. */
	protected boolean hasFullHouse() { 
		if(nValues==2 && nPairs==1 && nThrees==1) {

			// Only three of a kind cards should go in finallyhand
			for(int i=0; i<hand.size(); i++) { 
				int duper = 0;
				for(int j=0; j<hand.size(); j++) { 
					if(i==j) { 
						continue;
					} 
					if(hand.get(i).getFace()==hand.get(j).getFace()) {
						duper++;
					}
				}
				if(duper==2) { 
					finallyhand.add(hand.get(i));
				}
			}

			return true;
		} else { 
			return false;
		}
	}

	/** Check for flush - matching straights. */
	protected boolean hasFlush() {
		// Add suits to a set, size should be 1
		Set<Character> suits = new HashSet<Character>();
		for(Card c : hand) { 
			suits.add(c.getSuit());
		}
		if(suits.size()==1) {
			finallyhand = new ArrayList<Card>(hand);
			return true;
		} else {
			return false; 
		}
	}

	/** Check for straight - cards in order. */
	protected boolean hasStraight() {
		// Sort by face value
		ValueComparator comp = new ValueComparator();
		Collections.sort(hand, comp);

		// This is a bit messy, but basically it checks the index of each card
		// and ensures that the next card has an index of previous - 1.
		//
		// Ace is a special case - it can either end a straight TJQKA,
		// or it can start a straight A2345.
		// We have ace hard coded in the values index at the end,
		// so it always behaves as expected with TJQKA.
		// However, if we see an ace at the beginning, 
		// we have to check if it's an ok start.

		int priorCardIndex, thisCardIndex;
		
		Iterator<Card> iter = hand.iterator();
		Card mycard = iter.next();

		//// This might be an issue - straights that start with ace.
		//if(mycard.getFace()=='A') {
		//	priorCardIndex = -1;
		//} else {
		//}

		priorCardIndex = indexOf(FACE, mycard.getFace());
		while(iter.hasNext()) { 
			mycard = iter.next();
			thisCardIndex = indexOf(FACE, mycard.getFace());
			if( thisCardIndex != (priorCardIndex-1) ) {
				return false;
			}
			priorCardIndex = thisCardIndex;
		}
		finallyhand = new ArrayList<Card>(hand);
		return true;
	}

	/** Check for three pairs */
	protected boolean hasThree() { 
		if(nThrees>0) {

			// Only three of a kind cards should go in finallyhand
			for(int i=0; i<hand.size(); i++) { 
				int duper = 0;
				for(int j=0; j<hand.size(); j++) { 
					if(i==j) { 
						continue;
					} 
					if(hand.get(i).getFace()==hand.get(j).getFace()) {
						duper++;
					}
				}
				if(duper==2) { 
					finallyhand.add(hand.get(i));
				}
			}

			return true;
		} else { 
			return false;
		}
	}

	/** Check for two pairs */
	protected boolean hasTwo() { 
		if(nPairs==2) { 

			// Only pair cards should go in finallyhand
			for(int i=0; i<hand.size(); i++) { 
				int duper = 0;
				for(int j=0; j<hand.size(); j++) { 
					if(i==j) { 
						continue;
					} 
					if(hand.get(i).getFace()==hand.get(j).getFace()){
						duper++;
					}
				}
				if(duper==1) { 
					finallyhand.add(hand.get(i));
				}
			}

			return true;
		} else {
			return false;
		} 
	}

	/** Check for one pair */
	protected boolean hasOne() { 
		if(nPairs==1) { 

			// Only pair cards should go in finallyhand
			for(int i=0; i<hand.size(); i++) { 
				int duper = 0;
				for(int j=0; j<hand.size(); j++) { 
					if(i==j) { 
						continue;
					} 
					if(hand.get(i).getFace()==hand.get(j).getFace()) {
						duper++;
					}
				}
				if(duper==1) { 
					finallyhand.add(hand.get(i));
				}
			}

			return true;
		} else {
			return false;
		}
	}




	// Done.
	////////////////////////////////////////



	public int indexOf(char[] arr, char target) {
		for(int i=0; i<arr.length; i++) { 
			if(arr[i]==target) { 
				return i;
			}
		}
		return -1;
	}
	public int indexOf(String[] arr, String target) {
		for(int i=0; i<arr.length; i++) { 
			if(arr[i].equals(target)) { 
				return i;
			}
		}
		return -1;
	}
}

/** Implement a Poker Hand comparator object. 
 *
 * This utilizes all of the work we did above
 * by just calling compareTo. 
 */
class PokerCompare implements Comparator<PokerHand> {
	public PokerCompare() {}
	public int compare(PokerHand hand1, PokerHand hand2) {
		return hand1.compareTo(hand2);
	}
}
