package algorithm.backtrack;

import java.util.Arrays;
import java.util.stream.Stream;

public class KnightTourProblem {

    static int N = 8;

    public static void main(String[] args){
        // Function Call
        solveKT();
    }

    static boolean isSafe(int x, int y, int sol[][]){
        return (x >= 0 && x < N && y >= 0 && y < N
                && sol[x][y] == -1);
    }

    static void printSolution(int sol[][]){
        Stream.of(sol).map(Arrays::toString).forEach(System.out::println);
    }

    static void solveKT(){
        int sol[][] = new int[8][8];

        /* Initialization of solution matrix */
        for (int x = 0; x < N; x++){
            for (int y = 0; y < N; y++){
                sol[x][y] = -1;
            }
        }

        /* knight possible moves in (x,y)*/
        int xMove[] = { 2, 1, -1, -2, -2, -1,  1,  2 };
        int yMove[] = { 1, 2,  2,  1, -1, -2, -2, -1 };

        // Since the Knight is initially at the first block
        sol[0][0] = 0;

        /* Start from 0,0 and explore all tours using
           solveKTUtil() */
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
        }else{
            printSolution(sol);
        }
    }

    static boolean solveKTUtil(int x, int y, int movei,int sol[][], int xMove[],int yMove[]){
        int k, nextX, nextY;

        if (movei == N * N){
            return true;
        }


        /* Try all next moves from the current coordinate
            x, y */
        for (k = 0; k < 8; k++) {
            nextX = x + xMove[k];
            nextY = y + yMove[k];
            if (isSafe(nextX, nextY, sol)) {
                sol[nextX][nextY] = movei;
                if (solveKTUtil(nextX, nextY, movei + 1,
                        sol, xMove, yMove))
                    return true;
                else
                    sol[nextX][nextY]= -1; // backtracking
            }
        }
        return false;
    }
}
