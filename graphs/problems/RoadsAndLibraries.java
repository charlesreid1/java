import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * HackerRank question.
 *
 * The Ruler of HackerLand believes that every citizen of the 
 * country should have access to a library. Unfortunately, HackerLand 
 * was hit by a tornado that destroyed all of its libraries and 
 * obstructed its roads! As you are the greatest programmer of 
 * HackerLand, the ruler wants your help to repair the roads and 
 * build some new libraries efficiently.
 *
 * Hackerland has n cities numbered 1 to n. Cities are connected
 * by m bidirectional roads. A city has access to a library if:
 *  - their city contains a library
 *  - they can travel by road from their city to a city with a library
 *
 * Cost of repairing old roads is croad dollars, and cost of building
 * a library is clib.
 *
 * Given q questions (queries), where each query consists of a different
 * map, determine minimum cost of making libraries accessible to all 
 * citizens.
 *
 * https://www.hackerrank.com/challenges/torque-and-development/problem
 */

public class RoadsAndLibraries {

    static class Graph {
        ArrayList<Vertex> vertexList;
        ArrayList<Edge> edgeList;

        public Graph() {
            vertexList = new ArrayList<>();
            edgeList = new ArrayList<>();
        }

        public void init(int n) { 
            for(int i=0; i<n; i++) { 
                Vertex v = new Vertex(i+1);
                vertexList.add(v);
            }
        }

        public void addEdge(int i, int j) { 
            // To add an edge:
            // - get the two endpoints, 
            // - then create the edge, 
            // - then add vertex i and vertex j as neighbors. 
            Vertex vi = vertexList.get(i-1);
            Vertex vj = vertexList.get(j-1);
            Edge e = new Edge(vi, vj);
            vi.addNeighbor(vj, e);
            vj.addNeighbor(vi, e);
            edgeList.add(e);
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("Vertices:\n");
            for(int i=0; i<vertexList.size(); i++) { 
                sb.append("    ");
                sb.append(i);
                sb.append("\n");
            }
            sb.append("Edges:\n");
            for( Edge e : edgeList ) { 
                ArrayList<Vertex> endpoints = e.getEndpoints();
                sb.append("    ");
                sb.append(endpoints.get(0).getElement());
                sb.append("-");
                sb.append(endpoints.get(1).getElement());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    static class Vertex {
        private int element;
        private HashMap<Vertex, Edge> neighborList;
        public Vertex(int element) { 
            this.element = element;
            neighborList = new HashMap<>();
        }
        public Collection<Edge> outgoingEdges() {
            return neighborList.values();
        }
        public void addNeighbor(Vertex v, Edge e) { 
            neighborList.put(v, e);
        }
        public int getElement() {
            return this.element;
        }
        public String toString() {
            return Integer.toString(element);
        }
    }

    static class Edge {
        ArrayList<Vertex> endpoints;
        public Edge(Vertex i, Vertex j) {
            endpoints = new ArrayList<>();
            endpoints.add(i);
            endpoints.add(j);
        }
        public ArrayList<Vertex> getEndpoints() { 
            return endpoints;
        }
        public boolean hasEndpoint(Vertex v) {
            if(v.getElement() == endpoints.get(0).getElement() ) {
                return true;
            } else if(v.getElement() == endpoints.get(1).getElement() ) {
                return true;
            } else {
                return false;
            }
        }
        public Vertex getOpposite(Vertex v) { 
            if(v.getElement() == endpoints.get(0).getElement() ) {
                return endpoints.get(1);
            } else if(v.getElement() == endpoints.get(1).getElement() ) {
                return endpoints.get(0);
            } else {
                System.out.println("Endpoint 0: "+endpoints.get(0));
                System.out.println("Endpoint 1: "+endpoints.get(1));
                System.out.println("Checking for vertex: "+v.getElement());
                throw new IllegalArgumentException("Vertex not an endpoint of this edge.");
            }
        }
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("(Edge ");
            sb.append(endpoints.get(0).getElement());
            sb.append("-");
            sb.append(endpoints.get(1).getElement());
            sb.append(")");
            return sb.toString();
        }
    }




    /** Compute the number of connected components of graph g. */
    public static int numComponents(Graph g) {
        return g.vertexList.size() - dfsConnected(g).size();
    }

    /** Return a map representing DFS forest. 
     * If a vertex is missing from this map, 
     * it serves as a root in this forest.
     * Number of connected components is equal to:
     *
     * > g.vertexList.size() - forest.size()
     *
     */
    public static HashMap<Vertex, Edge> dfsConnected(Graph g) {
        HashSet<Vertex> known = new HashSet<>();
        HashMap<Vertex, Edge> forest = new HashMap<>();
        for(Vertex u : g.vertexList) {
            if(!known.contains(u)) {
                dfs(g, u, known, forest);
            }
        }
        return forest;
    }

    /** Recursive depth-first search that populates disovered with all edges/vertices discoverable from u.*/
    public static void dfs(Graph g, Vertex u, 
                           HashSet<Vertex> known,
                           HashMap<Vertex, Edge> discovered) {
        known.add(u);
        for(Edge e : u.outgoingEdges()) { 
            Vertex v = e.getOpposite(u);
            if(!known.contains(v)) {
                // e = edge that discovered v
                discovered.put(v,e);
                dfs(g, v, known, discovered);
            }
        }

        //return discovered;
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int q = in.nextInt();

        for(int a0 = 0; a0 < q; a0++){

            // Each "query" is a separate, uh, question.

            int n = in.nextInt();
            int m = in.nextInt();
            long clib = in.nextLong();
            long croad = in.nextLong();

            Graph g = new Graph();
            g.init(n);

            for(int a1 = 0; a1 < m; a1++){
                int i = in.nextInt();
                int j = in.nextInt();
                g.addEdge(i,j);
            }

            long result = 0;
            if(clib < croad) { 
                // Libraries are cheaper than roads,
                // so build a library at every vertex.
                result = clib*g.vertexList.size();

            } else {
                // Roads are cheaper than libraries,
                // so rebuild all roads,
                // and build one library per component.

                HashMap<Vertex, Edge> forest = dfsConnected(g);
                int nComponents = g.vertexList.size() - forest.size();
                int nEdges = forest.size();

                result = clib*nComponents + croad*nEdges;
            }
            System.out.println(result);

        }
    }
}

