package algorithm.backtrack;

import java.util.Arrays;

public class RatInMazeProblem {

    public static void main (String [] args){

        int rateMaze[][] = {
                        { 1, 0, 0, 0 },
                        { 1, 1, 0, 1 },
                        { 0, 1, 0, 0 },
                        { 1, 1, 1, 1 }
                    };

        int resultMaze[][]= new int[rateMaze.length][rateMaze.length];

        if(solveRatMazeProblem(rateMaze,0,0,resultMaze)){
            System.out.println("there is path available for RAT");
            printPath(resultMaze);
        }else{
            System.out.println("No solution with this input");
        }
    }

    private static void printPath(int[][] rateMaze) {
        Arrays.stream(rateMaze).forEach(t->{
            Arrays.stream(t).forEach(System.out::print);
            System.out.println();
            }
        );
    }

    private static boolean isSafeCell(int rateMaze[][] , int row, int col){
        return rateMaze[row][col]==1;
    }

    private static boolean solveRatMazeProblem(int[][] rateMaze, int row, int col,int resultMaze[][]) {

        /*
            solve Rat Maze problem
         */
        if(row==rateMaze.length-1 && col==rateMaze.length-1){
            if(isSafeCell(rateMaze, row,col)){
                resultMaze[row][col]=1;
                return true;
            }

        }else{
            if(rateMaze[row][col]==1){
                resultMaze[row][col]=1;
            }

            if((isSafeCell(rateMaze, row,col+1) && solveRatMazeProblem(rateMaze,row,col+1,resultMaze))
            || (isSafeCell(rateMaze, row+1,col) && solveRatMazeProblem(rateMaze,row+1,col,resultMaze))){
                return true;
            }
        }
        return false;

    }
}
