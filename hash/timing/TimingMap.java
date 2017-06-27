import java.util.*;
import java.io.*;

/** Timing Map class.
 *
 * This class times built-in and hand-rolled map classes.
 */
public class TimingMap {

	// Static constants:

	// We will perform and time Nops add operations.
	static int Nops = 100;

	// We will do that Ntrials times.
	static int Ntrials = 1000;

	// We will add to a container of size N.
	static int[] Ns = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};


	// Utility class: UlyssesScanner

	protected static class UlyssesScanner {
		String filename;
		Scanner s;
		public UlyssesScanner() throws FileNotFoundException { 
			this("ulysses.txt");
		}
		public UlyssesScanner(String f) throws FileNotFoundException {
			filename = f;
			s = new Scanner(new BufferedReader(new FileReader(filename)));
		}
		public boolean hasNext() {
			return s.hasNext();
		}
		public String next() {
			return s.next();
		}
	}

	protected static class OxenScanner extends UlyssesScanner { 
		public OxenScanner() throws FileNotFoundException { 
			super("../14oxenofthesun.txt");
		}
	}



	/** Run tests */
	public static void main(String[] args) throws FileNotFoundException { 

		Map<String,String> m = new HashMap<String,String>();
		test_builtin(m);

		System.out.println("\n\n");

		Map<String,String> m2 = new TreeMap<String,String>();
		test_builtin(m2);
	}



	/** Test built-in map types. */
	public static void test_builtin(Map<String,String> m) throws FileNotFoundException {

		Random r = new Random();


		System.out.print("N, Walltime Add Tot (ms), Walltime Add Per 1k (ms), ");
		System.out.println("Walltime Rm Tot (ms), Walltime Rm Per 1k (ms)");


		// Gathering stats on each value of N
		for(int i = 0; i<Ns.length; i++) { 

			// set N
			int N = Ns[i];

			// set timer
			Tim add_tim = new Tim();
			Tim rm_tim  = new Tim();

			for(int k=0; k<Ntrials; k++) { 

				// Initialize the container to be of size N
				UlyssesScanner ulysses = new UlyssesScanner();
				// (this actually isn't correct... duplicates mean we are smaller than N. 
				//  could alternatively do this wile size()<N.)
				for(int j=0; j<N; j++) { 
					if(ulysses.hasNext()) { 
						String token = ulysses.next();
						m.put(token, token.toUpperCase());
					}
				}


				// Now time adding
				add_tim.tic();

				for(int j=0; j<Nops; j++) { 
					if(ulysses.hasNext()) { 
						String token = ulysses.next();
						m.put(token, token.toUpperCase());
					}
				}

				add_tim.toc();



				OxenScanner ox = new OxenScanner();


				// Now time removing
				rm_tim.tic();


				for(int j=0; j<Nops; j++) { 
					if(ox.hasNext()) { 
						String token = ox.next();
						m.remove(token);
					}
				}

				rm_tim.toc();

			}

			// Now printing out N, total walltime, time per 1k add ops
			//double add_per_op = add_tim.elapsedms()/(1.0*Ntrials*Nops);
			double add_per_1kop = (add_tim.elapsedms()/(1.0*Ntrials*Nops))*1000;
			double rm_per_1kop  = (rm_tim.elapsedms()/(1.0*Ntrials*Nops))*1000;

			// done with timing trials, now print out
			// N, add total, ad per 1k operation



			// Add:
			System.out.printf("%d, %.4f, %.4f, ", N, add_tim.elapsedms(), add_per_1kop);


			// Remove:
			System.out.printf("%.4f, %.4f\n", rm_tim.elapsedms(), rm_per_1kop);


		}

	}

}


