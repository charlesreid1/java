/** 
 * Hypergeometric Distribution Calculations.
 *
 * This class enable some calculations using the 
 * hypergeometric distribution, used for instances of 
 * sampling without replacement.
 */

public class Hypergeometric {

	public static void main(String[] args) { 
		testHypergeom();
	}

	public static void testHypergeom() {
		System.out.println("Testing hypergeometric distribution.");
		System.out.println("K = 4, k = 2, N = 15, n = 5");
		System.out.println();
		System.out.println("Number of hands with exactly two aces:");
		System.out.println("Should be 103776");
		System.out.println( coefficient(4,2,52,5));
		System.out.println();
		System.out.println("Probability of hand with exactly two aces:");
		System.out.println("Should be 0.0399...");
		System.out.println( coefficientP(4,2,52,5));
	}

	public static long coefficient(int K, int k, int N, int n) { 
		// This computes the hypergeometric coefficient for the given parameter values:
		// K = total possible successes
		// k = target number of successes
		// N = population size
		// n = sample size

		// Number of outcomes is given by:
		// ( K ) ( N-K )
		// ( k ) ( n-k )

		int result = 1;
		result *= Binomial.coefficient(K,k);
		result *= Binomial.coefficient(N-K,n-k);
		return result;
	}

	/** Compute probability of exactly k successes in n draws. 
	 *
	 * Probability is given by:
	 * ( K ) ( N-K )
	 * ( k ) ( n-k )
	 * -------------
	 *     ( N )
	 *     ( n )
	 * */
	public static double coefficientP(int K, int k, int N, int n) { 
		// This computes the probability of k successes 
		// using a hypergeometric distribution.
		double result = (1.0*coefficient(K,k,N,n))/Binomial.coefficient(N,n);
		return result;
	}




}
