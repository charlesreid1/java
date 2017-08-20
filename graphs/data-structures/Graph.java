/**
 * Graph ADT.
 */
public class Graph<V,E> {

	public static void main(String[] args) { 
	}

	public int numVertices() { 
		return 0;
	}

}

/**
 * Basic Vertex Class.
 */
class Vertex<V> {
	private V data;
	public Vertex(V data) { 
		this.data = data;
	}
	public V element() {
		return data;
	}
}

/**
 * Basic Edge Class.
 */
class Edge<E> {
	private E data;
	public Edge(E data) { 
		this.data = data;
	}
	public E element() {
		return data;
	}
}