package graph;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class GraphBuilder {
	private Graph graph;
    private double alpha;
    private Random setUpGen;
    public GraphBuilder(double alpha, int initialNodeNumber) {
    	graph = new Graph(alpha);
    	this.alpha = alpha;
    	setUpGen = new Random(System.currentTimeMillis());
    	initialise(initialNodeNumber);
    }
    private void initialise(int initialNodeNumber) {
    	for (int index = 0; index < initialNodeNumber; index++) {
    		graph.addNode(new Node(Graph.nodeCount));
    	}
    		//connect all nodes to each other
        for(Node aNode : graph.getNodes()){
        	for(Node other : graph.getNodes()){
        		if(!aNode.equals(other) && !aNode.isConnectedTo(other)){
        			aNode.connect(other);
        			graph.updateStats(other);
        		}
            }
        }
    }
    public void growGraph(int size){
    	ArrayList <Node> candidates = new ArrayList<Node>();
    	for(int index=0; index<size;index++){
    		Node newNode = new Node(Graph.nodeCount);
    		getCandidates(candidates, alpha);
    		if(candidates.size() > 0){
    			int randomIndex = Math.abs(setUpGen.nextInt()) % candidates.size();
    			Node toConnectTo = candidates.get(randomIndex);
    			newNode.connect(toConnectTo);
    			graph.updateStats(toConnectTo);
    			graph.addNode(newNode);
    			candidates.clear();
    		}
    	}
    }
    private void getCandidates(ArrayList<Node> candidates, double alpha){
    		//the value degree^alpha for the node with largest degree
    	double largestProbability = graph.getLargestProbability();
		for(Node aNode : graph.getNodes()){
				//if theres more than one node with this largest degree
			if(Math.pow(aNode.getDegree(),alpha)>=largestProbability){
				candidates.add(aNode);
			}
		}
    }
   
    private void printGraph(PrintWriter fwriter) throws IOException {
    	fwriter.println("*Vertices "+(graph.nodeCount));
        for (Node n : graph.getNodes()) {
           fwriter.println((n.getID()+1));
        }
        fwriter.println("*Edges");
        for (Node n : graph.getNodes()) {
        	for (Node adj : n.getAdjacencyList()) {
        		fwriter.println((n.getID()+1)+" "+(adj.getID()+1));
        	}
        }
    }
    private void printGraph2(PrintWriter fwriter) throws IOException {
    	 for (Node n : graph.getNodes()) {
             fwriter.println((n.getID()+1) + "," + n.getAdjacencyList().size());
          }
    }

    public static void main(String[] args) throws IOException {
    	double alpha =Double.parseDouble(args[0]);
    	int initialNodePopulation = Integer.parseInt(args[1]);
    	int finalPopulationSize = Integer.parseInt(args[2]);
        GraphBuilder gb = new GraphBuilder(alpha, initialNodePopulation);
        gb.growGraph(finalPopulationSize);
        PrintWriter graphFile = new PrintWriter(new FileOutputStream("dataFile"+alpha+".net"));
        PrintWriter degreeFile = new PrintWriter(new FileOutputStream("degreeFile"+alpha));
        gb.printGraph(graphFile);
        gb.printGraph2(degreeFile);
        degreeFile.flush();
        degreeFile.close();
        graphFile.flush();
        graphFile.close();
    }
}


