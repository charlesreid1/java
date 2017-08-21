public class GraphTest {

	public static void main(String[] args) { 

		AdjMapGraph<String,String> g = new AdjMapGraph<String,String>(false);

		AdjMapGraph<String,String>.Vertex<String> v1 = g.insertVertex("node-rad");
		AdjMapGraph<String,String>.Vertex<String> v2 = g.insertVertex("node-bleu");
		AdjMapGraph<String,String>.Vertex<String> v3 = g.insertVertex("node-grun");
		AdjMapGraph<String,String>.Vertex<String> v4 = g.insertVertex("node-allo");
		AdjMapGraph<String,String>.Vertex<String> v5 = g.insertVertex("node-oranje");

		AdjMapGraph<String,String>.Edge<String> e1 = g.insertEdge(v1,v2,"edge-won");
		AdjMapGraph<String,String>.Edge<String> e2 = g.insertEdge(v1,v3,"edge-too");
		AdjMapGraph<String,String>.Edge<String> e3 = g.insertEdge(v1,v5,"edge-tre");
		AdjMapGraph<String,String>.Edge<String> e4 = g.insertEdge(v2,v3,"edge-foar");
		AdjMapGraph<String,String>.Edge<String> e5 = g.insertEdge(v2,v4,"edge-phive");
		AdjMapGraph<String,String>.Edge<String> e6 = g.insertEdge(v2,v5,"edge-sux");

		System.out.println("Trying to print");
		System.out.println(g.toString());
	}

}
