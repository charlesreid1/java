import java.util.Arrays;

/**
 * Card.
 *
 * This represents the card with two characters.
 */
public class Card implements Comparable<Card> {
	protected char s, f;
	//protected final char s, f;

	/** Card object. */
	public Card(char f, char s) { 
		this.f = f;
		this.s = s;
	}
	public String toString() { 
		StringBuffer sb = new StringBuffer();
		sb.append(f);
		sb.append(s); 
		return sb.toString();
	}

	public int compareTo(Card c) { 
		return this.getFaceIndex() - c.getFaceIndex();
	}
	public char getFace() { return f; }
	public char getSuit() { return s; }
	public int getFaceIndex() { return Deck.getFaceIndex(this.f); }
	public int getSuitIndex() { return Deck.getSuitIndex(this.s); }
}
