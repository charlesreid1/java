/**
 * Class that implements tests of the binary search tree
 * implemented following Weiss, C++ Data Structures.
 *
 * See p. 133 of Weiss book.
 */
public class Weiss { 
	public static void main(String[] args) { 
		BinarySearchTree b = new BinarySearchTree();;
		b.insert(new Integer(5));
		b.insert(new Integer(4));
		b.insert(new Integer(9));
		b.insert(new Integer(8));
		b.insert(new Integer(-2));
		b.insert(new Integer(-3));
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

		public Integer getElement() { return this.element; }

		public BinaryNode getLeft() { return this.left; }
		public BinaryNode getRight() { return this.right; }

		public void setLeft(BinaryNode newChild) { this.left = newChild; }
		public void setRight(BinaryNode newChild) { this.left = newChild; }
	}



	/////////////////////////


	int size;
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

	/** Prints the tree to the console. */
	//public void printTree() {}




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
		return findMin_r(node.getLleft());
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
		// Insert at the root, let recursion sort them out 
		insert(x, this.root);
	}

	/** Insert at binary node t */
	private void insert(Integer x, BinaryNode t) {
		// Fundamental property of binary search trees:
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

	private void remove(Integer x, BinaryNode t) { 

	}




}








