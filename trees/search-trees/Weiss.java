/**
 * Class that implements tests of the binary search tree
 * implemented following Weiss, C++ Data Structures.
 *
 * See p. 133 of Weiss book.
 */
public class Weiss { 
	public static void main(String[] args) { 
		test1();
	}



	public static void test2() { 
		BinarySearchTree b = new BinarySearchTree();
		b.insert(new Integer(5));
		b.insert(new Integer(8));
		b.insert(new Integer(48));
		b.insert(new Integer(3));
		b.printTree();
	}


	public static void test1() { 
		BinarySearchTree b = new BinarySearchTree();
		b.insert(new Integer(5));
		b.insert(new Integer(8));
		b.insert(new Integer(28));
		b.insert(new Integer(43));
		b.insert(new Integer(49));

		b.insert(new Integer(44));
		b.insert(new Integer(-4));
		b.insert(new Integer(-6));
		b.insert(new Integer(-8));
		b.insert(new Integer(-3));
		b.insert(new Integer(45));
		b.insert(new Integer(26));
		b.insert(new Integer(52));
		b.insert(new Integer(58));
		b.insert(new Integer(68));
		b.insert(new Integer(29));
		b.insert(new Integer(108));
		b.insert(new Integer(60));
		b.insert(new Integer(40));
		b.insert(new Integer(30));
		b.printTree();

		System.out.println("------------- removing 43 ");
		b.remove(new Integer(43) );
		b.printTree();

		System.out.println("------------- removing 26 ");
		b.remove(new Integer(26) );
		b.printTree();

		//System.out.println("------------- removing -10 ");
		//b.remove(new Integer(-10) );
		//b.printTree();
		//System.out.println("------------- removing -2 ");
		//b.remove(new Integer(-2) );
		//b.printTree();
	}
}



/** 
 * Binary search tree 
 * implemented using integers.
 *
 * This can be thought of as a Binary Search Tree
 * abstract data type implementation.
 *
 * This relies primarily on public methods
 * that call private recursive methods.
 */
class BinarySearchTree { 


	////////////////////////

	/** 
	 * Binary tree node.
	 */
	class BinaryNode { 
		Integer element;
		BinaryNode left, right;

		public BinaryNode(Integer e, BinaryNode l, BinaryNode r) { 
			this.left = l;
			this.right = r;
			this.element = e;
		}

		public String toString() { return this.element.toString(); }
		public Integer getElement() { return this.element; }
		public void setElement(Integer e) { this.element = e; }

		public BinaryNode getLeft() { return this.left; }
		public BinaryNode getRight() { return this.right; }

		public void setLeft(BinaryNode newChild) { this.left = newChild; }
		public void setRight(BinaryNode newChild) { this.right = newChild; }
	}



	/////////////////////////


	private int size;
	BinaryNode root;


	/** Empty constructor */
	public BinarySearchTree() {
		this.size = 0;
		this.root = null;
	}



	// Utility methods:

	/** Returns true if there are no elements in this tree. */
	public boolean isEmpty() {
		return this.size==0;
	}


	/** Create a clone 
	 * of this current binary tree,
	 * and return it as a new binary tree.
	 */
	public BinarySearchTree clone() { 
		BinarySearchTree btree = new BinarySearchTree();
		btree.root = clone(this.root);
		return btree;
	}

	private BinaryNode clone(BinaryNode node) { 
		if(node==null) { 
			// Base case: leaf node 
			return null;
		} else {
			// Recursive case: non-leaf node. 
			// Apply definition of tree, and clone the subtrees.
			return new BinaryNode(node.getElement(), 
								clone(node.getLeft()), 
								clone(node.getRight()));
		}
	}

	/** Prints the tree to the console. */
	public void printTree() {
		printTree_r(this.root, 0);
	}

	/** Prints the tree to the console recursively, 
	 * one line per node.
	 */
	private void printTree_r(BinaryNode node, int depth) { 
		if(node==null) { 
			return;
		} else {
			// perform visit action on this node
			System.out.println(space(4*depth)+node);
			// perform visit action on this node's children
			if(node.getLeft()!=null) { 
				System.out.println(space(4*(depth+1))+"L child:");
			}
			printTree_r(node.getLeft(),  depth+1);

			if(node.getRight()!=null) { 
				System.out.println(space(4*(depth+1))+"R child:");
			}
			printTree_r(node.getRight(), depth+1);
		}

	}

