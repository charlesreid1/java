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
public class AdjMapGraph<V,E> implements IGraph<V,E> {

   	// 
   	// 
   	// We are running into yet another problem with 
   	// class organization and layout.
   	// 
   	// Putting Vertex and Edge class in separate places
	// means the Vertex class cannot refer to an Edge instance,
	// because it doesn't have a reference to E.
	//
	// Goodrich says Vertex and Edge classes "should" be 
	// defined inside the AdjMapGraph class.
	// It is clear now that that is because that's the 
	// only way both the Edge and Vertex class will understand
	// both V and E generic types.
	//
	// However, there are problems with THAT approach,
	// specifically, it protects these classes by default,
	// so that when we try to add vertices, 
	// it returns a Vertex object, so we need to 
	// have a way to get and assign a Vertex object
	// (which we can't if we have a separate class
	// with a driver to test out the Adjacency Map Graph).
	//
	// On the other hand, we could use the interfaces
	// IVertex and IEdge, but then we have problems when we 
	// try and create an edge, and pass in two IVertex 
	// objects as the endpoints of the edge. The IVertex
	// objects can't be automatically converted to 
	// Vertex objects. They have to be cast as Vertex
	// objects. Thus, we can either keep things simple 
	// and validate every Vertex object, 
	// or we can overcomplicate things by accepting
	// either a Vertex or an IVertex object. (Seems stupid.)
	//
	// On the third hand, if we just try and power through and make
	// the Vertex and Edge class public, we have to refer to it
	// by saying AdjMapGraph.Vertex.
	//
	// Except, it actually gets worse. Because these both take 
	// generic types, we actually have to refer to the Vertex class
	// as AdjMapGraph<String,String>.Vertex<String>.
	//
	// This is a completely asinine waste of time.
	// None of these issues are present in the abstraction,
	// and the abstraction makes perfect sense.
	// It isn't until you actually roll up your sleves 
	// and try to DO SOMETHING with Java that you start to
	// run into these idiotic speed bumps.
	//
	// FINAL SOLUTION:
	//
	// ...use python?



	/**
	 * Adjacency Map Vertex.
	 */
	public class Vertex<V> implements IVertex<V> {
	
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
	public class Edge<E> implements IEdge<E> {
	
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

	/** Construct an empty graph. */
	public AdjMapGraph(boolean directed) {
		isDirected = directed;
		vertexList = new LinkedList<Vertex<V>>();
		edgeList = new LinkedList<Edge<E>>();
	}



	// Methods to Modify Graph

	/** Create a Vertex with the given element and insert it into the graph. */
	public Vertex<V> insertVertex(V element) {
		Vertex<V> v = new Vertex<>(element, isDirected);
		vertexList.add(v);
		// Skip position.
		return v;
	}

	/** Create an Edge with the given element between nodes u and v. */
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
		if(getEdge(u,v)==null) { 
			Edge<E> e = new Edge<>(u, v, element);
			edgeList.add(e);
			// Skip position.
			u.getOutgoing().put(v, e);
			v.getOutgoing().put(u, e);
			return e;
		} else {
			throw new IllegalArgumentException("There is already an edge between u and v!");
		}
	}

	/** Remove a Vertex from the graph, and delete all incident edges. */
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		for(Edge<E> e : v.getOutgoing().values()) {
			removeEdge(e);
		}
		for(Edge<E> e : v.getIncoming().values()) {
			removeEdge(e);
		}
		vertexList.remove(vertexList.indexOf(v));
	}

	/** Remove an Edge from the graph. */
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		ArrayList<Vertex<V>> vertices = e.getEndpoints();
		// Remove vertices at edge endpoints from their incoming/outgoing list
		vertices.get(0).getOutgoing().remove(vertices.get(1));
		vertices.get(1).getIncoming().remove(vertices.get(0));
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



	public String toString() { 
		StringBuilder sb = new StringBuilder();
		for(Vertex<V> v : vertexList) {
			sb.append("Vertex "+v.getElement()+"\n");
			if(isDirected) { 
				sb.append(" [outgoing]");
			}
			sb.append(" "+outDegree(v)+" adjacent vertices.");
			for(Edge<E> e : outEdges(v)) {
				sb.append(String.format(" (%s,%s)", opposite(v,e).getElement(), e.getElement()));
			}
			sb.append("\n");
			if(isDirected) { 
				sb.append(" [incoming]");
				sb.append(" "+inDegree(v)+" adjacent vertices.");
				for(Edge<E> e : inEdges(v)) {
					sb.append(String.format(" (%s,%s)", opposite(v,e).getElement(), e.getElement()));
				}
				sb.append("\n");
			}
		}

		return sb.toString();
	}

}
