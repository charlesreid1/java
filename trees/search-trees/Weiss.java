public class Weiss { 
}

/** 
 * Binary search tree 
 * implemented using integers.
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

		public setLeft(BinaryNode newChild) { this.left = newChild; }
		public setRight(BinaryNode newChild) { this.left = newChild; }
	}



	/////////////////////////


	int size;
	BinaryNode root;


	/** Empty constructor */
	public BinarySearchTree() {
		this.size = 0;
		this.BinaryNode = null;
	}



	// Utility methods:

	/** Returns true if there are no elements in this tree. */
	public boolean isEmpty() {
		return this.size==0;
	}

	/** Prints the tree to the console. */
	public void printTree() {}

	/** Returns the smallest element in the tree. */
	public Integer findMin() { }

	/** Returns the largest element in the tree. */
	public Integer findMax() { }





	// Insert methods:

	/** Insert x into the tree, ignoring duplicates. */
	public void insert(Integer x) {
		// Insert at the root, let recursion sort them out 
		insert(x, this.root);
	}

	/** Insert at binary node t */
	public void insert(Integer x, BinaryNode t) {
		// Fundamental property of binary search trees:
	}




	// Search methods:

	/** Returns true if this tree contains integer x. */
	public boolean contains(Integer x) {
		return contains(x, this.root);
	}

	public boolean contains(Integer x, BinaryNode t) {

		if(t==null) { 
			return false;
		} else if( x < t.getElement() ) {

	}





	// Remove methods:

	public void remove(Integer x) { 
		return remove(x, this.root);
	}

	public void remove(Integer x, BinaryNode t) { 

	}




}








