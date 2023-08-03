package algorithm.greedy;

public class PrimsMST {

    public static void main(String[] args) {

        int[][] graph = new int[][]{
                                        {0, 2, 0, 6, 0},
                                        {2, 0, 3, 8, 5},
                                        {0, 3, 0, 0, 7},
                                        {6, 8, 0, 0, 9},
                                        {0, 5, 7, 9, 0}
                                   };

        MST t = new MST();
        t.primMST(graph);
    }

    static class MST {
        private static final int V = 5;

        int minKey(int[] key, Boolean[] mstSet) {
            int min         = Integer.MAX_VALUE;
            int min_index   = -1;

            for (int v = 0; v < V; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min         = key[v];
                    min_index   = v;
                }
            }
            return min_index;
        }

        void printMST(int[] parent, int[][] graph) {
            System.out.println("Edge \tWeight");
            for (int i = 1; i < V; i++) {
                System.out.println(parent[i] + " - " + i + "\t" + graph[parent[i]][i]);
            }
        }

        void primMST(int[][] graph) {

            int[] parent = new int[V];
            int[] key    = new int[V];
            Boolean[] mstSet = new Boolean[V];

            // Initialize all keys as INFINITE
            for (int i = 0; i < V; i++) {
                key[i]      = Integer.MAX_VALUE;
                mstSet[i]   = false;
            }

            key[0]      = 0;
            parent[0]   = -1;

            for (int count = 0; count < V - 1; count++) {

                int uIndex       = minKey(key, mstSet);
                mstSet[uIndex]   = true;

                for (int vIndex = 0; vIndex < V; vIndex++)
                    if (graph[uIndex][vIndex] != 0 && !mstSet[vIndex] && graph[uIndex][vIndex] < key[vIndex]) {
                        parent[vIndex]   = uIndex;
                        key[vIndex]      = graph[uIndex][vIndex];
                    }
            }

            // print the constructed MST
            printMST(parent, graph);
        }
    }
}
