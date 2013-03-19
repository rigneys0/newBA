package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Graph {
	private ArrayList<Node> nodes;
	public static int nodeCount=0;
	public Graph(){
		nodes = new ArrayList<Node>();
	}
	public void addNode(Node newNode){
		nodes.add(newNode);
		nodeCount++;
	}
	public ArrayList<Node> getNodes(){
		return nodes;
	}
}
