public class GraphTest {

	public static void main(String[] args) { 

		AdjMapGraph<String,String> g = new AdjMapGraph<String,String>(false);

		IVertex<String> v1 = g.insertVertex("Red");
		IVertex<String> v2 = g.insertVertex("Bleu");
		IVertex<String> v3 = g.insertVertex("Gren");
		IVertex<String> v4 = g.insertVertex("Yallo");
		IVertex<String> v5 = g.insertVertex("Oranje");

		IEdge<String> e1 = g.insertEdge(v1,v2,"One");
		IEdge<String> e2 = g.insertEdge(v1,v3,"Two");
		IEdge<String> e3 = g.insertEdge(v1,v5,"Three");
		IEdge<String> e4 = g.insertEdge(v2,v3,"Four");
		IEdge<String> e5 = g.insertEdge(v2,v4,"Five");
		IEdge<String> e6 = g.insertEdge(v2,v5,"Six");

		System.out.println("Printing out graph:");
		System.out.println(g.toString());
	}

}
