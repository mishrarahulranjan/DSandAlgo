package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumKLengthPath {

    static class AdjListNode {
        int vertex;
        int weight;

        AdjListNode(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
        int getVertex() { return vertex; }
        int getWeight() { return weight; }
    }

    static class Graph {

        int V;

        ArrayList<ArrayList<AdjListNode>> adj;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<ArrayList<AdjListNode>>(V);

            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<AdjListNode>());
            }
        }

        void addEdge(int u, int v, int weight) {
            AdjListNode node1 = new AdjListNode(v, weight);
            adj.get(u).add(node1);

            AdjListNode node2 = new AdjListNode(u, weight);
            adj.get(v).add(node2);
        }
    }

    public static void main(String[] args){
        // create the graph given in above figure
        int V = 9;
        Graph g = new Graph(V);

        // making above shown graph
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        int src = 0;
        int k = 62;

        if(pathMoreThanK(g ,src, k))
            System.out.println("YES");
        else
            System.out.println("NO");


        k = 60;
        if(pathMoreThanK(g ,src, k))
            System.out.println("YES");
        else
            System.out.println("NO");



    }
     private static boolean pathMoreThanK(Graph g,int src, int k){

            // Create a path array with nothing included
            // in path
            boolean path[] = new boolean[g.V];

            Arrays.fill(path, false);

            // Add source vertex to path
            path[src] = true;

            return pathMoreThanKUtil(g,src, k, path);
        }

        // Prints shortest paths from src to all other vertices
     static boolean pathMoreThanKUtil(Graph g,int src, int k, boolean[] path){

            // If k is 0 or negative, return true;
            if (k <= 0)
                return true;
            for(int i = 0; i < g.adj.get(src).size(); i++){
                AdjListNode vertex = g.adj.get(src).get(i);

                // Get adjacent vertex and weight of edge
                int v = vertex.vertex;
                int w = vertex.weight;

                // If vertex v is already there in path, then
                // there is a cycle (we ignore this edge)
                if (path[v] == true)
                    continue;

                // If weight of is more than k, return true
                if (w >= k)
                    return true;

                // Else add this vertex to path
                path[v] = true;

                // If this adjacent can provide a path longer
                // than k, return true.
                if (pathMoreThanKUtil(g,v, k-w, path))
                    return true;

                // Backtrack
                path[v] = false;
            }

            // If no adjacent could produce longer path, return
            // false
            return false;
        }
}
