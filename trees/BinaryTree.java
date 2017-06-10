public interface BinaryTree extends Tree {
	public Position left(Position p);
	public Position right(Position p);
	public Position sibling(Position p);
	// iterable
	public ArrayList<Position> children(Position p) { 
		ArrayList<Position> chilluns = new ArrayList<Position>();
		chilluns.add(left(p));
		chilluns.add(right(p));
		return chilluns;
	}
}
