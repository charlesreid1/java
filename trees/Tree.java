/**
 * Tree abstract data type.
 *
 */
public interface Tree {
	public Position element();
	public Position root();
	public boolean isRoot(Position p);
	public Position parent(Position p);
	public int numChildren(Position p);
	public ArrayList<Position> children(Position p);
	public boolean isLeaf(Position p);
	public int size();
	public int depth(Position p);
	public boolean isEmpty();
	// iterable
	public ArrayList<Position> positions(Position p);

	public int height() { return depth(root()) };
}