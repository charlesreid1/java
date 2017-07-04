import java.util.List;
import java.util.ArrayList;
/** 
 * Abstract binary tree class. 
 * 
 * Implements an abstract binary tree class
 * that extends the abstract tree class.
 *
 * Note that much of the real functionality 
 * is actually being left to the concrete
 * implementation of Position and BinaryTree.
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	public Position<E> sibling(Position<E> p) {
		Position<E> parent = parent(p);
		if(parent==null) {
			// root node
			return null;
		} else {
			if(left(parent)==p) {
				return right(parent);
			} else {
				return left(parent);
			}
		}
	}

	public int numChildren(Position<E> p) {
		int children = 0;
		if(left(p)!=null) { children++; }
		if(right(p)!=null){ children++; }
		return children;
	}

	public Iterable<Position<E>> children(Position<E> p) {
		int cap = 2;
		List<Position<E>> chillun = new ArrayList<Position<E>>(cap);
		if(left(p)!=null) { 
			chillun.add(left(p));
		} 
		if(right(p)!=null) { 
			chillun.add(right(p));
		}
		return chillun;
	}
}

