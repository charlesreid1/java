import java.io.*;
import java.util.*;

public class SquareRoot {
	public static void main(String[] args) { 
		//System.out.printf("Actual square root = %.16f\n",Math.sqrt(2));
		//testIters();
		//testTol();
		//testAccuracy();
		testTime();
	}


	/** Time Newton's Method.
	 *
	 * How long does it take to achieve 10 digits of accuracy? */
	public static void testTime() { 

		int Nops;
		double a;
		double initialGuess;
		double tol;
		double time;

		Tim tim;

		Nops = 10000000;
		a = 2;
		initialGuess = 1;
		tol = 1E-8;
		tim = new Tim();
		tim.tic();
		for(int i=0; i<Nops; i++) { 
			nmsqrttol(a, initialGuess, tol);
		}
		tim.toc();
		time = 1000*tim.elapsedms()/Nops;
		System.out.println("Newton's Method Time (ms) per 1k operations: "+time);


		Nops = 10000000;
		a = 2;
		tim = new Tim();
		tim.tic();
		for(int i=0; i<Nops; i++) { 
			Math.sqrt(a);
		}
		tim.toc();
		time = 1000*tim.elapsedms()/Nops;
		System.out.println("Math Library Time (ms) per 1k operations: "+time);

	}


	/** Test Newton's Method for accuracy. */
	public static void testAccuracy() {
		try {

			// Get 10,000 digits of sqrt(2)
			Scanner s = new Scanner(new BufferedReader(new FileReader("sqrt2.txt")));
			String correct = "";
			if(s.hasNextLine()) { 
				correct = s.nextLine();
			}

			// Now compute sqrt(2) using our method
			double a = 2.0;
			double initialGuess = 1.0;

			String computed;
			double tol = 1;
			for(int i=0; i<14; i++) {
				tol /= 10;
				computed = Double.toString(nmsqrttol(a, initialGuess, tol)) ;

				// Now find length of longest common substring, and discount "1."
				int common = commonSubstring(correct, computed) - 2;
				System.out.printf("Tolerance = %.1g\t\tNumber of accurate digits = %d\n", tol, common);
			}


		} catch(FileNotFoundException e) {
			return;
		}
	}

	/** Test Newton's Method specifying tolerance. */
	public static void testTol() {
		double n = 2;
		double initialGuess = 1;
		double tol = 1.0;
		System.out.println("Testing Newton's Method, Specifying Tolerance:");
		for(int i=0; i<8; i++) {
			tol /= 10;
			System.out.printf("%.1g\t\t%.16f\n", tol, nmsqrttol(n, initialGuess, tol)) ;
			//System.out.println(nmsqrttol(n, initialGuess, tol));
		}
	}

	/** Test Newton's Method specifying number of iterations. */
	public static void testIters() {
		double n = 2;
		double initialGuess = 1;
		System.out.println("Testing Newton's Method, Specifying Niters:");
		for(int nsteps=3; nsteps<8; nsteps++) {
			System.out.println(nmsqrt(n, initialGuess, nsteps));
		}
	}


	/** Compute the length of the longest common substring of two strings. */
	public static int commonSubstring(String first, String second) {
	    if (first == null || second == null || first.length() == 0 || second.length() == 0) {
	        return 0;
	    }
	
	    int maxLen = 0;
	    int fl = first.length();
	    int sl = second.length();
	    int[][] table = new int[fl][sl];
	
	    for (int i = 0; i < fl; i++) {
	        for (int j = 0; j < sl; j++) {
	            if (first.charAt(i) == second.charAt(j)) {
	                if (i == 0 || j == 0) {
	                    table[i][j] = 1;
	                }
	                else {
	                    table[i][j] = table[i - 1][j - 1] + 1;
	                }
	                if (table[i][j] > maxLen) {
	                    maxLen = table[i][j];
	                }
	            }
	        }
	    }
	    return maxLen;
	}




	//////////////////////////////////////////////////////////
	//
	// Begin Newton's Method Code Begins Here
	//


	/** Compute a square root using Newton's Method, to a specified tolerance.
	 *
	 * @param a Compute the square root of a.
	 * @param x0 Initial guess.
	 * @param tol Tolerance (stopping criteria).
	 */
	public static double nmsqrttol(double a, double x0, double tol) { 
		double xi, xip1, err;
		xi = xip1 = x0;
		err = x0;
		while(err > tol) { 
			xip1 = xi - (xi*xi-a)/(2*xi);
			err = Math.abs( xip1 - xi );
			xi = xip1;
		}
		return xip1;
	}


	/** Compute a square root using Newton's Method, using specified
	 * number of iterations.
	 *
	 * @param a Compute the square root of a.
	 * @param x0 Initial guess.
	 * @param nsteps Number of iterative steps to take.
	 */
	public static double nmsqrt(double a, double x0, int nsteps) { 
		double xi, xip1;
		xi = x0;
		xip1 = x0;
		for(int i=0; i<nsteps; i++) { 
			xip1 = xi - (xi*xi - a)/(2*xi);
			xi = xip1;
		}
		return xip1;
	}
}