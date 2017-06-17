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
		b.insert(new Integer(-4));
		b.insert(new Integer(-6));
		b.insert(new Integer(-8));
		b.insert(new Integer(-3));
		b.insert(new Integer(45));
		b.insert(new Integer(58));
		b.insert(new Integer(68));
		b.insert(new Integer(108));
		b.insert(new Integer(138));
		b.insert(new Integer(78));
		b.insert(new Integer(-11));
		b.insert(new Integer(-10));
		b.insert(new Integer(-13));
		b.insert(new Integer(-12));
		b.insert(new Integer(-1));
		b.insert(new Integer(-2));
		b.insert(new Integer(-5));
		b.insert(new Integer(-7));
		b.insert(new Integer(-9));
		b.printTree();

		System.out.println("------------- removing 48 ");
		b.remove(new Integer(48) );
		b.printTree();
		System.out.println("------------- removing -10 ");
		b.remove(new Integer(-10) );
		b.printTree();
		System.out.println("------------- removing -2 ");
		b.remove(new Integer(-2) );
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
		// Don't deal with the null case - 
		// once we get a null pointer,
		// we are losing track of what pointed to us.
		// (this is the pitfall of following a c++ book.)
		// we can't modify the memory contents directly. 
		// modify the parent pointers.
		//
		// to remove the element,
		// we need a pointer to its parent, 
		// so we can release it and have it point 
		// at the (correct) new thing.
		// (root parent is a special/weird case.)
		//
		// We don't want to check if we have the element to remove - 
		// we want to check if we have the parent of the element to remove. 
		//
		// But...
		// Is left the element we want to remove?
		// Is right the element we want to remove?
		// These seem like the wrong question.



		if(node==null) { 
			System.out.println("Removing nothin from null");
			// Item not found, return nothing to caller
			return;
		} 

		if( x < node.getElement() ) { 

			// Not equal, keep removing left
			if(node.left==null) { 
				return;
			} else {
				System.out.println("Keep removing to the left... "+node+" -> "+node.left);
				remove_r(x, node.left);
			}

		} else if( x > node.getElement() ) {

			// Not equal, keep removing right
			if(node.right==null) { 
				return;
			} else {
				remove_r(x, node.right);
			}

		} else if(node.getLeft()!=null && node.getRight()!=null) {
			// Equal: two children case.
			// Find smallest value in right subtree,
			// set current node value to it.
			System.out.println("Removing: 2-kid case.");
			BinaryNode min = findMin(node.getRight());
			Integer eradicate = min.getElement();
			node.setElement(eradicate); // set this node equal to min
			remove_r(eradicate, node.getRight()); // remove min from subtree
			size--;

		} else {
			System.out.println("Removing: 1-kid case. " + node);
			node = null;

			//// H
			////
			////
			////
			//// Equal: one child case
			//// Pick left or right.
			//if(node.getLeft()==null) { 
			//	node = node.getRight();
			//} else {
			//	node = node.getLeft();
			//}

			size--;
		}
	}




}