	/** Just make some spaces. */
	private String space(int n) { 
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) { sb.append(" "); }
		return sb.toString();
	}


	// Non-recursive versions of findMin/findMax

	/** Private method that returns the smallest element in the tree.
	 * This assumes the tree is sorted.
	 * */
	private BinaryNode findMin(BinaryNode node) { 
		if(node!=null) { 
			while(node.getLeft() != null) {
				node = node.getLeft();
			}
		}
		return node;
	}

	/** Private method that returns the largest element in the tree. 
	 * This assumes the tree is sorted.
	 * */
	private BinaryNode findMax(BinaryNode node) { 
		if(node!=null) { 
			while(node.getRight() != null) {
				node = node.getRight();
			}
		}
		return node;
	}


	// Recursive versions of findMin/findMax

	/** Private method that returns the smallest element in the tree, recursively.
	 * This assumes the tree is sorted.
	 * */
	private BinaryNode findMin_r(BinaryNode node) { 
		if(node==null){
			// base case: we're nobody
			return null;
		}
		if(node.getLeft()==null) { 
			// base case: it's us
			return node;
		}
		// recursive case:
		// keep going
		return findMin_r(node.getLeft());
	}

	/** Private method that returns the largest element in the tree, recursively. 
	 * This assumes the tree is sorted.
	 * */
	private BinaryNode findMax_r(BinaryNode node) { 
		if(node==null){
			// base case: we're nobody
			return null;
		}
		if(node.getRight()==null) { 
			// base case: it's us
			return node;
		}
		// recursive case:
		// keep going
		return findMax_r(node.getRight());
	}






	// Insert methods:



	/** Insert x into the tree, ignoring duplicates. */
	public void insert(Integer x) {

		if(isEmpty()) {
			// Just... handle it.
			this.root = new BinaryNode(x,null,null);
			this.size++;

		} else {

			// Insert at the root, let recursion sort them out 
			insert_r(x, this.root);

		}
	}


	private void insert_r(Integer x, BinaryNode node) { 
		// Key to getting the weirdness worked out 
		// was not dealing with the null case -
		// once we get to the null pointer,
		// we lose track of what we were originally pointing to 
		// (like, the root node or the child node or whatever).
		// Instead, check for the null case, and handle it right there.

		if(x < node.getElement()) {

			// Go left or insert left
			if(node.left==null) { 
				node.left = new BinaryNode(x,null,null);
				size++;
			} else {
				insert_r(x, node.left);
			}

		} else if(x > node.getElement()) { 

			// Go right
			if(node.right==null) { 
				node.right = new BinaryNode(x,null,null);
				size++;
			} else {
				insert_r(x, node.right);
			}

		} else { 
			// Matches something in the tree
			// Do nothing
		}
	}



	// Search methods:

	/** Returns true if this tree contains integer x. */
	public boolean contains(Integer x) {
		return contains(x, this.root);
	}

	/** Recursive contains method. */
	private boolean contains(Integer x, BinaryNode t) {
		// Recursive methods must handle the case of an empty tree.
		if(t==null) { 
			// Base case #1 - I'm empty
			return false;

		} else if( x < t.getElement() ) {
			// Go left
			return contains(x, t.getLeft());

		} else if( x > t.getElement() ) {
			// Go right
			return contains(x, t.getRight());

		} else { 
			// Base case #2 - equal
			return true;
		}
	}





	// Remove methods:

	public void remove(Integer x) { 
		if(isEmpty()) { 
			return;
		} else {
			remove_r(x, this.root);
		}
	}

	/** Private recursive removal operation. 
	 *  As with many data structures,
	 *  removal is tricky.
	 *
	 *  If node is a leaf - delete immediately.
	 *  If the node has one child - adjust parent's link to bypass to child.
	 *  If the node has two children:
	 *  - Replace the data of this node with smallest data of right subtree.
	 *  - Recursively delete that node, which is now empty.
	 *  - Smallest node in right subtree cannot have left child
	 *
	 *  x is item to remove. node is root of subtree. 
	 * */
	private void remove_r(Integer x, BinaryNode node) { 
		// Removal of node from binary tree must deal with a few cases:
		// - Empty tree case (dealt with above)
		// - Null node case (did not find)
		// - Keep going case (our key is not equal to node's key)
		// - 0 children
		// - 1 child
		// - 2 children 
		//
		// To remove the element, need a pointer to its parent,
		// then we decide whether to attach anything.

		System.out.println("Removing integer " + x + " from node " + node);
		System.out.println("    node.left = "+node.getLeft());
		System.out.println("    node.right = "+node.getRight());

		if(node==null) { 
			// Item not found, return nothing to caller
			return;
		} 

		if( x < node.getElement() ) { 
			// Not equal, keep removing left
			if(node.left==null) { 
				return;
			} else {
				remove_r(x, node.left);
			}

		} else if( x > node.getElement() ) {
			// Not equal, keep removing right
			if(node.right==null) { 
				return;
			} else {
				remove_r(x, node.right);
			}

		} else {

			// We found our node

			if(node.getLeft()!=null && node.getRight()!=null) {
				System.out.println("Found our node: two children case.");
				// Equal: two children case.
				// Find smallest value in right subtree,
				// set current node value to it.
				BinaryNode replacement = findMin(node.getRight());
				System.out.println("Replacing "+node+" with: " + replacement);
				Integer replacementVal = replacement.getElement();
				node.setElement(replacementVal); // set this node equal to replacement value
				remove_r(replacementVal, node.getRight()); // remove this node from subtree
				size--;

			} else if(node.getLeft()==null && node.getRight()==null) { 
				System.out.println("Found our node: no children case.");
				// Removing a node with no children 
				node = null;
				size--;

			} else {
				System.out.println("Found our node: one child case.");
				// Removing a node with one child.
				// Doing so without the parent pointer.
				if(node.getLeft()==null) { 
					node.setElement( node.getRight().getElement() );
					node.setRight( node.getRight().getRight() );
				} else {
					node.setElement( node.getLeft().getElement() );
					node.setLeft( node.getLeft().getLeft() );
				}
				size--;
			}

		}// end found 
	}




}








