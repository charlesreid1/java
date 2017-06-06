import java.util.LinkedList;
import java.util.Random;

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
		//linked_list_add_test();
		//linked_list_add_remove_test();
		//test_reverse();
		test_rotate();
	}




	/** Verify rotate linked list method is O(n). */
	public static void test_rotate() {

		StringBuffer sb = new StringBuffer();
		sb.append("N, Walltime Rotate (ms), Amortized Rotate (us)\n");

		int ntrials = 1000;
		Random r;

		// Loop over values of N
		for(int N = (int)(5E3); N <= (int)(5E5); N+=2500) { 

			Tim tim = new Tim();

			// Trials counter is always k for Kafka
			for(int k = 0; k<ntrials; k++) { 
				// Each trial is a different sequence of random numbers,
				// but the sequence matches between tests of different collection types 
				r = new Random(k);
				TLinkedList<Integer> mylist = new TLinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					mylist.addFirst(r.nextInt(100));
				}
				mylist.rotate();
			}
			double time_total = tim.elapsedms();

			// print amortized cost per 1000 operations, times 1000 ms -> us
			sb.append( String.format("%d, ",N) );
			sb.append( String.format("%.3f, ", time_total) );
			sb.append( String.format("%.3f ", time_total/N*1000) );
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}










	/** Verify reverse linked list method is O(n). */
	public static void test_reverse() {

		StringBuffer sb = new StringBuffer();
		sb.append("N, Walltime Reverse (ms), Amortized Reverse (us)\n");

		int ntrials = 1000;
		Random r;

		// Loop over values of N
		for(int N = (int)(1E3); N <= (int)(1E5); N+=2500) { 


			// builtin linked list:
			//
			// b prefix is for built-in datatype
			Tim tim = new Tim();

			// Trials counter is always k for Kafka
			for(int k = 0; k<ntrials; k++) { 
				// Each trial is a different sequence of random numbers,
				// but the sequence matches between tests of different collection types 
				r = new Random(k);
				TLinkedList<Integer> mylist = new TLinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					mylist.addFirst(r.nextInt(100));
				}
				mylist.reverse();
			}
			double time_total = tim.elapsedms();

			// print amortized cost per 1000 operations, times 1000 ms -> us
			sb.append( String.format("%d, ",N) );
			sb.append( String.format("%.3f, ", time_total) );
			sb.append( String.format("%.3f ", time_total/N*1000) );
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}





	/** Compare the add and remove method - adding and removing from list. 
	 *
	 *  NOTE: If this is not constant, then add is not O(1) amortized, 
	 *  and there is something wrong with your implementation.
	 *  */
	public static void linked_list_add_remove_test() {
		StringBuffer sb = new StringBuffer();

		//
		// Add Remove Amortized Walltime Per 1k Builtin (us), Add Remove Amortized Walltime Per 1k SLL (us), Add Remove Amortized Walltime Per 1k DLL (us)
		//
		sb.append("N, Walltime Builtin (us), Walltime SLL (us), Walltime DLL (us)\n");
		
		int ntrials = 1000;
		Random r;

		// Loop over values of N
		for(int N = (int)(1E3); N <= (int)(1E6); N*=4) { 

			sb.append( String.format("%d, ",N) );


			// builtin linked list:
			//
			// b prefix is for built-in datatype
			Tim btim = new Tim();

			// Trials counter is always k for Kafka
			for(int k = 0; k<ntrials; k++) { 
				// Each trial is a different sequence of random numbers,
				// but the sequence matches between tests of different collection types 
				r = new Random(k);
				LinkedList<Integer> blist = new LinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					// 75% of the time, we add something. 25% of the time, we remove something.
					boolean addSomething = r.nextBoolean();
					if(addSomething) { 
						blist.add(0,r.nextInt(100));
					} else {
						if(!blist.isEmpty()) {
							blist.remove(0);
						}
					}
				}
			}
			double btime_total = btim.elapsedms();



			// my little singly linked list:
			//
			// m prefix is for mine
			Tim mtim = new Tim();

			for(int k=0; k<ntrials; k++) { 
				// reproducible random numbers across tests
				r = new Random(k);
				TLinkedList<Integer> mylist = new TLinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					// 75% of the time, we add something. 25% of the time, we remove something.
					boolean addSomething = r.nextBoolean();
					if(addSomething) { 
						mylist.addFirst(r.nextInt(100));
					} else {
						if(!mylist.isEmpty()) {
							mylist.removeFirst();
						}
					}
				}
			}
			double mtime_total = mtim.elapsedms();




			// doubly linked list:
			//
			// d prefix is for doubly
			Tim dtim = new Tim();

			for(int k=0; k<ntrials; k++) { 
				r = new Random(k);
				TLinkedList<Integer> dlist = new TLinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					// 75% of the time, we add something. 25% of the time, we remove something.
					boolean addSomething = r.nextBoolean();
					if(addSomething) { 
						dlist.addFirst(r.nextInt(100));
					} else {
						if(!dlist.isEmpty()) {
							dlist.removeFirst();
						}
					}
				}
			}
			double dtime_total = dtim.elapsedms();

			// print amortized cost per 1000 operations, times 1000 ms -> us
			sb.append( String.format("%.3f, ", btime_total/N*1000) );
			sb.append( String.format("%.3f, ", mtime_total/N*1000) );
			sb.append( String.format("%.3f ",  dtime_total/N*1000) );


			sb.append("\n");

		}


		System.out.println(sb.toString());

	}


	/** Compare the add method - appending to the rear of a list. 
	 *  
	 *  Compares builtin LinkedList type with self-authored TLinkedList class.
	 *
	 *  NOTE: If this is not constant, then add is not O(1) amortized, 
	 *  and there is something wrong with your implementation.
	 *  */
	public static void linked_list_add_test() {

		StringBuffer sb = new StringBuffer();

		// Add Amortized Walltime Per 1k Builtin (us), Add Amortized Walltime Per 1k SLL (us)
		sb.append("N, Add Builtin (us), Add SLL (us), Add DLL (us)\n");
		
		int ntrials = 1000;

		// Loop over some values of N, 
		// print the total cost,
		// print the cost per add operation 
		for(int N = 1024; N < 5e5; N *= 2) { 


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
			double btime_total = btim.elapsedms();

			// singly linked list:
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
			double mtime_total = mtim.elapsedms();


			// doubly linked list:
			Tim dtim = new Tim();

			// Trials counter is k
			for(int k=0; k<ntrials; k++) { 
				TLinkedList<Integer> dlist = new TLinkedList<Integer>();
        		for(int i=0; i<N; i++) { 
					dlist.addLast(i*i);
				}
			}
			double dtime_total = dtim.elapsedms();


			// Print out the data in CSV format:

			// N
			sb.append( String.format("%d, ", N) );

			// print cost per 1000 add operations us, times 100 ms -> us
			sb.append( String.format("%.3f, ",  btime_total/N*1000) );
			sb.append( String.format("%.3f, ",  mtime_total/N*1000) );
			sb.append( String.format("%.3f ",   dtime_total/N*1000) );
			sb.append("\n");

		}

		System.out.println(sb.toString());
	}



}

