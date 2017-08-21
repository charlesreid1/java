import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * Adjacency Map Graph.
 *
 * This maintains two lists - one of all vertices, one of all edges.
 * Each vertex stores a map of vertices-to-edges.
 * Each edge stored by a vertex is incident to that vertex.
 */
public class AdjMapGraph<V,E> implements IGraph<V,E> {



	public static void main(String[] args) { 
		System.out.println("Testing.");
	}



	/**
	 * Adjacency Map Vertex.
	 */
	class Vertex<V> implements IVertex<V> {
	
		// Not storing a position pointer
		private V element;
		private boolean isDirected;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
	
		public Vertex(V element, boolean isDirected) { 
			this.element = element;
			outgoing = new HashMap<Vertex<V>, Edge<E>>();
			if(isDirected) { 
				// need separate incoming and outgoing maps
				incoming = new HashMap<Vertex<V>, Edge<E>>();
			} else {
				outgoing = new HashMap<Vertex<V>, Edge<E>>();
			}
		}

		public V getElement() { 
			return element;
		}
	
		public Map<Vertex<V>,Edge<E>> getIncoming() {
			return incoming;
		}
	
		public Map<Vertex<V>,Edge<E>> getOutgoing() {
			return outgoing;
		}
	
	}

	/**
	 * Adjacency Map Edge.
	 */
	class Edge<E> implements IEdge<E> {

		// Not storing a position pointer
		private E element;
		private ArrayList<Vertex<V>> endpoints; 

		public Edge(Vertex<V> u, Vertex<V> v, E element) {
			this.element = element;
			endpoints = new ArrayList<Vertex<V>>();
			endpoints.add(u);
			endpoints.add(v);
		}

		public E getElement() {
			return element;
		}

		public ArrayList<Vertex<V>> getEndpoints() {
			return endpoints;
		}
	}



	/**
	 * Adjacency Map Graph class.
	 */

	private boolean isDirected;
	private LinkedList<Vertex<V>> vertexList;
	private LinkedList<Edge<E>> edgeList;

	public AdjMapGraph(boolean directed) {
		isDirected = directed;
		vertexList = new LinkedList<Vertex<V>>();
		edgeList = new LinkedList<Edge<E>>();
	}



	// Methods to Modify Graph



	// Vertex/Edge Utility Methods:

	/** Return number of vertices in graph. */
	public int numVertices() { 
		return vertexList.size();
	}

	/** Return number of edges in graph. */
	public int numEdges() { 
		return edgeList.size();
	}

	/** Return all vertices in graph. */
	public Iterable<Vertex<V>> vertices() { 
		return vertexList;
	}

	/** Return all edges in graph. */
	public Iterable<Edge<E>> edges() { 
		return edgeList;
	}



	// Vertices to Edges and Vice-Versa:

	/** Return edge connecting vertex u to vertex v. */
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
		return u.getOutgoing().get(v);
	}

	/** Return the vertices at the endpoints of the edge e. */
	public ArrayList<Vertex<V>> endpoints(Edge<E> e) { 
		return e.getEndpoints();
	}

	/** Return the vertex opposite a given vertex. */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		ArrayList<Vertex<V>> endpoints = e.getEndpoints();
		if(endpoints.get(0).equals(v)) {
			return endpoints.get(0);
		} else if(endpoints.get(1).equals(v)) {
			return endpoints.get(1);
		} else {
			throw new IllegalArgumentException("The vertex you passed is not incident to the edge you passed.");
		}
	}



	// Incoming/Outoing Edges from Vertex:

	/** Return the incoming edge degree of the vertex v. */
	public int inDegree(Vertex<V> v) {
		return v.getIncoming().size();
	}

	/** Return the outgoing edge degree of the vertex v. */
	public int outDegree(Vertex<V> v) {
		return v.getOutgoing().size();
	}

	/** Return an iterable set of Edge objects incoming to the vertex v. */
	public Iterable<Edge<E>> inEdges(Vertex<V> v) {
		return v.getIncoming().values();
	}

	/** Return an iterable set of Edge objects outgoing from the vertex v. */
	public Iterable<Edge<E>> outEdges(Vertex<V> v) {
		return v.getOutgoing().values();
	}

}
