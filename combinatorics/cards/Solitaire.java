public class Solitaire {

	public static void main(String[] args) { 
		countPiles();
	}

	/** Count number of piles formed by increasing runs. */
	public static void countPiles() { 
		Deck d = new Deck();
		for(int c=0; c<3; c++) { 
			int aces = 0;
			int MAX = 1000000;
			for(int i=0; i<MAX; i++) {
				d.shuffle();
				h = d.hand(5);
				if(h.hasFace('A')) { 
					aces++;
				}
			}
			System.out.printf("N_aces / N_cards = %d / %d = %.4f \n",aces,MAX,(1.0*aces)/MAX);
		}
	}

}