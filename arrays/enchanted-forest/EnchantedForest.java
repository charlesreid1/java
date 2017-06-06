class Illegal extends IllegalArgumentException{}

/** 
 * Goodrich et al, Data Structures in Java, Chapter 3: Arrays.
 *
 * Question C-3.23.
 * Suppose you are designing multiplayer game, n >= 1000 players,
 * 1 to n, interacting in an enchatnted forest.
 *
 * The winner is the first player who can meet all the other players
 * at least once. Ties allowed.
 *
 * If there is a meet(i,j) method for when player i meets player j,
 * i != j, how to keep track of pairs of meeting players
 * and who is the winner?
 */
public class EnchantedForest { 
	int[][] meetMatrix;
	int n;
	
	public EnchantedForest(int n) throws Illegal { 
		if(n<=0) { 
			throw new Illegal();
		}
		meetMatrix = new int[n][n];
		this.n = n;
	}

	public void meet(int i, int j) throws Illegal { 
		// i and j are integers starting at 1 and going to n
		if(i!=j && i>0 && i<=n && j>0 && j<=n) {
			meetMatrix[i-1][j-1] = 1; // don't increment, or you'll throw off the count!
			meetMatrix[j-1][i-1] = 1;
		} else {
			throw new Illegal();
		}
	}

	public void findWinners() { 
		// marking matrix symmetrically, so either row or column search
		for(int i=0; i<n; i++) { 

			int sum = 0;
			for(int j=0; j<n; j++) { 
				sum += meetMatrix[i][j];
			}
			if(sum==(n-1)) { 
				System.out.println("Winner: Player "+(i+1));
			}

		}
	}


	public static void main(String[] args) throws Illegal { 
		EnchantedForest ef = new EnchantedForest(5);
		ef.meet(1,2);
		ef.meet(1,3);
		ef.meet(1,4);
		ef.meet(2,3);
		ef.meet(2,4);
		ef.meet(2,5);
		System.out.println("Enchanted forest A:");
		ef.findWinners();



		EnchantedForest ee = new EnchantedForest(9);
		ee.meet(1,2);
		ee.meet(1,3);
		ee.meet(1,4);
		ee.meet(1,5);
		ee.meet(1,6);
		ee.meet(1,7);
		ee.meet(1,8);
		ee.meet(1,9);
		ee.meet(2,3);
		ee.meet(2,5);
		ee.meet(2,5);
		ee.meet(8,2);
		ee.meet(8,3);
		ee.meet(8,4);
		ee.meet(8,5);
		System.out.println("Enchanted forest B:");
		ee.findWinners();




	}
}

