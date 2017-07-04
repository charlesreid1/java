import java.util.Iterator;
/**
 * Tree abstract data type interface.
 *
 * Note that the Position class is not defined here. 
 * It is defined in the concrete implementation 
 * of your tree.
 */
public interface Tree<E> extends Iterable<E> {

	public int size();
	public int depth(Position<E> p) throws IllegalArgumentException;
	public int height();
	public boolean isEmpty();

	public Iterator<E> iterator();
	public Iterable<Position<E>> positions();

	public Iterable<Position<E>> children(Position<E> p);

	public Position<E> root();

	public boolean isRoot(Position<E> p) throws IllegalArgumentException;
	public boolean isInternal(Position<E> p) throws IllegalArgumentException;
	public boolean isExternal(Position<E> p) throws IllegalArgumentException;

	public Position<E> parent(Position<E> p) throws IllegalArgumentException;
	public int numChildren(Position<E> p) throws IllegalArgumentException;
}
