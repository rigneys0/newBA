package graph;

import java.util.TreeSet;

public class Node implements Comparable<Node>{
	private TreeSet<Node> adjacent;
	private int degree;
	private int id=0;
	public Node(int _id){
		 adjacent = new TreeSet<Node>();
		 id = _id;
	}
	public TreeSet<Node> getAdjacencyList(){
		return adjacent;
	}
	public int getDegree(){
		return degree;
	}
	public void incrementDegree(){
		degree++;
	}
	public void connect(Node other){
		adjacent.add(other);
		other.adjacent.add(this);
	}
	public boolean isConnectedTo(Node other){
		return adjacent.contains(other);
	}
	public int compareTo(Node other){
		return id - other.id;
	}
	public int getID(){
		return id;
	}
	@Override
	public boolean equals(Object other){
		return id==((Node)other).id;
	}
}
