import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

/** 
 * (Concrete) Linked binary tree class. 
 * 
 * Implements a concrete linked binary tree class
 * that extends the abstract binary tree class.
 *
 * The linked binary tree is a concrete implementation of the 
 * abstract binary tree class.
 *
 * Starts with a Node class, which represents stuff in the binary tree.
 * (Vertices in the directed acyclic tree graph.)
 * The Node class is a concrete implementation of the 
 * Position interface.
 *
 *
 * IMPORTANT NOTES:
 *	- Implements interface that implements another interface
 *	- Node class is protected
 *	- Protected createNode factory method 
 *	- Protected validate method
 *  - Public attach, remove
 *
 *
 */
public class LinkedBinTree<E> extends AbstractBinaryTree<E> {


	public static void main(String[] args) { 
		LinkedBinTree<Integer> t = new LinkedBinTree<Integer>();
	}


	/////////////////////////////////////////////////
	// Node class

	/**
	 * Node class - implements a Position interface.
	 *
	 * This is a concrete implementation of 
	 * positions in a binary tree that compose 
	 * a linked binary tree.
	 */
	protected static class Node<E> implements Position<E> {
		// No need to expose things.
		// (in general, how do you test a private/protected class?)
		private E element;
		private Node<E> parent; // pointer to parent node
		private Node<E> left;  // pointer to left child
		private Node<E> right; // pointer to right child
		// constructor with element and neighbors
		public Node(E e, Node<E> above, Node<E> lefty, Node<E> righty) { 
			element = e;
			parent = above;
			left = lefty;
			right = righty;
		}
		// get methods - one for each attribute
		public E getElement() { return element; }
		public Node<E> getParent() { return parent; }
		public Node<E> getLeft() { return left; }
		public Node<E> getRight() { return right; }
		// update methods
		public void setElement(E e) { element = e; }
		public void setParent(Node<E> up) { parent = up; }
		public void setLeft(Node<E> lefty) { left = lefty; }
		public void setRight(Node<E> righty) { right = righty; }

	}




	////////////////////////////////////////////
	// Utility method


