package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

public class HamiltonianCycleAllSolution {

    private static List<int[]> resultList = new ArrayList<>();

    public static void main(String [] args){

        int graph1[][] = {
                            {0, 1, 0, 1, 0},
                            {1, 0, 1, 1, 1},
                            {0, 1, 0, 0, 1},
                            {1, 1, 0, 0, 1},
                            {0, 1, 1, 1, 0},
                        };

        findHamiltonianCycles(graph1);

        int graph2[][] = {
                            {0, 1, 0, 1, 0},
                            {1, 0, 1, 1, 1},
                            {0, 1, 0, 0, 1},
                            {1, 1, 0, 0, 0},
                            {0, 1, 1, 0, 0},
                        };

        findHamiltonianCycles(graph2);
    }

    private static void findHamiltonianCycles(int[][] graph) {

        int vertex = graph.length;
        int[] path= new int[vertex];

        for (int i = 0; i < vertex; i++) path[i] = -1;

        path[0] = 0;
        hamiltonianCycle(graph,vertex,path,1);
        if(resultList.size()>0){
            System.out.println("Hamiltonian cycle available for given graph");
            printPath();
        }else{
            System.out.println("No hamiltonian cycle available");
        }

    }

    private static void printPath(){
        resultList.stream().forEach(path->{
                    for (int v:path){
                        System.out.print("V"+v +"->");
                    }
                        System.out.print("V"+path[0]);
                        System.out.println();

        });
    }

    private static void hamiltonianCycle(int[][] graph,int vertex, int[] path,int position) {

        if (position == vertex){
            if (graph[path[position-1]][path[0]] == 1) {
                resultList.add(path.clone());
            }
        }else{
            for(int v=1;v<vertex;v++){
                if(isSafe(graph,path,v,position)){
                    path[position]=v;
                    hamiltonianCycle(graph,vertex,path,position+1);
                    path[position]=-1;
                }
            }

        }
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
