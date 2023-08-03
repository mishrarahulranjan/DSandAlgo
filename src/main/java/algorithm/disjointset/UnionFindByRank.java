package algorithm.disjointset;

public class UnionFindByRank {

    static class Graph{

        int V, E;
        Edge[] edge;

        Graph(int nV, int nE){
            V = nV;
            E = nE;
            edge = new Edge[E];
            for (int i = 0; i < E; i++)
            {
                edge[i] = new Edge();
            }
        }

        class Edge{
            int src, dest;
        }

        class subset{
            int parent;
            int rank;
        }

        int find(subset[] subsets, int i){
            if (subsets[i].parent != i)
                subsets[i].parent
                        = find(subsets, subsets[i].parent);
            return subsets[i].parent;
        }

        void Union(subset[] subsets, int x, int y){
            int xRoot = find(subsets, x);
            int yRoot = find(subsets, y);

            if (subsets[xRoot].rank < subsets[yRoot].rank)
                subsets[xRoot].parent = yRoot;
            else if (subsets[yRoot].rank < subsets[xRoot].rank)
                subsets[yRoot].parent = xRoot;
            else {
                subsets[xRoot].parent = yRoot;
                subsets[yRoot].rank++;
            }
        }

        int isCycle(Graph graph){
            int V = graph.V;
            int E = graph.E;

            subset[] subsets = new subset[V];
            for (int v = 0; v < V; v++) {

                subsets[v] = new subset();
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            for (int e = 0; e < E; e++) {
                int x = find(subsets, graph.edge[e].src);
                int y = find(subsets, graph.edge[e].dest);
                if (x == y)
                    return 1;
                Union(subsets, x, y);
            }
            return 0;
        }

        public static void main(String[] args){
        /** Let us create the following graph
                    0
                 /    \
                /      \
               1---------2
         **/

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
                System.out.println("Graph contains cycle");
            else
                System.out.println(
                        "Graph doesn't contain cycle");
        }
    }
}
