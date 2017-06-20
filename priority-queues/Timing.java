import java.util.LinkedList;
import java.util.Random;

/** Timing class: measure big-O complexity and runtime of data structures.
 *
 * Compare algorithms, test structures, and verify expected big-O behavior.
 *
 */
public class Timing {

	// Tests
	public static void main(String[] args) { 
		sorted_timing();
	}

	/** Time sorted priority queue. */
	public static void sorted_timing() {

		// This generates CSV files to verify the following information:
		// - add method is O(N)
		// - remove min method is O(1)

		StringBuffer sb = new StringBuffer();

		sb.append("N, Walltime Add (us), Walltime Rm Min (us)\n");

		int ntrials = 1000;
		Random r = new Random();

		// Loop over values of N
		int start =   50000;
		int skip  =   50000;
		int MAX   = 1000000;
		for(int N = start; N <= MAX; N+=skip) { 

			Tim add_tim = new Tim();
			Tim rm_tim = new Tim();

			// Trials counter is always k for Kafka
			for(int k = 0; k<ntrials; k++) { 
				// Each trial is a different sequence of random numbers,
				// but the sequence matches between tests of different collection types 
				SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();

				add_tim.tic();
        		for(int i=0; i<N/2; i++) { 
					Integer key = new Integer( r.nextInt() );
					Integer val = new Integer( r.nextInt() );
					q.add(key,val);
				}
				add_tim.toc();

				rm_tim.tic();
        		for(int i=0; i<N/4; i++) { 
					q.removeMin();
				}
				rm_tim.toc();

				add_tim.tic();
        		for(int i=N/2; i<N; i++) { 
					Integer key = new Integer( r.nextInt() );
					Integer val = new Integer( r.nextInt() );
					q.add(key,val);
				}
				add_tim.toc();

				rm_tim.tic();
        		for(int i=N/4; i<N; i++) { 
					q.removeMin();
				}
				rm_tim.toc();

			}

			// Normalized for trials, not for container size N
			sb.append( String.format("%d, ",N) );
			sb.append( String.format("%.3f, ", 1000*add_tim.elapsedms()/ntrials) );
			sb.append( String.format("%.3f  ",  1000*rm_tim.elapsedms()/ntrials) );
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}






}

