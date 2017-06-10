public interface BinaryTree<E> extends Tree<E> {
	public Position<E> left(   Position<E> p) throws IllegalArgumentException;
	public Position<E> right(  Position<E> p) throws IllegalArgumentException;
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
