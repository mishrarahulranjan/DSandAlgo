package algorithm.backtrack;

public class HamiltonianCycle {

    public static void main(String [] args){

        int graph1[][] = {
                            {0, 1, 0, 1, 0},
                            {1, 0, 1, 1, 1},
                            {0, 1, 0, 0, 1},
                            {1, 1, 0, 0, 1},
                            {0, 1, 1, 1, 0},
                        };

        findHamiltonianCycle(graph1);

        int graph2[][] = {
                            {0, 1, 0, 1, 0},
                            {1, 0, 1, 1, 1},
                            {0, 1, 0, 0, 1},
                            {1, 1, 0, 0, 0},
                            {0, 1, 1, 0, 0},
                        };

        findHamiltonianCycle(graph2);
    }

    private static void findHamiltonianCycle(int[][] graph) {
        int vertex = graph.length;
        int[] path= new int[vertex];
        for (int i = 0; i < vertex; i++)
            path[i] = -1;

        path[0]=0;
        if(hamiltonianCycle(graph,vertex,path,1)){
            printPath(path);
        }else{
            System.out.println("No hamiltonian cycle available");
        }
    }

    private static void printPath(int[] path) {
        for (int v:path){
            System.out.print("V"+v +"->");
        }
        System.out.print("V"+path[0]);
        System.out.println();
    }

    private static boolean hamiltonianCycle(int[][] graph,int vertex, int[] path,int position) {

        if (position == vertex)
        {
            // And if there is an edge from the last included
            // vertex to the first vertex
            if (graph[path[position - 1]][path[0]] == 1)
                return true;
            else
                return false;
        }else{
            for(int v=1;v<vertex;v++){
                if(isSafe(graph,path,v,position)){
                    path[position]=v;
                    if(hamiltonianCycle(graph,vertex,path,position+1)){
                        return true;
                    }else{
                        path[position]=-1;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] graph, int[] path, int v, int position) {

        //check if vertex is already placed.
        for(int pathValue:path){
            if(v==pathValue){
                return false;
            }
        }

        //check if edge is available or not
        if(graph[path[position-1]][v] == 1 ){
            return true;
        }

        return false;
    }
}
