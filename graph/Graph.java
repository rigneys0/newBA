package graph;

import java.util.ArrayList;

public class Graph {
        private ArrayList<Node> nodes;
        public static int nodeCount=0;
        private double largestProbability;
        private double alpha;
        private double sumOfModifiedDegrees;
        public Graph(double _alpha){
        	nodes = new ArrayList<Node>();
        	alpha = _alpha;
        	largestProbability=0;
        	sumOfModifiedDegrees=0;
        }
        public void addNode(Node newNode){
        	nodes.add(newNode.getID(),newNode);
        	nodeCount++;
        }
        public void updateStats(Node destination){
        	double modifiedNodeDegree =
        			Math.pow(destination.getDegree(), alpha);
        	sumOfModifiedDegrees += modifiedNodeDegree;
        	if((modifiedNodeDegree/sumOfModifiedDegrees)>largestProbability){
        		largestProbability = ((double)modifiedNodeDegree/sumOfModifiedDegrees);
        	}
        }
        public double getLargestProbability(){
        	return largestProbability;
        }
        public ArrayList<Node> getNodes(){
                return nodes;
        }
}

