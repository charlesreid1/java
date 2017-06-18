import java.util.LinkedList;
import java.util.Random;

public class TreeTesting {

	public static void banner(String title) { System.out.println("\n\n================ "+title+"\n\n"); }
	
	// Tests
	public static void main(String[] args) { 
		banner("Testing tree traversal.");
		test_traversal();
		banner("Testing tree attaching.");
		test_attach();
		banner("Testing tree removal.");
		test_remove();
	}




	/** Generate a full tree and start paring some nodes down. */
	public static void test_remove() {

		for(int n = 3; n<=8; n++) {
			LinkedBinTree<Integer> t = getFullTree(n);

			System.out.println("\n\nTree:");
			System.out.println("N levels: " + n);

			Position<Integer> p = t.root.getLeft();
			try {
				t.remove(p);
			} catch(IllegalArgumentException e) {
				// do nothing
			}

			System.out.println("Pruning subtree.");

			System.out.println("Before:");
			System.out.println("Tree size: " + t.size());

			t.pruneSubtree(p);

			System.out.println("After:");
			System.out.println("Tree size: " + t.size());
			//printPreorder(t,t.root(), 0);
		}	

	}




	/** Generate a full tree and traverse it a couple of ways. */
	public static void test_traversal() {

		LinkedBinTree<Integer> t = getFullTree(4);

		// Traverse the tree in breadth-first order using the built-in BFT iterator.
		System.out.println("Printing tree in breadth-first order:");
		for(Position<Integer> p : t.bft()) {
			System.out.print(spaces(t.depth(p)));
			System.out.print(p.getElement());
			System.out.print("\n");
		}

		// Traverse the tree in pre-order using a custom-defined static recursive method (see below.)
		System.out.println("Printing tree in pre-order:");
		printPreorder(t, t.root(), 0);

		System.out.println("Size of tree: " + t.size());

		// Creating and traversing a tree 
		// tests a surprising amount of the class.
	}

	/** Generate some big trees and attach them. */
	public static void test_attach() { 

		// Attach

		LinkedBinTree<Integer> a = getFullTree(12);
		LinkedBinTree<Integer> b = getFullTree(12);

		int asize = a.size();
		int bsize = b.size();

		System.out.println("Size of Tree A: " + asize);
		System.out.println("Size of Tree B: " + bsize);

		LinkedBinTree<Integer> full = getFullTree(12);

		int fullsize = full.size();
		System.out.println("Size of FullTree before attach: " + fullsize);

		for(Position<Integer> p : full.bft()) {
			if(full.isExternal(p)) {
				full.attach(p, a, b);
			}
		}
		
		System.out.println("Size of FullTree after attach: " + full.size());
		System.out.println("(should match "+ (asize+bsize+fullsize) + ")");


		System.out.println("Tree height: "+full.height());

	}

	/** Print the nodes in preorder.
	 * Need to include the <E> after static
	 * to make sure this works as a generic static method.
	 * */
	public static <E> void printPreorder( Tree<E> t, Position<E> p, int d) { 
		System.out.println(spaces(2*d)+p.getElement());
		for(Position<E> c : t.children(p)) {
			printPreorder(t, c, d+1);
		}
	}

	////////////////////////////////////////////////////
	// Test suite

	/** Test method: get a full binary tree with n levels. */
	public static LinkedBinTree<Integer> getFullTree(int n) {

		int MAX = 100;

		LinkedBinTree<Integer> t = new LinkedBinTree<Integer>();
		Random r = new Random();

		t.addRoot(0);

		for(int level = 1; level <= n; level++) { 

			for(Position<Integer> node : t.positions()) {
				// isLeafNode? isExternal? isDangling? 
				// the problem with interfaces is when you end up with a dozen files where it ::could:: be defined.
				// grep to th e rescue.
				if(t.isExternal(node)) {
					Integer iL = new Integer( r.nextInt(MAX*MAX)-4*MAX  );
					Integer iR = new Integer( r.nextInt(MAX*MAX)-4*MAX  );
					t.addLeft(node,iL);
					t.addRight(node,iR);
				}
			}
		}

		return t;
	}

	///////////////////////////////////////
	// Utility Methods

	public static String spaces(int n) {
		StringBuffer s = new StringBuffer();
		for(int i=0;i<n;i++) { 
			s.append(" ");
		}
		return s.toString();
	}

}



