package algorithm.disjointset;
import java.lang.*;

/**
 * Find the cycle in graph
 */
public class UnionFind {


    public static void main(String[] args) {
        /*               0
                        / \
                       /   \
                      /     \
                     1-------2

        */

        int V = 3, E = 3;
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;

        if (graph.isCycle(graph) == 1)
            System.out.println("graph contains cycle");
        else
            System.out.println("graph doesn't contain cycle");
    }

    static class Graph {
        int V;
        int E;
        Edge edge[];

        class Edge {
            int src, dest;
        }

        // Creates a graph with V vertices and E edges
        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[E];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }

        int find(int parent[], int i) {
            if (parent[i] == -1)
                return i;
            return find(parent, parent[i]);
        }

        void Union(int parent[], int x, int y) {
            parent[x] = y;
        }


        int isCycle(Graph graph) {

            int parent[] = new int[graph.V];

            for (int i = 0; i < graph.V; ++i)
                parent[i] = -1;

            for (int i = 0; i < graph.E; ++i) {
                int x = graph.find(parent, graph.edge[i].src);
                int y = graph.find(parent, graph.edge[i].dest);

                if (x == y)
                    return 1;

                graph.Union(parent, x, y);
            }
            return 0;
        }
    }
}