import java.util.PriorityQueue;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
class Main {
	public static LinkedList<Node> adjList[];
	public static boolean visited[];
	public static int tracker[]; // keeps track of minimum edge length of each vertex
	public static int numVertices;
	public static int numEdges;
	public static int source;
	public static PriorityQueue<Node> queue = new PriorityQueue<Node>(); //keep track of temp vertices we need to visit
	public static int prev[];
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		numVertices = scan.nextInt();
		numEdges = scan.nextInt();
		source = scan.nextInt();
		adjList = new LinkedList[numVertices];
		tracker = new int[numVertices];
		visited = new boolean[numVertices];
		prev = new int[numVertices];
		for(int i = 0; i < numVertices; i++) {
			tracker[i] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < numVertices ; i++){ // Creates a new list of references for each vertex for neighboring vertexes 
	        adjList[i] = new LinkedList<Node>();
		}
		while(scan.hasNext()) {
			int j = scan.nextInt(); // find the vertex
			adjList[j].add(new Node(scan.nextInt(),scan.nextInt(),j));  //index of end vertex
		}
		scan.close();
		
		dijkstra(adjList,source);
		for(int i = 0; i < tracker.length; i++) {
			if(visited[i] == true) {
				if(i != source) {
				System.out.println(i + " " + tracker[i] + " " + prev[i]);
				}
		}
		}
	}
		
		
	
	public static void dijkstra(LinkedList<Node> adj[], int src) {
		queue.add(new Node(src, 0, src));
		tracker[src] = 0;
		prev[src] = src; //set source's parent to source
		while(!queue.isEmpty()) { //Keep checking vertices until all paths from source are found
			int current = queue.remove().id; //current vertex is node we are checking
			visited[current] = true; // only set vertex to true it is the current vertex
			checkNeighbors(current);  //check all of current's neighbors
		}
	}
	public static void checkNeighbors(int curr) {
		int edgeDist = -1;
		int newDist = -1;
		for(int i = 0; i < adjList[curr].size(); i++) {
			Node next = adjList[curr].get(i); //get all of current's neighbors
			if(visited[next.id] == false) { //if neighbor has not been visited
				edgeDist = next.cost;
				newDist = tracker[curr] + edgeDist;
				if(newDist < tracker[next.id]) {//check if the new path is less than the one in tracker
					tracker[next.id] = newDist; // if so, set the new path as the least
					prev[next.id] = curr;//set the parent of the checked node to current node
				}
			
				//visited[next.id] = true;
				if(visited[next.id] == false) {
					//if(!queue.contains(next)) {
					queue.add(new Node(next.id,tracker[next.id],curr)); // add the checked neighbor into queue for checking
				//}
				}
		}
	}
}
}
class Node implements Comparable<Node> {
	public int id;
	public int cost;
	public int parent;
	
	public Node(){
	}
	public Node(int id, int cost, int parent) {
		this.id = id; // node #
		this.cost = cost; //edge length 
		this.parent = parent; //prev node
	}
	public int compareTo(Node n) {
    	if(this.cost < n.cost) {
    		return -1;
    	}
    	else if(this.cost > n.cost) {
    		return 1;
    	}
    	else {
    		return 0;
    	}
    }

}

	

