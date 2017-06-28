import java.util.HashMap;
import java.util.Random;

/** Timing Map class.
 *
 * This class times built-in and hand-rolled map classes.
 */
public class TimingCompare {

	// Static constants:

	// We will perform and time Nops insert operations.
	static int Nops = 100;

	// We will do that Ntrials times.
	static int Ntrials = 10000;

	// We will insert to a container of size N.
	static int[] Ns = {100,200,300,400,500,600,700,800,900,1000};



	// Utility class: word generator
	
	protected static class WordGenerator { 
		Random r;
		public WordGenerator() { 
			r = new Random();
		}
		public String next() { 
			int n = r.nextInt(15);
			char[] result = new char[n];
			for(int j=0; j<n; j++) { 
				result[j] = (char)('a' + r.nextInt(26));
			}
			return new String(result);
		}
	}



	/** Run tests */
	public static void main(String[] args) {

		ChainedHashMap<String,String> cm = new ChainedHashMap<String,String>();
		//test_myhashmap(cm);
		test_generic(cm);

		System.out.println("\n\n");

		HashMap<String,String> hm = new HashMap<String,String>();
		//test_builtinhashmap(hm);
		test_generic(hm);
	}


	public static void test_generic(Object map) { 

		Random r = new Random();

		System.out.print("N, Walltime Insert Tot (ms), Walltime Insert Per 1k (ms), ");
		System.out.print("Walltime Rm Tot (ms), Walltime Rm Per 1k (ms)\n");

		// Gathering stats on each value of N
		for(int i = 0; i<Ns.length; i++) { 

			// set N
			int N = Ns[i];

			// set timer
			Tim insert_tim = new Tim();
			Tim rm_tim     = new Tim();

			for(int k=0; k<Ntrials; k++) { 




				// MyMap Test:

				if(map instanceof ChainedHashMap) { 

					ChainedHashMap<String,String> m = (ChainedHashMap<String,String>)(map);

					m.clear();

					// Initialize the container to be of size N
					WordGenerator wg = new WordGenerator();
					// (this actually isn't correct... duplicates mean we are smaller than N. 
					//  could alternatively do this wile size()<N.)
					for(int j=0; j<N; j++) { 
						String token = wg.next();
						if(token.length()>0) { 
							m.put(token, token.toUpperCase());
						}
					}

					// Now time inserting
					insert_tim.tic(); 
					for(int j=0; j<Nops; j++) { 
						String token = wg.next();
						if(token.length()>0) { 
							m.put(token, token.toUpperCase());
						}
					} 
					insert_tim.toc();

					// Now time removing
					rm_tim.tic(); 
					for(int j=0; j<Nops; j++) { 
						String token = wg.next();
						m.remove(token);
					} 
					rm_tim.toc();







				// Builtin Map Test:

				} else if(map instanceof HashMap) { 

					HashMap<String,String> m = (HashMap<String,String>)(map);

					m.clear();

					// Initialize the container to be of size N
					WordGenerator wg = new WordGenerator();
					// (this actually isn't correct... duplicates mean we are smaller than N. 
					//  could alternatively do this wile size()<N.)
					for(int j=0; j<N; j++) { 
						String token = wg.next();
						if(token.length()>0) { 
							m.put(token, token.toUpperCase());
						}
					}

					// Now time inserting
					insert_tim.tic(); 
					for(int j=0; j<Nops; j++) { 
						String token = wg.next();
						if(token.length()>0) { 
							m.put(token, token.toUpperCase());
						}
					} 
					insert_tim.toc();

					// Now time removing
					rm_tim.tic(); 
					for(int j=0; j<Nops; j++) { 
						String token = wg.next();
						m.remove(token);
					} 
					rm_tim.toc();
				}




			}

			// Now printing out N, total walltime, time per 1k insert ops
			//double insert_per_op = insert_tim.elapsedms()/(1.0*Ntrials*Nops);
			double insert_per_1kop = (insert_tim.elapsedms()/(1.0*Ntrials*Nops))*1000;
			double rm_per_1kop  = (rm_tim.elapsedms()/(1.0*Ntrials*Nops))*1000;

			// done with timing trials, now print out
			// N, insert total, ad per 1k operation

			// Insert:
			System.out.printf("%d, %.4f, %.4f, ", N, insert_tim.elapsedms(), insert_per_1kop);

			// Remove:
			System.out.printf("%.4f, %.4f\n", rm_tim.elapsedms(), rm_per_1kop);
		}
	}


}


