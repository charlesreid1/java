/** 
 * Binomial Distribution Calculations.
 *
 * This class enable some calculations using the 
 * binomial distribution, used for instances of 
 * independent trials.
 */

public class Binomial {

	public static void main(String[] args) { 
		System.out.println(coefficient(10,4));
	}

	/** Compute the binomial coefficient. 
	 *
	 * ( n )       n!
	 * (   ) = --------------
	 * ( k )    k! (n-k)!
	 * */
	public static long coefficient(int n, int k) { 
		return prodRange(n, n-k)/factorial(k);
	}

	/** Return the product from i to j, exclusive.
	 * i*(i-1)*(i-2)* ... *(i-j+1)
	 */
	public static long prodRange(int i, int j) { 
		long result = 1;
		if(i<j) { 
			int temp = i; i = j; j = temp;
		}
		for(int k=i; k>j; k--) { 
			result *= k;
		}
		return result;
	}

	/** Compute the factorial of a number, iteratively. */
	public static long factorial(int k) {
		if(k<0) { 
			throw new IllegalArgumentException("This ain't the gamma function!");
		} else if(k==0 || k==1) {
			return 1;
		} else {
			// Avoid a stack overflow error
			long result = 1;
			for(int i=1; i<=k; i++) { 
				result *= i; 
			}
			return result;
		}
	}
}
