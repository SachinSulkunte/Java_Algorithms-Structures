package tests;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import graphs.Graph;
import graphs.PrintCallBack;

public class driver {

	private static Graph<Double> createGraph() {
		Graph<Double> graph = new Graph<Double>();

		/* Adding vertices to the graph */
		String[] vertices = new String[] { "ST", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
		for (int i = 0; i < vertices.length; i++) {
			graph.addVertex(vertices[i], new Double(i + 1000.50));
		}
			
		/* Adding directed edges */
		graph.addDirectedEdge("ST", "D", 1);
		graph.addDirectedEdge("ST", "E", 3);
		graph.addDirectedEdge("ST", "C", 2);
		graph.addDirectedEdge("B", "A", 2);
		graph.addDirectedEdge("D", "B", 7);
		graph.addDirectedEdge("C", "E", 5);
		graph.addDirectedEdge("E", "C", 4);
		graph.addDirectedEdge("ST", "ST", 4);

	
		return graph;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph<Double> graph = createGraph();
		String startVertex = "ST";
		String answer = runDijkstras(graph, startVertex);

		System.out.println(answer);
	}

	private static String runDijkstras(Graph<Double> graph, String startVertex) {
		ArrayList<String> shortestPath = new ArrayList<String>();
		StringBuffer results = new StringBuffer();
		
		Set<String> vertices = graph.getVertices();
		TreeSet<String> sortedVertices = new TreeSet<String>(vertices);
		for (String endVertex : sortedVertices) {
			int shortestPathCost = graph.doDijkstras(startVertex, endVertex,
					shortestPath);
			results.append("Shortest path cost between " + startVertex + " " + endVertex
					+ ": " + shortestPathCost);
			results.append(", Path: " + shortestPath + "\n");
			shortestPath.clear();
		}
		
		return results.toString();
	}
}
