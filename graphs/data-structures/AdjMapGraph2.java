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
 *
 */
public class AdjMapGraph2<V,E> implements IGraph<V,E> {


	/**
	 * Adjacency Map Vertex.
	 */
	public class Vertex<V> implements IVertex<V> {
	
		// Not storing a position pointer
		private V element;
		private boolean isDirected;
		private Map<IVertex<V>, IEdge<E>> outgoing, incoming;
	
		public Vertex(V element, boolean isDirected) { 
			this.element = element;
			outgoing = new HashMap<IVertex<V>, IEdge<E>>();
			if(isDirected) { 
				// need separate incoming and outgoing maps
				incoming = new HashMap<IVertex<V>, IEdge<E>>();
			} else {
				outgoing = new HashMap<IVertex<V>, IEdge<E>>();
			}
		}
	
		public V getElement() { 
			return element;
		}
	
		public Map<IVertex<V>,IEdge<E>> getIncoming() {
			return incoming;
		}
	
		public Map<IVertex<V>,IEdge<E>> getOutgoing() {
			return outgoing;
		}
	
	}




	/**
	 * Adjacency Map Edge.
	 */
	public class Edge<E> implements IEdge<E> {
	
		// Not storing a position pointer
		private E element;
		private ArrayList<IVertex<V>> endpoints; 
	
		public Edge(IVertex<V> u, IVertex<V> v, E element) {
			this.element = element;
			endpoints = new ArrayList<IVertex<V>>();
			endpoints.add(u);
			endpoints.add(v);
		}
	
		public E getElement() {
			return element;
		}
	
		public ArrayList<IVertex<V>> getEndpoints() {
			return endpoints;
		}
	}



	/**
	 * Adjacency Map Graph class.
	 */

	private boolean isDirected;
	private LinkedList<Vertex<V>> vertexList;
	private LinkedList<Edge<E>> edgeList;

	/** Construct an empty graph. */
	public AdjMapGraph2(boolean directed) {
		isDirected = directed;
		vertexList = new LinkedList<Vertex<V>>();
		edgeList = new LinkedList<Edge<E>>();
	}



	// Quite possibly the most important methods,
	// since they may save boatloads of headaches.

  	@SuppressWarnings({"unchecked"})
  	private Vertex<V> validate(IVertex<V> v) {
  	  	if (!(v instanceof Vertex)) {
			throw new IllegalArgumentException("Invalid vertex");
		}

		// Safe cast:
  	  	Vertex<V> vert = (Vertex<V>) v;

		// We COULD check if this vertex is actually in this graph...
		// naah.
  	  	return vert;
  	}

  	@SuppressWarnings({"unchecked"})
  	private Edge<E> validate(IEdge<E> e) {
		if(!(e instanceof Edge)) { 
			throw new IllegalArgumentException("Invalid edge");
		}
		// Safe cast:
		Edge<E> edge = (Edge<E>) e;

		// We COULD check if this edge is actually in this graph...
		// naah.
  	  	return edge;
	}




	// Methods to Modify Graph

	/** Create a Vertex with the given element and insert it into the graph. */
	public IVertex<V> insertVertex(V element) {
		Vertex<V> v = new Vertex<>(element, isDirected);
		vertexList.add(v);
		// Skip position.
		return (IVertex<V>) v;
	}

	/** Create an Edge with the given element between nodes u and v. */
	public IEdge<E> insertEdge(IVertex<V> u, IVertex<V> v, E element) throws IllegalArgumentException {
		if(getEdge(u,v)==null) { 
			Edge<E> e = new Edge<>(u, v, element);
			edgeList.add(e);
			Vertex<V> origin = validate(u);
			Vertex<V> dest = validate(v);
			// Skip position.
			origin.getOutgoing().put(v, e);
			dest.getOutgoing().put(u, e);
			return (IEdge<E>) e;
		} else {
			throw new IllegalArgumentException("There is already an edge between u and v!");
		}
	}

	/** Remove a Vertex from the graph, and delete all incident edges. */
	public void removeVertex(IVertex<V> vert) throws IllegalArgumentException {
		Vertex<V> v = validate(vert);
		for(IEdge<E> e : v.getOutgoing().values()) {
			removeEdge(e);
		}
		for(IEdge<E> e : v.getIncoming().values()) {
			removeEdge(e);
		}
		vertexList.remove(vertexList.indexOf(v));
	}

	/** Remove an Edge from the graph. */
	public void removeEdge(IEdge<E> edge) throws IllegalArgumentException {
		Edge<E> e = validate(edge);

		ArrayList<IVertex<V>> vertices = e.getEndpoints();

		// Remove vertices at edge endpoints from their incoming/outgoing list
		Vertex<V> v0 = validate(vertices.get(0));
		Vertex<V> v1 = validate(vertices.get(1));
		v0.getOutgoing().remove(v1);
		v1.getIncoming().remove(v0);

		// Remove edge from list of edges
		edgeList.remove(edgeList.indexOf(e));
	}



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
	public IEdge<E> getEdge(IVertex<V> u, IVertex<V> v) {
		Vertex<V> vxu = validate(u);
		return vxu.getOutgoing().get(v);
	}

	/** Return the vertices at the endpoints of the edge e. */
	public ArrayList<IVertex<V>> endpoints(IEdge<E> edge) { 
		Edge<E> e = validate(edge);
		return e.getEndpoints();
	}

	/** Return the vertex opposite a given vertex. */
	public IVertex<V> opposite(IVertex<V> vertex, IEdge<E> edge) throws IllegalArgumentException {
		Edge<E> e = validate(edge);
		ArrayList<IVertex<V>> endpoints = e.getEndpoints();
		if(endpoints.get(0).equals(vertex)) {
			return endpoints.get(0);
		} else if(endpoints.get(1).equals(vertex)) {
			return endpoints.get(1);
		} else {
			throw new IllegalArgumentException("The vertex you passed is not incident to the edge you passed.");
		}
	}



	// Incoming/Outoing Edges from Vertex:

	/** Return the incoming edge degree of the vertex v. */
	public int inDegree(IVertex<V> v) {
		return validate(v).getIncoming().size();
	}

	/** Return the outgoing edge degree of the vertex v. */
	public int outDegree(IVertex<V> v) {
		return validate(v).getOutgoing().size();
	}

	/** Return an iterable set of Edge objects incoming to the vertex v. */
	public Iterable<IEdge<E>> inEdges(IVertex<V> v) {
		return validate(v).getIncoming().values();
	}

	/** Return an iterable set of Edge objects outgoing from the vertex v. */
	public Iterable<IEdge<E>> outEdges(IVertex<V> v) {
		return validate(v).getOutgoing().values();
	}



	public String toString() { 
		StringBuilder sb = new StringBuilder();
		for(IVertex<V> v : vertexList) {
			sb.append("Vertex "+v.getElement()+"\n");
			if(isDirected) { 
				sb.append(" [outgoing]");
			}
			sb.append(" "+outDegree(v)+" adjacent vertices.");
			for(IEdge<E> e : outEdges(v)) {
				sb.append(String.format(" (%s,%s)", opposite(v,e).getElement(), e.getElement()));
			}
			sb.append("\n");
			if(isDirected) { 
				sb.append(" [incoming]");
				sb.append(" "+inDegree(v)+" adjacent vertices.");
				for(IEdge<E> e : inEdges(v)) {
					sb.append(String.format(" (%s,%s)", opposite(v,e).getElement(), e.getElement()));
				}
				sb.append("\n");
			}
		}

		return sb.toString();
	}

}
