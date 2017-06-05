import java.util.LinkedList;

/** Timing class: measure big-O complexity and runtime of data structures.
 *
 * Compare algorithms, test structures, and verify expected big-O behavior.
 *
 * This program is intended to be run on the command line like so:
 *
 * java Timing > output.txt
 *
 */
public class Timing {

	// Tests
	public static void main(String[] args) { 
		linked_list_add_test();
	}


	/** Compare the add method - appending to the rear of a list. 
	 *  
	 *  Compares builtin LinkedList type with self-authored TLinkedList class.
	 *  */
	public static void linked_list_add_test() {


		System.out.println("N, Walltime Builtin (s), Walltime Mine (s)");

		// Loop over some values of N, and print the amortized operational cost.
		for(int N = 1024; N < 1E6; N*=2) { 

			int ntrials = 1000;

			// Hold on to your butts.

			System.out.printf("%d, ", N);


			// builtin linked list:
			//
			// b prefix is for built-in datatype
			Tim btim = new Tim();

			// Trials counter is always
			// K for Kafka
			for(int k = 0; k<ntrials; k++) { 
				LinkedList<Integer> blist = new LinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					blist.add(i*i);
				}
			}
			double btime_total = btim.elapsed();

			System.out.printf("%.2f,",  btime_total);


			// my little linked list:
			//
			// m prefix is for mine
			Tim mtim = new Tim();

			// Trials counter is k
			for(int k=0; k<ntrials; k++) { 
				TLinkedList<Integer> mylist = new TLinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					mylist.addLast(i*i);
				}
			}
			double mtime_total = mtim.elapsed();
			System.out.printf("%.2f \n", mtime_total);


		}
	}



}

