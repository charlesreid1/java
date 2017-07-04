import java.util.Random;
/** 
 * AVL Rebalancing Binary Search Tree. 
 *
 * Adel'son-Vel'skii Landis
 * 
 * This implements an AVL tree, which is a binary 
 * search tree that is self-balancing.
 * This is accomplished through the use of 
 * a rebalance method. 
 *
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {


	public static void main(String[] args) { 
		AVLTree<Integer> t = getFullTree(5);
		t.printTree();
	}



	/** Test method: get a full binary tree with n levels. */
	public static AVLTree<Integer> getFullTree(int n) {

		AVLTree<Integer> t = new AVLTree<Integer>();
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



	protected static class ANode<E> extends Node<E> {
		int height;
		public ANode(E e, Node<E> above, Node<E> lefty, Node<E> righty) { 
			super(e, above, lefty, righty);
		}
		public int getHeight() { return height; }
		public void setHeight(int height) { this.height = height; }
		// These casts are pretty ugly. Is there a better way to do it? Probably not, if self-referential.
		// Could just let the container (Tree object) do it.
		// height(node) instead of node.height()
		public int getLeftHeight() { return  ((ANode)this.left ).getHeight(); }
		public int getRightHeight() { return ((ANode)this.right).getHeight(); }
		public boolean isBalanced() { return (Math.abs( ((ANode)this.left).getHeight() - ((ANode)this.right).getHeight()) <= 1 ); }
	}




	/////////////////////////////////////////////////

	public AVLTree() { 
		super();
	}


	/** Computing/determining height */
	protected void recomputeHeight(ANode<E> node) { 
		int h = 1 + Math.max( ((ANode)node.getLeft()).getHeight(), ((ANode)node.getRight()).getHeight());
		node.setHeight(h);
	}


	/** Get the taller of node's two children. */
	protected ANode<E> getTallerChild(ANode<E> node, boolean goLeft) { 
		int j = 0;
		if(goLeft) j = 1;
		ANode<E> aleft = (ANode)(node.getLeft());
		ANode<E> aright = (ANode)(node.getRight());
		if( (aleft.getHeight() + j) > (aright.getHeight()) ){
			return aleft;
		} else {
			return aright;
		}
	}

	/** Get the taller of node's grandchildren.  */
	protected ANode<E> getTallerGrandchild(ANode<E> node) { 
		ANode<E> child = getTallerChild(node, false);
		// if child is on left, favor left grandchild
		boolean favorLeft = (child==node.getLeft());
		return getTallerChild(child, favorLeft);
	}


	/** Recursive rebalance starts at a given node, then works 
	 * its way up the tree if there are changes that need to be 
	 * propagated to nodes further up the tree.
	 */
	protected void rebalance(ANode<E> node) { 
		while(node != null) { 
			// trivially 0 if new node
			int oldHeight = node.getHeight();
			if(!node.isBalanced()) {
				// Restructure methods will return our new root,
				// which will be our new node.
				// We pass it the taller grandchild.
				node = restructure(getTallerGrandchild(node));

				ANode<E> aleft  = ((ANode)node.getLeft() );
				ANode<E> aright = ((ANode)node.getRight());

				recomputeHeight(aleft);
				recomputeHeight(aright);
			}
			recomputeHeight(node);
			if(node.getHeight() == oldHeight) { 
				// nothing changed
			} else {
				// Height changed, propagate method to parent.
				node = (ANode)node.getParent();
			}
		}

	}






	// Relink, Rotate, Restructure:

	/** Re-link a parent and child node so they both reflect the correct relationship. */
	protected void relink(Node<E> parent, Node<E> child, boolean isLeftChild) { 
		// Set parent->child link
		if(isLeftChild) {
			parent.setLeft(child);
		} else {
			parent.setRight(child);
		}

		// Set child->parent link
		if(child !=null) {
			child.setParent(parent);
		}
	}

	/** Two-Node Rotation: rotate this node above its parent.
	 * Maintain binary search property throughout a rotation.
	 */
	protected void rotate(Node<E> node) { 

		boolean LEFT = true;
		boolean RIGHT = false;

		Node<E> x = node;
		Node<E> y = node.getParent();
		Node<E> z = y.getParent();
		if(z==null) { 
			// Rearranging at top of tree
			this.root = x;
			x.setParent(null);
		} else {
			// Rearrange so that x is direct child of z
			if(y == z.getLeft()) { 
				relink(z, x, LEFT);
			} else {
				relink(z, x, RIGHT);
			}
		}

		// Now that x is child of z, 
		// rotate x and y (and transfer middle subtree from x to y)
		if(x==y.getLeft()) { 
			// middle subtree goes from right child of x to left child of y
			Node<E> x_middle_subtree = x.getRight();
			relink( y, x_middle_subtree, LEFT);

			// Now make y right child of x
			relink(x, y, RIGHT);
		} else {
			// middle subtree goes from left child of x to right child of y
			Node<E> x_middle_subtree = x.getLeft();
			relink( y, x_middle_subtree, RIGHT);

			// Now make y left child of x
			relink( x, y, LEFT);
		}
	}
		

	/** Tri-Node Restructure: restructure this node, parent, and grandparent. 
	 * */
	protected ANode<E> restructure(ANode<E> node) { 
		Node<E> x = node;
		Node<E> y = x.getParent();
		Node<E> z = y.getParent();
		
		// Check if we have matching algnments (zig-zig)
		if( (x==y.getRight()) == (y==z.getRight()) ) {
			// y (middle node) is new subtree root
			rotate(y);
			return (ANode)y;
		} else {
			// x (end node) is new subtree root.
			// Double-rotate x to ensure it is ok.
			rotate(x);
			rotate(x);
			return (ANode)x;
		}
	}

}

