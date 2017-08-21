public class GraphTest2 {

	public static void main(String[] args) { 

		AdjMapGraph2<String,String> g = new AdjMapGraph2<String,String>(false);

		IVertex<String> v1 = g.insertVertex("node-rad");
		IVertex<String> v2 = g.insertVertex("node-bleu");
		IVertex<String> v3 = g.insertVertex("node-grun");
		IVertex<String> v4 = g.insertVertex("node-allo");
		IVertex<String> v5 = g.insertVertex("node-oranje");

		IEdge<String> e1 = g.insertEdge(v1,v2,"edge-won");
		IEdge<String> e2 = g.insertEdge(v1,v3,"edge-too");
		IEdge<String> e3 = g.insertEdge(v1,v5,"edge-tre");
		IEdge<String> e4 = g.insertEdge(v2,v3,"edge-foar");
		IEdge<String> e5 = g.insertEdge(v2,v4,"edge-phive");
		IEdge<String> e6 = g.insertEdge(v2,v5,"edge-sux");

		System.out.println("Trying to print");
		System.out.println(g.toString());
	}

}
