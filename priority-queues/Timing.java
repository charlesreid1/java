import java.util.LinkedList;
import java.util.Random;
import java.util.PriorityQueue;

/** Timing class: measure big-O complexity and runtime of data structures.
 *
 * Compare algorithms, test structures, and verify expected big-O behavior.
 *
 * Output format:
 * This timing class verifies multiple behaviors for multiple classes.
 * We don't spend too much time making things pretty, 
 * because we're principally interested in the timing results.
 *
 * Operations whose big O cost we want to verify:
 *		- insert
 *		- removeMin
 *		- iterator
 *
 * Objects we are dealing with:
 *		- SortedPQ
 *
 */
public class Timing {

	// We will perform and time Nops add operations.
	static int Nops = 100;

	// We will do that Ntrials times.
	static int Ntrials = 5000;

	// We will add to a container of size N.
	static int[] Ns = {100,200,300,400,500,600,700,800,900,1000};



	/** Run tests */
	public static void main(String[] args) { 

		PQ[] collection = new PQ[3];
		collection[2] = new HeapPQ<Integer,Integer>();
		collection[1] = new UnsortedPQ<Integer,Integer>();
		collection[0] = new SortedPQ<Integer,Integer>();

		// There is likely a better way to do this, 
		// but with column-wise data, I'm too lazy to fix.
		String[] testnames = {"Heap","Unsorted","Sorted"};
		int c = 0;
		for(PQ q : collection) {
			System.out.println("---------------------------");
			System.out.println("Add Test: "+testnames[c]+" priority queue");
			add(q);
			c++;
		}

		// To give a better relative measure...
		PriorityQueue<Integer> builtin = new PriorityQueue<Integer>();
		System.out.println("---------------------------");
		System.out.println("Add Test: Built-in PriorityQueue");
		add2(builtin);
	}


	/** Add test for homebrew priority queue. */
	public static void add(PQ<Integer,Integer> q) { 

		Random r = new Random();

		System.out.println("N\t\tWalltime Add Tot (ms)\t\tWalltime Add Per 1k (ms)");

		// Gathering stats on each size
		for(int i = 0; i<Ns.length; i++) { 

			// set N
			int N = Ns[i];

			// set timer
			Tim tim = new Tim();

			for(int k=0; k<Ntrials; k++) { 

				// Start by initializing the container
				q.clear();
				for(int j = 0; j < N; j++) {
					q.insert(r.nextInt(),r.nextInt());
				}

				// Now time adding
				tim.tic();

				for(int m=0; m<Nops; m++) { 
					q.insert(r.nextInt(), r.nextInt());
				}

				tim.toc();

			}

			// Now printing out N, total walltime, time per 1k add ops
			double per_op = tim.elapsedms()/(1.0*Ntrials*Nops);
			double per_1kop = (tim.elapsedms()/(1.0*Ntrials*Nops))*1000;

			// done with timing trials, now print out
			// N, add total, ad per 1k operation
			System.out.printf("%d\t\t\t%.4f\t\t\t%.4f\n", N, tim.elapsedms(), per_1kop);

		}

	}




	/** Add test for built-in priority queue. */
	public static void add2(PriorityQueue<Integer> q) { 

		Random r = new Random();

		System.out.println("N\t\tWalltime Add Tot (ms)\t\tWalltime Add Per 1k (ms)");

		// Gathering stats on each size
		for(int i = 0; i<Ns.length; i++) { 

			// set N
			int N = Ns[i];

			// set timer
			Tim tim = new Tim();

			for(int k=0; k<Ntrials; k++) { 

				// Start by initializing the container
				q.clear();
				for(int j = 0; j < N; j++) {
					q.add(r.nextInt());
				}

				// Now time adding
				tim.tic();

				for(int m=0; m<Nops; m++) { 
					q.add(r.nextInt());
				}

				tim.toc();

			}

			// Now printing out N, total walltime, time per 1k add ops
			double per_op = tim.elapsedms()/(1.0*Ntrials*Nops);
			double per_1kop = (tim.elapsedms()/(1.0*Ntrials*Nops))*1000;

			// done with timing trials, now print out
			// N, add total, ad per 1k operation
			System.out.printf("%d\t\t\t%.4f\t\t\t%.4f\n", N, tim.elapsedms(), per_1kop);

		}

	}

}

