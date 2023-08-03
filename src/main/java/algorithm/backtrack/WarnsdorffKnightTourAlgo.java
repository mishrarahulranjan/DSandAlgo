package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WarnsdorffKnightTourAlgo {

    static class Position {
            int x;
            int y;
        Position(int x,int y){
                this.x = x;
                this.y = y;
            }
    }

    static int N = 8;

    static int xMove[] = { 2, 1, -1, -2, -2, -1,  1,  2 };
    static int yMove[] = { 1, 2,  2,  1, -1, -2, -2, -1 };


    public static void main(String[] args){
        int[][] tourMatrix = solveKnightTour();
        if(null== tourMatrix){
            System.out.println("No solution is Possible");
        }else{
            printSolution(tourMatrix);
        }
    }

    static boolean isSafe(int x, int y, int tourMatrix[][]){
        return (x >= 0 && x < N && y >= 0 && y < N
                && tourMatrix[x][y] == -1);
    }

    static List<Position> getPossiblePosition(int x, int y, int tourMatrix[][]){
        List<Position> positions = new ArrayList<>(8);
        for (int posIndex = 0; posIndex < 8; posIndex++){
            if(isSafe(x+xMove[posIndex],y+yMove[posIndex],tourMatrix)){
                positions.add(new Position(x+xMove[posIndex],y+yMove[posIndex]));
            }
        }
        return positions;
    }

    static void printSolution(int sol[][]){
        Stream.of(sol).map(Arrays::toString).forEach(System.out::println);
    }

    static int[][] solveKnightTour(){

        int[][] tourMatrix = new int[8][8];

        //initialize the tourMatrix with -1
        for (int x = 0; x < N; x++){
            for (int y = 0; y < N; y++){
                tourMatrix[x][y] = -1;
            }
        }
        int movNumber=1;
        tourMatrix[0][0] =movNumber;
        if(solveKnightTour(tourMatrix,0,0,movNumber+1)){
            return tourMatrix;
        }

        return null;
    }

    static boolean solveKnightTour(int[][] tourMatrix, int x, int y,int movNumber){

        List<Position> positions = getPossiblePosition(x,y,tourMatrix);
        if(positions.size()==0 ){
            return true;
        }

        //warnsdorff algo, get a position having minimum number of unvisited adjacent
        Position minimalDegreePosition = positions.get(0);
        for(Position pos : positions){
            if(getPossiblePosition(minimalDegreePosition.x,minimalDegreePosition.y,tourMatrix).size() >
                    getPossiblePosition(pos.x,pos.y,tourMatrix).size()){
                minimalDegreePosition= pos;
            }
        }
        tourMatrix[minimalDegreePosition.x][minimalDegreePosition.y]=movNumber;

        if(solveKnightTour(tourMatrix,minimalDegreePosition.x,minimalDegreePosition.y,movNumber+1)){
            return true;
        }

        return false;
    }
}
