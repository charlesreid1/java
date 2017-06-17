/**
 * Class that implements tests of the binary search tree
 * implemented following Weiss, C++ Data Structures.
 *
 * See p. 133 of Weiss book.
 */
public class Weiss { 
	public static void main(String[] args) { 
		BinarySearchTree b = new BinarySearchTree();
		b.insert(new Integer(5));
		b.insert(new Integer(8));
		b.insert(new Integer(48));
		b.insert(new Integer(43));
		b.insert(new Integer(49));
		b.insert(new Integer(44));
		b.insert(new Integer(45));
		b.insert(new Integer(58));
		b.insert(new Integer(68));
		b.insert(new Integer(108));
		b.insert(new Integer(138));
		b.insert(new Integer(78));
		b.insert(new Integer(-8));
		b.insert(new Integer(-10));
		b.insert(new Integer(-2));
		b.insert(new Integer(-3));
		b.printTree();
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
		public void setRight(BinaryNode newChild) { this.left = newChild; }
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
			printTree_r(node.getLeft(),  depth+1);
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

			System.out.println("recursive insertion of "+x);
			System.out.println("what is root? "+this.root);
			System.out.println("what is root left? "+this.root.getLeft());
			System.out.println("what is root right? "+this.root.getRight());

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
		remove(x, this.root);
	}

	/** Private recursive removal operation. 
	 *  As with many data structures,
	 *  removal is tricky.
	 *
	 *  If node is a leaf - delete immediately.
	 *  If the node has one child - adjust parent's link to bypass to child.
	 *  If the node has two childre:
	 *  - Replace the data of this node with smallest data of right subtree.
	 *  - Recursively delete that node, which is now empty.
	 *  - Smallest node in right subtree cannot have left child
	 *
	 *  x is item to remove. node is root of subtree. 
	 * */
	private void remove(Integer x, BinaryNode node) { 

		if(node==null) { 
			// Item not found, return nothing to caller
			return;
		} 

		if( x < node.getElement() ) { 
			// Not equal, keep looking.
			// Go left
			remove(x, node.getLeft());
		} else if( x > node.getElement() ) {
			// Not equal, keep looking.
			// Go right
			remove(x, node.getRight());
		} else if(node.getLeft()!=null && node.getRight()!=null) {
			// Equal: two children case.
			// Find smallest value in right subtree,
			// set current node value to it.
			BinaryNode min = findMin(node.getRight());
			node.setElement(min.getElement());
		} else {
			// Equal: one child case
			//BinaryNode old = node;
			// Pick left or right.
			node = (node.getLeft()!=null)?node.getLeft():node.getRight();
			size--;
		}
	}




}







