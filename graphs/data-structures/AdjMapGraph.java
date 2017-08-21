import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/** 
 * Adjacency Map Graph.
 *
 * This maintains two lists - one of all vertices, one of all edges.
 * Each vertex stores a map of vertices-to-edges.
 * Each edge stored by a vertex is incident to that vertex.
 */
public class AdjMapGraph<V,E> implements Graph<V,E> {



	public static void main(String[] args) { 
		System.out.println("Testing.");
	}



	/**
	 * Adjacency Map Vertex.
	 */
	class AdjMapVertex<V> implements Vertex<V> {
	
		// Not storing a position pointer
		private V element;
		private boolean isDirected;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
	
		public AdjMapVertex(V element, boolean isDirected) { 
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
	
		public Map<Vertex<V>, Edge<E>> getIncoming() {
			return incoming;
		}
	
		public Map<Vertex<V>, Edge<E>> getOutgoing() {
			return outgoing;
		}
	
	}
	
	/**
	 * Adjacency Map Edge.
	 */
	class AdjMapEdge<E> implements Edge<E> {

		// Not storing a position pointer
		private E element;
		private ArrayList<AdjMapVertex<V>> endpoints; 

		public AdjMapEdge(AdjMapVertex<V> u, AdjMapVertex<V> v, E element) {
			this.element = element;
			endpoints = new ArrayList<AdjMapVertex<V>>();
			endpoints.add(u);
			endpoints.add(v);
		}

		public E getElement() {
			return element;
		}
	}




	/**
	 * Adjacency Map Graph class.
	 */

	public int numVertices() { 
		return 0;
	}

}