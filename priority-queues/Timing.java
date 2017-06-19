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

		sb.append("N, Walltime Add (ms), Walltime Rm Min (ms)\n");

		int ntrials = 200;
		Random r = new Random();

		// Loop over values of N
		for(int N = (int)(5E3); N <= (int)(5E5); N+=2500) { 

			Tim add_tim = new Tim();
			Tim rm_tim = new Tim();

			// Trials counter is always k for Kafka
			for(int k = 0; k<ntrials; k++) { 
				// Each trial is a different sequence of random numbers,
				// but the sequence matches between tests of different collection types 
				SortedPriorityQueue<Integer> q = new SortedPriorityQueue<Integer>();
				Integer key = new Integer( r.nextInt() );
				Integer val = new Integer( r.nextInt() );

				add_tim.tic();
        		for(int i=0; i<N; i++) { 
					q.add(key,val);
				}
				add_tim.toc();

				rm_tim.tic();
        		for(int i=0; i<N; i++) { 
					q.removeMin();
				}
				rm_tim.toc();
			}

			sb.append( String.format("%d, ",N) );
			sb.append( String.format("%.3f, ", add_tim.elapsedms()) );
			sb.append( String.format("%.3f  ",  rm_tim.elapsedms()) );
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}






}

