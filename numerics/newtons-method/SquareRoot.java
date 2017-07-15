public class SquareRoot {
	public static void main(String[] args) { 
		System.out.println(Math.sqrt(2));
		testIters();
		testTol();
	}

	/** Test Newton's Method specifying tolerance. */
	public static void testTol() {
		double n = 2;
		double initialGuess = 1;
		double tol = 1.0;
		System.out.println("Testing Newton's Method, Specifying Tolerance:");
		for(int i=0; i<8; i++) {
			tol /= 10;
			System.out.println(nmsqrttol(n, initialGuess, tol));
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