package graphs;

import java.util.*;

/**
 * Implements a graph using two maps; one map for adjacency properties 
 * and one map to keep track of the data associated with a vertex. 
 * 
 * @author Sachin Sulkunte
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;


	public Graph() {
		
		adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		dataMap = new HashMap<String, E>();
	}
	
	public void addVertex(java.lang.String vertexName, E data) {
		
		if(dataMap.containsKey(vertexName)) {
			
			throw new IllegalArgumentException("Vertex is already in the graph");
		}
		
		HashMap<String, Integer> newVertexMap = new HashMap<String, Integer>();
		
		adjacencyMap.put(vertexName, newVertexMap);
		dataMap.put(vertexName, data);
	}
	
	public java.util.Map<java.lang.String,java.lang.Integer> getAdjacentVertices(java.lang.String vertexName){
		
		if(adjacencyMap.containsKey(vertexName)) {
			
			return adjacencyMap.get(vertexName);
		}
		
		HashMap<String, Integer> empty = new HashMap<String, Integer>();
		return empty;
	}
	
	public void addDirectedEdge(java.lang.String startVertexName, java.lang.String endVertexName, int cost) {
		
		if(adjacencyMap.containsKey(endVertexName) == false || dataMap.containsKey(startVertexName) == false) {
			
			throw new IllegalArgumentException("Invalid vertex parameters");
		}
		
		getAdjacentVertices(startVertexName).put(endVertexName, cost);	//call to method for returning vertex's aadjacency map 
	}
	
	//"You can assume that endVertex is adjacent to startVertex"
	public int getCost(java.lang.String startVertexName, java.lang.String endVertexName) {
			
		return getAdjacentVertices(startVertexName).get(endVertexName);
	}
	
	public java.util.Set<java.lang.String> getVertices(){
		
		return dataMap.keySet();
	}
	
	public E getData(java.lang.String vertex) {
		
		if(dataMap.containsKey(vertex) == false) {
			
			throw new IllegalArgumentException("Invalid vertex");
		}
		
		return dataMap.get(vertex);
	}
	
	public void doDepthFirstSearch(java.lang.String startVertexName, CallBack<E> callback) {
		
		if(dataMap.containsKey(startVertexName) == false) {
			
			throw new IllegalArgumentException("Invalid vertex");
		}
		
		ArrayList<String> visited = new ArrayList<String>();	//vertices visited
		Stack<String> discovered = new Stack<String>();	//vertices to be processed
		
		discovered.push(startVertexName);
		String current = startVertexName;
		
		while(!discovered.isEmpty()) {
			
			discovered.pop();
			if(searchHelper(current, visited) == false) {
				
				visited.add(current);
				callback.processVertex(current, getData(current));
				for(String element: getAdjacentVertices(current).keySet()) {
					
					if(searchHelper(element, visited) == false) {
						
						discovered.push(element);
					}
				}
			}
			
			try {
				
				current = discovered.peek();
			} catch(EmptyStackException e) {
				
				return;
			}
			
		}
		
	}
	
	public void doBreadthFirstSearch(java.lang.String startVertexName, CallBack<E> callback) {
		
		if(dataMap.containsKey(startVertexName) == false) {
			
			throw new IllegalArgumentException("Invalid vertex");
		}
		
		ArrayList<String> visited = new ArrayList<String>();	//vertices visited
		PriorityQueue<String> discovered = new PriorityQueue<String>();	//vertices to be processed
		
		discovered.add(startVertexName);
		String current = startVertexName;
		
		while(!discovered.isEmpty()) {
			
			discovered.remove(current);
			if(searchHelper(current, visited) == false) {
				
				visited.add(current);
				callback.processVertex(current, getData(current));
				for(String element: getAdjacentVertices(current).keySet()) {
					
					if(searchHelper(element, visited) == false) {
						
						discovered.add(element);	//priority queue defaults to alphabetical order
					}
				}
			}
			
			current = discovered.peek();
		}
	}
	
	private boolean searchHelper(String vertex, ArrayList<String> list) {		//checks if vertex is in visited list
		
		for(int i = 0; i < list.size(); i++) {
			
			if(list.get(i) == vertex) {
				
				return true;
			}
		}
		
		return false;
	}
	
	public int doDijkstras(java.lang.String startVertexName, java.lang.String endVertexName, 
			java.util.ArrayList<java.lang.String> shortestPath) {
		
		if(dataMap.containsKey(startVertexName) == false || dataMap.containsKey(endVertexName) == false) {
			
			throw new IllegalArgumentException("Invalid vertex");
		}
		else if(getAdjacentVertices(startVertexName).isEmpty()) {
			
			shortestPath.add("None");
			return -1;
		}
		else if(endVertexName == startVertexName) {
			
			for(String element: getAdjacentVertices(startVertexName).keySet()) {	//if vertex has looped edge
				
				if(element == startVertexName) {
					
					shortestPath.add(startVertexName);
					return getCost(startVertexName, endVertexName);
				}
			}
			shortestPath.add(startVertexName);
			return 0;
		}
		
		ArrayList<String> set = new ArrayList<String>();
		PriorityQueue<Integer> order = new PriorityQueue<Integer>();
		HashMap<String, Integer> vCost = new HashMap<String, Integer>(); //tracks cost from first vertex
		HashMap<String, String> prev = new HashMap<String, String>(); 	//tracks predecessors
		String current = startVertexName;

		for(String vertex: dataMap.keySet()) {
			
			if(vertex == startVertexName) {
				
				vCost.put(vertex, 0);
				prev.put(vertex, null);
			}
			else {
				
				vCost.put(vertex, 10001);
				prev.put(vertex, null);
			}
		}
		
		while(set.size() < dataMap.size() + 1) {
			
			if(!order.isEmpty()) {
				
				int curr = order.poll();	//removes lowest in queue
				for(String items: vCost.keySet()) {
					
					if(curr == vCost.get(items)) {
						
						current = items;
						break;
					}
				}
			}
			
			set.add(current);
			for(String element: getAdjacentVertices(current).keySet()) {
				
				if(vCost.get(element) > vCost.get(current) + getCost(current, element)) {
					
					vCost.put(element, vCost.get(current) + getCost(current, element));
					prev.put(element, current);
					order.add(vCost.get(current) + getCost(current, element));
				}
			}
		}
		
		int cost = vCost.get(endVertexName);

		String cu = endVertexName;
		while(cu != startVertexName) {
			
			if(prev.get(cu) != null) {
				
				shortestPath.add(cu);
				cu = prev.get(cu);
			}
			else {
				
				shortestPath.add("None");
				return -1;
			}
		}
		shortestPath.add(startVertexName);
		Collections.reverse(shortestPath);
		return cost;
	}
	
	public java.lang.String toString(){
		
		String results = "";
		
		results += "Vertices: [";
		TreeMap<String, HashMap<String, Integer>> vertices = new TreeMap<String, HashMap<String, Integer>>(adjacencyMap);
		for(String key: vertices.keySet()) {
			
			if(key == vertices.lastKey()) {
				
				results += key + ']';
			}
			else {
				
				results += key + ", ";
			}
		}
		
		results += "\nEdges:\n";
		
		for(String key: vertices.keySet()) {
			
			results += "Vertex(" + key + ")--->{";
			TreeMap<String, Integer> adjacent = new TreeMap<String, Integer>(adjacencyMap.get(key));
			for(String vertex: adjacent.keySet()) {
				
				if(vertex == adjacent.lastKey()) {
					
					results += vertex + "=" + adjacent.get(vertex) + "}\n";
					break;
				}
				else {
					
					results += vertex + "=" + adjacent.get(vertex) + ", ";
				}
			}

			if(adjacent.isEmpty()) {
				
				results += "}\n";
			}
		}
		
		return results;
	}
}
