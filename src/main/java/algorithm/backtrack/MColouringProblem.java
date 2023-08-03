package algorithm.backtrack;

import java.util.Arrays;

public class MColouringProblem {

    public static void main (String args[]){
        int mColour = 3;

        boolean[][] graph = {
                { false, true, true, true },
                { true, false, true, false },
                { true, true, false, true },
                { true, false, true, false },
        };

        int nVertex=graph.length;
        int [] colouredVertex= new int[nVertex];

        if(colourTheGraph(graph,nVertex,mColour,colouredVertex,0)){
            System.out.println("graph can be coloured with "+mColour +" colour");
            Arrays.stream(colouredVertex).forEach(System.out::println);
        }else{
            System.out.print("no possible solution with "+mColour);
        }
    }

    private static boolean colourTheGraph(boolean[][] graph,int n,int mColour,int [] colouredVertex,int startVertex) {
        if(startVertex>=n){
            return true;
        }
        for(int colour=1;colour<=mColour;colour++){
            if(isSafeColour(graph,colour,n,startVertex,colouredVertex)){
                colouredVertex[startVertex]=colour;
                if(colourTheGraph(graph,n,mColour,colouredVertex,startVertex+1)){
                    return true;
                }else{
                    colouredVertex[startVertex]=0;
                }
            }
        }

        return false;
    }

    private static boolean isSafeColour(boolean[][] graph, int colour, int n, int vertex, int[] colouredVertex) {
        for(int j=0;j<n;j++){
            if(graph[vertex][j] && colouredVertex[j]==colour){
                return false;
            }
        }
        return  true;
    }
}