	/** Factory function: make a new node storing element e. */
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) { 
		return new Node<E>(e, parent, left, right);
	}




	////////////////////////////////////////////
	// Linked Binary Tree class:


	// Linked binary tree instance variables:
	// - protect root and size
	protected Node<E> root; // = null
	private int size; // = 0 

	// Linked binary tree default constructor - empty tree
	public LinkedBinTree() {}


	
	// useful/accessor methods

	public int size() { return size; }
	public boolean isEmpty() { return size==0; }




	// Get methods for useful nodes:
	// root, right, 
	// left, parent

	/** Returns the position of tree root. */
	public Position<E> root() {
		return root; 
	}

	/** Returns the position of p's right child. */
	public Node<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	/** Returns the position of p's left child. */
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	/** Returns the position of p's parent. */
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}



	// Binary tree utility methods

	/** Validate position and return as node. 
	 * Non-public utility. */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		/*
		if(!(p.instanceOf(Node))) {
			throw new IllegalArgumentException("Not a valid position type.");
		}
		*/
		Node<E> node = (Node<E>)p;
		if(node.getParent() == node) { 
			throw new IllegalArgumentException("p is no longer in the tree.");
		}
		return node;
	}




	// These are all O(1):
	// - addRoot
	// - addLeft
	// - addRight
	// - set
	// - attach


	// Add methods for useful nodes:
	// this,
	// root, right,
	// left, parent

	/** Add a root node to an empty tree. */
	public Node<E> addRoot(E e) throws IllegalStateException {
		if(!isEmpty()) {
			throw new IllegalStateException("Tree is not empty");
		}
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/** Add a left child to the specified position in the tree. */
	public Node<E> addLeft(Position<E> p, E e) throws IllegalStateException { 
		Node<E> parent = validate(p);
		if(left(parent)!=null) {
			throw new IllegalStateException("Left child already exists!");
		}
		Node<E> c = createNode(e, parent, null, null);
		parent.setLeft(c);
		return c;
	}

	/** Add a right child to the specified position in the tree. */
	public Node<E> addRight(Position<E> p, E e) throws IllegalStateException { 
		Node<E> parent = validate(p);
		if(right(parent)!=null) { 
			throw new IllegalStateException("Right child already exists!");
		}
		Node<E> c = createNode(e, parent, null, null);
		parent.setRight(c);
		return c;
	}


	/** At this node, p, replace the element/data with e, and return the old/replaced value. */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	/** Attach trees t1 and t2 as left and right subtrees of external node (leaf node) p. */
	public void attach(Position<E> p, LinkedBinTree<E> t1, LinkedBinTree<E> t2) throws IllegalArgumentException {

		Node<E> node = validate(p);
		if(isInternal(p)) {
			throw new IllegalArgumentException("Position p must be a leaf.");
		}
		size += t1.size() + t2.size();
		if(!t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if(!t2.isEmpty()) { 
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}

	/** Remove the node at position p and replace the node with its (single) child. 
	 * Throws IllegalArgumentException if 2 children. 
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if(numChildren(p)==2) { 
			throw new IllegalArgumentException("Position p cannot be removed, has two children");
		}

		// Get the correct child
		Node<E> child = ( node.getLeft()==null ? node.getRight() : node.getLeft() );
		if(child!=null) {
			// Child grand-parent becomes parent
			child.setParent(node.getParent()); 
		}
		if(node==root) { 
			// Child becomes root
			root = child; 
		} else { 
			Node<E> parent = node.getParent();
			if(node==parent.getLeft()) {
				parent.setLeft(child);
			} else {
				parent.setRight(child);
			}
		}
		size--;
		E temp = node.getElement();

		// Garbage collection
		node.setElement(null); 
		node.setLeft(null);
		node.setRight(null);

		// Defunct mode 
		node.setParent(node); 
		return temp;
	}




	///////////////////////////////////
	// Binary Tree Iterators
	//
	// We still have not defined a positions() method.
	// We define a way to iterate over all positions 
	// in the tree here.


	// When user calls positions(), we should return an iterable container
	// with all the positions.


	/** Define a method that returns an iterable Position pointer that 
	 * iterates through the tree in the specified order. */
	public Iterable<Position<E>> positions() { return preorder(); }



	// Define some tree traversal methods below.
	// We can use whichever one we want to generate all the positions in the tree.
	// These methods should be private.


	/** Pre-order tree traversal. 
	 *
	 * This returns an iterable.
	 * Pre-order means the visit action is performed on the node
	 * before any of its children are visited. 
	 * This is a recursive method. */ 
	private Iterable<Position<E>> preorder() {
		List<Position<E>> snapshot = new LinkedList<Position<E>>();
		preorderSubtree(root(), snapshot);
		return snapshot;
	}

	/** Utility method for pre-order tree traversal. */
	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) { 
		// Base case is, no children, no loop.
		// Recursive case is, this will be called on child nodes.

		// 1. Perform visit action for Position p
		snapshot.add(p);

		// 2. Recurse through children
		for(Position<E> c : children(p)) {
			preorderSubtree(c,snapshot);
		}
	}


	/** Post-order tree traversal. 
	 *
	 * Returns an iterable.
	 *
	 * Post-order means the visit action happens on the node
	 * AFTER all the children have been visited.
	 * This is a recursive method. */
	public Iterable<Position<E>> postorder() { 
		List<Position<E>> snapshot = new LinkedList<Position<E>>();
		postorderSubtree(root(), snapshot);
		return snapshot;
	}

	/** Utility method for post-order tree traversal.  */
	private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) { 
		// 1. Recurse through children
		for(Position<E> c : children(p)) {
			postorderSubtree(c,snapshot);
		}

		// 2. Perform visit action for Position p
		snapshot.add(p);
	}

	/** In-order tree traversal. */
	public Iterable<Position<E>> inorder() { 
		List<Position<E>> snapshot = new LinkedList<Position<E>>();
		inorderSubtree(root(), snapshot);
		return snapshot;
	}

	/** Utility method for in-order tree traversal.  */
	private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) { 
		// 1. Recurse through left child
		inorderSubtree(left(p),snapshot);

		// 2. Perform visit action for Position p
		snapshot.add(p);

		// 3. Recurse through right child
		inorderSubtree(right(p),snapshot);
	}

	/** Breadth-first tree traversal. 
	 *
	 * Breadth first traversal/search uses a queue.
	 * Classic fencepost.
	 * */
	public Iterable<Position<E>> bft() {
		// Pseudocode:
		//
		// Fencepost algorithm:
		//
		// Plant a fencepost by starting with root node
		// Perform visit action on root node
		// Iterate over the children, add them to the queue. 
		// While queue not empty, 
		//			Pop from the queue, 
		//			perform visit action, 
		//			iterate over the children, 
		//			and add them to the queue.
		// Done.
	
		List<Position<E>> snapshot = new LinkedList<Position<E>>();

		// Use the Position interface, rather than the Node class, 
		// so that you aren't tied to a particular implementation.
		//
		// Create Grand Master Q
		Queue<Position<E>> q = new LinkedList<Position<E>>();

		// Plant a fencepost by starting with the root node.
		// Set the pointer p to root.
		// Perform action on the node, then add children of the node.
		Position<E> p = root();

		// Perform visit action on point
		snapshot.add(p);

		// Enqueue children of point in queue
		for(Position<E> c : children(p)) {
			q.add(c);
		}

		// Mind your p's and q's.
		while(!q.isEmpty()) { 
			p = q.remove();

			// Perform visit action on point p
			snapshot.add(p);

			for(Position<E> c : children(p)) {
				q.add(c);
			}
		}

		return snapshot;
	}




	// Finally, we can now define an iterator 
	// that iterates over the elements, rather than the positions,
	// of the Binary Tree.
	// 
	// This uses the positions() method and traversals we defined above.


	/** Element iterator class for iterating over elements, rather than positions, in a tree. */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> piter;
		public ElementIterator() { 
			piter = positions().iterator();
		}
		/** Returns true if there is a next position. */
		public boolean hasNext() { return piter.hasNext(); }
		/** Returns the element at the next node in the tree. */
		public E next() { return piter.next().getElement(); }
		/** Removes the selected position from the tree - yikes. */
		public void remove() { piter.remove(); }
	}

	/** Define the Linked Binary Tree iterator to be 
	 * an element iterator, not a position iterator. */
	public Iterator<E> iterator() { return new ElementIterator(); }

}



