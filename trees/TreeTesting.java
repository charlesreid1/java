import java.util.LinkedList;
import java.util.Random;

public class TreeTesting {

	// Tests
	public static void main(String[] args) { 
		LinkedBinTree<Integer> t = getFullTree(3);

		// Traverse the tree in order.
		System.out.println("Printing tree in breadth-first order:");
		for(Position<Integer> p : t.bft()) {
			System.out.print(spaces(t.depth(p)));
			System.out.print(p.getElement());
			System.out.print("\n");
		}

		System.out.println("Printing tree in pre-order:");
		printPreorder(t, t.root(), 0);
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

		LinkedBinTree<Integer> t = new LinkedBinTree<Integer>();
		Random r = new Random();

		t.addRoot(0);

		for(int level = 1; level <= n; level++) { 

			for(Position<Integer> node : t.positions()) {
				// isLeafNode? isExternal? isDangling? 
				// the problem with interfaces is when you end up with a dozen files where it ::could:: be defined.
				// grep to th e rescue.
				if(t.isExternal(node)) {
					Integer iL = new Integer( r.nextInt(n*n)-4*n  );
					Integer iR = new Integer( r.nextInt(n*n)-4*n  );
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



