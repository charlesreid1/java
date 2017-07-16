import java.util.*;

public class Convergents {

	/** 
	 * Compute the convergents (rational representation of
	 * continued fraction).
	 *
	 * This uses the recurrence relation:
	 *
	 * P_n     a_n P_n-1  + P_n-2
	 * ---- = -----------------
	 *  Q_n    a_n Q_n-1  + Q_n-2
	 */
	public static long[] convergents(List<Integer> cfrepr, int nterms) {
		long[] convergents = new long[2];

		// Initial values for convergent recurrence relation
		long Pnm2 = 0; // P_{n-2}
		long Pnm1 = 1;
		long Qnm2 = 1;
		long Qnm1 = 0;
		long P = 0;
		long Q = 0;

		// Term 0 is the constant value a0.
		int accessindex = 0;
		for(int i=0; i<=nterms; i++) { 
			int an = cfrepr.get(accessindex);

			P = an * Pnm1 + Pnm2;
			Q = an * Qnm1 + Qnm2;

			Pnm2 = Pnm1;
			Pnm1 = P;

			Qnm2 = Qnm1;
			Qnm1 = Q;

			if(accessindex+1>=cfrepr.size()) { 
				// Ensure we keep repeating the sequence
				// if the sequence has fewer terms than
				// the user asks for.
				// This allows us to get really good
				// approximations for numbers.
				// This only works because the sequence
				// is palindromic.
				accessindex = 1;
			} else {
				accessindex++;
			}
		}

		convergents[0] = P;
		convergents[1] = Q;

		return convergents;
	}


	/** 
	 * Compute the convergents (rational representation of
	 * continued fraction) of the square root of n.
	 */
	public static long[] convergentsSquareRoot(int n, int nterms) {
		List<Integer> cfrepr = continuedFractionSqrt(n);
		return convergents(cfrepr, nterms);
	}

	/** Find the (shorter than 10) continued fraction sequence for sqrt(n). 
	 * This returns a list of integers, [a0, a1, a2, ...]
	 */
	public static List<Integer> continuedFractionSqrt(int n) {
		int niters = 10; // handbrake

		int ai = 0;
		double val = 0;
		double remainder = 0;
		List<Integer> coeffs = new ArrayList<Integer>();

		// Fencepost
		remainder = 1.0/Math.sqrt(n);

		for(int i=0; i<niters; i++) {
			val = 1.0/remainder;
			ai = floor(val);
			remainder = val - ai;
			coeffs.add(ai);
			if(coeffs.get(i) == 2*coeffs.get(0)) {
				break;
			}
		}
		return coeffs;
	}

	/** Check if x is a perfect square. */
	public static boolean isPerfectSquare(int x) { 
		int s = (int)(Math.round(Math.sqrt(x)));
		return x==(s*s);
	}

	/** Find the floor of a double. */
	public static int floor(double j) {
		return (int)(Math.floor(j));
	}
}
