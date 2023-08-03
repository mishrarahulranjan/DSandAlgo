package algorithm.greedy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
  let us create the following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4
**/

public class PrismMSTAdjacencyList {

    public static void main(String[] args){

        int V = 9;
        Graph graph = new Graph(V);
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 2, 6);
        addEdge(graph, 0, 3, 5);
        addEdge(graph, 1, 3, 15);
        addEdge(graph, 2, 3, 4);

        // Method invoked
        prims_mst(graph);
    }

    static class Node1 {
        int dest;
        int weight;

        Node1(int a, int b){
            dest = a;
            weight = b;
        }
    }

    static class Graph {
        int V;
        LinkedList<Node1>[] adj;

        Graph(int e) {
            V = e;
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++)
                adj[i] = new LinkedList<>();
        }
    }

    static class Node {
        int vertex;
        int key;
    }

    static class comparator implements Comparator<Node> {
        @Override
        public int compare(Node node0, Node node1) {
            return node0.key != node1.key ? node0.key - node1.key : node0.vertex - node1.vertex;
        }
    }

    static void addEdge(Graph graph, int src, int dest, int weight) {
        Node1 node0 = new Node1(dest, weight);
        Node1 node  = new Node1(src, weight);
        graph.adj[src].addLast(node0);
        graph.adj[dest].addLast(node);
    }

    static void prims_mst(Graph graph){

        Boolean[] mstSet    = new Boolean[graph.V];
        Node[] nodes        = new Node[graph.V];
        int[] parent        = new int[graph.V];

        for (int i = 0; i < graph.V; i++){
            nodes[i]        = new Node();
        }

        for (int i = 0; i < graph.V; i++) {
            mstSet[i]        = false;
            nodes[i].key     = Integer.MAX_VALUE;
            nodes[i].vertex  = i;
            parent[i]        = -1;
        }

        mstSet[0]       = true;
        nodes[0].key    = 0;

        TreeSet<Node> queue = new TreeSet<Node>(new comparator());

        for (int i = 0; i < graph.V; i++){
            queue.add(nodes[i]);
        }

        while (!queue.isEmpty()) {
            Node node0              = queue.pollFirst();
            mstSet[node0.vertex]    = true;
            for (Node1 iterator : graph.adj[node0.vertex]) {
                // If V is in queue
                if (mstSet[iterator.dest] == false) {
                    if (nodes[iterator.dest].key  > iterator.weight) {
                        queue.remove(nodes[iterator.dest]);
                        nodes[iterator.dest].key    = iterator.weight;
                        queue.add(nodes[iterator.dest]);
                        parent[iterator.dest]       = node0.vertex;
                    }
                }
            }
        }

        for (int i = 1; i < graph.V; i++){
            System.out.println(parent[i] + " "+ "-"+ " " + i);
        }
    }
}
