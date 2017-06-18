/**
 * Abstract tree class.
 *
 * Provides concrete implementation of some 
 * easy-to-define methods.
 *
 * Provides an actual class that can be 
 * extended, slightly more useful than interfaces,
 * this method is a good place to define 
 * concrete implementations of virtual methods 
 * that are common across all trees.
 *
 * Note that this is not an endpoint, but just a stop
 * along the way toward implementing a full,
 * concrete tree type.
 *
 * This would be simpler, but for the fact that we're
 * exploring ways of using object oriented programming 
 * principles and featrues to make things more extensible.
 *
 * IMPORTANT NOTES:
 *  - public abstract class
 */
public abstract class AbstractTree<E> implements Tree<E> {

	/** Return true if position is internal node. */
	public boolean isInternal(Position<E> p) { 
		return numChildren(p)>0;
	}

	/** Return true if position is leaf node. */
	public boolean isExternal(Position<E> p) {
		return numChildren(p)==0;
	}

	/** Return true if this tree position is root. */
	public boolean isRoot(Position<E> p) { 
		return p==root(); 
	}

	/** Boolean: is it an empty tree? */
	public boolean isEmpty() {
		return size()==0;
	}

	/** Recursive implementation of depth. */
	/*
	public int depth(Position<E> p) { 
		if(p==root()) {
			// base case
			return 0; 
		} else {
			// recursive case
			return 1 + depth(parent(p));
		}
	}
	*/

	/** A bad height method, quadratic complexity class. 
	 * Depth is called on each leaf.
	 * Worst case depth is, depth ~ n.
	 * Worst case height is, n_external ~ n.
	 * Combined, worst case is ~ n^2.
	 * */
	private int heightBad() { 
		int h = 0;
		for(Position<E> p : positions()) { 
			if(isExternal(p)) { 
				h = Math.max(h, depth(p));
			}
		}
		return h;
	}

	/** Recursive implementation of height (max distance from position to leaf).
	 *
	 * This finds the height (maximum distance
	 * from position to leaf) in a top-down way,
	 * extending all the way to the end,
	 * then accumulating integers as it returns back
	 * to the original root caller.
	 */
	public int height(Position<E> p) {
		int h = 0;
		for(Position<E> c : children(p)) { 
			// see what new height is for this child 
			int newh = 1 + height(c);
			// be greedy
			h = Math.max(h, newh);
		}
		return h;
	}

	/** Get the height (max distance from root to leaf) of the entire tree. */
	public int height() {
		return height(root());
	}

	/** Get the depth (distance from position to root) of a position. */
	public int depth(Position<E> p){ 
		if(isRoot(p)) {
			return 0;
		} else {
			return 1 + depth(parent(p));
		}
	}



}
