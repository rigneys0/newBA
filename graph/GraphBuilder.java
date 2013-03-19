package graph;

import java.util.Random;

public class GraphBuilder {
	private Graph graph;
	private double alpha;
	private Random setUpGen;

	public GraphBuilder(double alpha) {
		graph = new Graph();
		this.alpha = alpha;
		setUpGen = new Random(System.currentTimeMillis());
		initialise();
	}

	private void initialise() {
		for (int index = 0; index < 3; index++) {
			graph.addNode(new Node(Graph.nodeCount));
		}
		for(Node n : graph.getNodes()){
			for(Node n2 : graph.getNodes()){
				if(!n.equals(n2) && !n.isConnectedTo(n2))
					n.connect(n2);
				//System.out.println(n.getID() + " " + n2.getID());
			}
		}
	}

	private void growGraph(int size) {
		Random setUpGen2 = new Random(System.currentTimeMillis());
		Node temp = null;
		int bestDegree = 0;
		for(int index=0; index< size; index++){
			int currentDegreeTotal=0;
			Node newNode = new Node(Graph.nodeCount);
			for(Node n : graph.getNodes()){
				currentDegreeTotal+= Math.pow(n.getDegree(), alpha);
				if(n.getDegree()>=bestDegree){
					temp=n;
					bestDegree = n.getDegree();
				}
			}
			graph.addNode(newNode);
			newNode.connect(temp);
			
		
		}
		/*double currentDegreeTotal = 0;
		double prob=0;
		Node tmpNode=null;
		for(int index=0; index< size; index++){
			currentDegreeTotal = 0;
			Node newNode = new Node(Graph.nodeCount);
			for(Node n : graph.getNodes()){
				currentDegreeTotal+= Math.pow(n.getDegree(), alpha);
			}
			
			int index2 = Math.abs(setUpGen.nextInt()) % Graph.nodeCount;
			Node someNode = graph.getNodes().get(index2);
			//tmpNode = someNode;
			int endPoint = index2-1;
			while(index2!=endPoint){
				System.out.println(index2 + " "+ endPoint);
				someNode = graph.getNodes().get(index2);
				double tmpProb = Math.pow(someNode.getDegree(), alpha)/currentDegreeTotal;
				if(tmpProb>=prob){
					prob = tmpProb;
					tmpNode=someNode;
				}
				
				index2=(index2 + 1)%graph.nodeCount;
			}
			graph.addNode(newNode);
			newNode.connect(tmpNode);
		}*/
	}

	private void printGraph() {
		for (Node n : graph.getNodes()) {
			for (Node adj : n.getAdjacencyList()) {
				System.out.println(n.getID() + " : " + adj.getID());
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		GraphBuilder gb = new GraphBuilder(new Double(1));
		gb.growGraph(26);
		gb.printGraph();
	}
}
