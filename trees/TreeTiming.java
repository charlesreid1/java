import java.util.LinkedList;
import java.util.Random;

/** 
 * Tree Timing:
 * Measure Big-O complexity and run time cost of tree operations. 
 *
 * This program is intended to be run on the command line like so:
 *
 * java Timing > output.txt
 *
 */
public class TreeTiming {



	public static void main(String[] args) { 
		System.out.println("***************************");
		System.out.println("Breadth-first search timing");
		System.out.println("***************************");
		bft();

		System.out.println("***************************");
		System.out.println("Add new nodes to tree timing");
		System.out.println("***************************");
		add();

		System.out.println("***************************");
		System.out.println("Remove nodes from tree timing");
		System.out.println("***************************");
		remove();

	}


	/** Add operation timing test. */
	public static void add() {

		StringBuffer sb = new StringBuffer();
		sb.append("Nodes Added, Total Time (ms), Amortized Time (ms)\n");

		int MAX = 100;
		Random r = new Random();

		for(int j=6; j<= 14; j++) {

			Tim tim = new Tim();
			int Ntrials = 1000;
			int Nnodes=(int)(Math.pow(2,j));


			sb.append(Nnodes + ", ");


			for(int trials = 0; trials < Ntrials; trials++ ) {
			    
				LinkedBinTree<Integer> t = getFullTree(2);

				tim.tic();

				// Linear add
				Position<Integer> node = t.root().getLeft().getLeft();
				for(int i=0; i<Nnodes; i++) { 
					node = t.addLeft(node, new Integer( r.nextInt(MAX)));
				}

				tim.toc();

			}

			double tot_time = tim.elapsedms();
			double amortized = tot_time/Nnodes;

			sb.append( String.format("%.3f, %.6f\n", tot_time, amortized) );
		}
		System.out.println(sb.toString());

	}





	/** Remove operation timing test. */
	public static void remove() {

		StringBuffer sb = new StringBuffer();
		sb.append("Nodes Removed, Total Time (ms), Amortized Time (ms)\n");

		int MAX = 100;
		Random r = new Random();

		for(int j=6; j<= 14; j++) {

			Tim tim = new Tim();
			int Ntrials = 1000;
			int Nnodes=(int)(Math.pow(2,j));

			sb.append(Nnodes + ", ");


			for(int trials = 0; trials < Ntrials; trials++ ) {
			    
				LinkedBinTree<Integer> t = getFullTree(2);
				Position<Integer> node = t.root().getLeft().getLeft();


				tim.tic();

				// Linear add
				for(int i=0; i<Nnodes; i++) { 
					node = t.addLeft(node, new Integer( r.nextInt(MAX)));
				}

				tim.toc();

			}

			double tot_time = tim.elapsedms();
			double amortized = tot_time/Nnodes;

			sb.append( String.format("%.3f, %.6f\n", tot_time, amortized) );
		}
		System.out.println(sb.toString());

	}







	/** Breadth-first traversal timing test. */
	public static void bft() { 

		StringBuffer sb = new StringBuffer();
		String header = "N, Total Time (ms), Amortized Time (ms)\n";
		sb.append(header);

		for(int N = 6; N <= 14; N++ ) {

			Tim bft_tim = new Tim();

			int Ntrials = 1000;
			int size = 0;

			for(int trials = 0; trials < Ntrials; trials++ ) {

				bft_tim.tic();
				LinkedBinTree<Integer> t = getFullTree(N-1);
				size = t.size();
				int nodecount = 0;
				for(Position<Integer> p : t.bft()) { 
					nodecount++;
				}
				bft_tim.toc();
			}

			double tot_time = bft_tim.elapsedms();
			double amortized = tot_time/size;

			sb.append( String.format("%d, %.3f, %.6f\n", size, tot_time, amortized) );
		}
		
		System.out.println(sb.toString());

	}




	/** Test method: get a full binary tree with n levels. */
	public static LinkedBinTree<Integer> getFullTree(int n) {


		LinkedBinTree<Integer> t = new LinkedBinTree<Integer>();

		int MAX = 100;
		Random r = new Random();

		t.addRoot(0);

		for(int level = 1; level <= n; level++) { 

			for(Position<Integer> node : t.positions()) {
				// isLeafNode? isExternal? isDangling? 
				// the problem with interfaces is when you end up with a dozen files where it ::could:: be defined.
				// grep to th e rescue.
				if(t.isExternal(node)) {
					Integer iL = new Integer( r.nextInt(MAX)  );
					Integer iR = new Integer( r.nextInt(MAX)  );
					t.addLeft(node,iL);
					t.addRight(node,iR);
				}
			}
		}

		return t;
	}




}
