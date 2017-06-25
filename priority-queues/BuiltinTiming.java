import java.util.LinkedList;
import java.util.Random;
import java.util.PriorityQueue;

/** Timing of Builtin Priority Queue in Java.
 */

public class BuiltinTiming {

	// We will perform and time Nops add operations.
	static int Nops = 100;

	// We will do that Ntrials times.
	static int Ntrials = 10000;

	// We will add to a container of size N.
	static int[] Ns = { 1000,2000,3000,4000,5000,
					6000,7000,8000,9000,
					10000,20000,30000,40000,50000,60000,70000,80000,90000,100000};



	/** Run tests */
	public static void main(String[] args) { 
		PriorityQueue<Integer> builtin = new PriorityQueue<Integer>();
		System.out.println("---------------------------");
		System.out.println("Add Test: Built-in PriorityQueue");
		add(builtin);
	}



	/** Add test for built-in priority queue. */
	public static void add(PriorityQueue<Integer> q) { 

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
