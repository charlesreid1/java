import java.util.Random;
/** 
 *Binary Search Tree. 
 * 
 * This implements a binary search tree.
 */
public class BinarySearchTree<E extends Comparable<E>> extends LinkedBinTree<E> {

	public static void main(String[] args) { 
		BinarySearchTree<Integer> t = getBadTree(5);
		t.printTree();
	}
	
	/** Test method: get a full binary tree with n levels. */
	public static BinarySearchTree<Integer> getFullTree(int n) {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		Random r = new Random();
		int MAX = 9999;
		int MIN = 1000;
		t.addRoot(r.nextInt(MAX));
		for(int i=0; i<Math.pow(2,n); i++) { 
			t.insert( MIN + r.nextInt(MAX-MIN+1) );
		}
		return t;
	}


	public static BinarySearchTree<Integer> getBadTree(int n) {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		Random r = new Random();
		int MAX = 9999;
		int MIN = 1000;
		t.addRoot(r.nextInt(MAX));
		for(int i=0; i<Math.pow(2,n); i++) { 
			t.insert( MIN + 5*i);// r.nextInt(MAX-MIN+1) );
		}
		return t;
	}



	/////////////////////////////////////////////////




	public BinarySearchTree() {
		super();
	}




	// -------------------------




	// Non-recursive versions of findMin/findMax:

	/** Private method that returns the smallest element in the tree.
	 * This assumes the tree is sorted. It returns the left-most node.
	 * */
	protected Node<E> findMin(Node<E> node) { 
		if(node!=null) { 
			while(node.getLeft() != null) {
				node = node.getLeft();
			}
		}
		return node;
	}

	/** Private method that returns the largest element in the tree. 
	 * This assumes the tree is sorted. It returns the right-most node.
	 * */
	protected Node<E> findMax(Node<E> node) { 
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
	protected Node<E> findMin_r(Node<E> node) { 
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
	protected Node<E> findMax_r(Node<E> node) { 
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




	// -------------------------




	/** Add a node to a tree. */
	private void insert_r(E x, Node<E> node) { 

		if(x.compareTo(node.getElement())<0) {

			// Go left or insert left
			if(node.getLeft()==null) { 
				node.setLeft(new Node<E>(x,null,null,null));
				size++;
			} else {
				insert_r(x, node.getLeft());
			}

		} else if(x.compareTo(node.getElement())>0) {

			// Go right
			if(node.getRight()==null) { 
				node.setRight(new Node<E>(x,null,null,null));
				size++;
			} else {
				insert_r(x, node.getRight());
			}

		} else { 
			// Already in tree - do nothing.
		}
	}


	public void insert(E x) { 
		if(isEmpty()) { 
			// handle it
			this.root = new Node<E>(x, null, null, null);
			this.size++;
		} else {
			// insert at the root,
			// let reursion sort out the details.
			insert_r(x, this.root);
		}
	}



	// -------------------------





	/** Remove the node at position p and replace the node with its (single) child. 
	 * This overrides the parent method.
	 * */
	public E remove(E x) {
		if(isEmpty()) { 
			return null;
		} else {
			return remove_r(x, this.root);
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
	 *  target is item to remove. node is the node to remove from
	 * */
	protected E remove_r(E x, Node<E> node) {

		if(node==null) { 
			// Item not found, return nothing to caller
			return null;
		} 

		if(x.compareTo(node.getElement())<0) {
			// Not equal, keep removing left
			if(node.getLeft()==null) { 
				// bork
				return null;
			} else {
				return remove_r(x, node.getLeft());
			}

		} else if(x.compareTo(node.getElement())>0) {
			// Not equal, keep removing right
			if(node.getRight()==null) { 
				// bork
				return null;
			} else {
				return remove_r(x, node.getRight());
			}

		} else {

			// We found our node

			if(node.getLeft()!=null && node.getRight()!=null) {
				// Equal: two children case.
				// Find smallest value in right subtree,
				// set current node value to it.
				Node<E> replacement = findMin(node.getRight());
				E replacementVal = replacement.getElement();
				E doomed = node.getElement();
				node.setElement(replacementVal); // set this node equal to replacement value
				remove_r(replacementVal, node.getRight()); // remove this node from subtree

				size--;
				return doomed;

			} else if(node.getLeft()==null && node.getRight()==null) { 
				// Removing a node with no children 
				E doomed = node.getElement();
				node = null;
				
				size--;
				return doomed;

			} else {
				// Removing a node with one child.
				// Doing so without the parent pointer.
				E doomed;
				if(node.getLeft()==null) { 

					doomed = node.getElement();

					E newElement = node.getRight().getElement();
					Node<E> newRight = node.getRight().getRight();

					node.setElement(newElement);
					node.setRight(newRight);

				} else {

					doomed = node.getElement();

					E newElement = node.getLeft().getElement();
					Node<E> newLeft = node.getLeft().getLeft();

					node.setElement(newElement);
					node.setLeft(newLeft);

				}
				size--;
				return doomed;

			}

		}// end found 

	}

}


