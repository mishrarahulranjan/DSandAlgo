package algorithm.dp;

import java.util.ArrayList;
import java.util.List;
/**
  We can only go to the right or to the bottom
  Minimum cost Path :==>
    {3, 2, 12, 15, 10},
    {6, 19, 7, 11, 17},
    {8, 5, 12, 32, 21},
    {3, 20, 2, 9,  7}

  output :  52
 **/
public class MinimumCostPath {

    public static void main(String[] args) {

        int[][] matrix = {
                    {3, 2, 12, 15, 10},
                    {6, 19, 7, 11, 17},
                    {8, 5, 12, 32, 21},
                    {3, 20, 2, 9, 7}
               };

        System.out.println("1.minimum cost path is : " + minimumCostPathV1(matrix));
        System.out.println("2.minimum cost path is : " + minimumCostPathV2(matrix));
        int m = matrix.length;
        int n = matrix[0].length;
        int [][] cost= new int[matrix.length][matrix[0].length];
        System.out.println("3.minimum cost path is : " + minimumCostPathRecursive(matrix,m-1,n-1,cost));

        cost= minimumCostPathV3(matrix);
        List<Path> pathList = minimumCostPathListV2(cost);

        pathList.stream().forEach(it->{
            System.out.print(it);
            System.out.print("<<--");
        });
    }

    static private int minimumCostPathV1(int[][] matrix) {

        //cost[i][j] means cost of path from (i,j) to (m-1,n-1)
        int m               = matrix.length;
        int n               = matrix[0].length;
        int[][] cost = new int[m][n];
        cost[m - 1][n - 1]  = matrix[m - 1][n - 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    cost[i][j] = matrix[i][j];
                } else if (i == m - 1) {
                    cost[i][j] = matrix[i][j] + cost[i][j + 1];
                } else if (j == n - 1) {
                    cost[i][j] = matrix[i][j] + cost[i + 1][j];
                } else {
                    cost[i][j] = matrix[i][j] + Math.min(cost[i][j + 1], cost[i + 1][j]);
                }
            }
        }
        return cost[0][0];
    }

    static private int minimumCostPathV2(int[][] matrix) {

        //cost[i][j] means cost of path from (0,0) to (i,j)
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cost = new int[m][n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    cost[0][0] = matrix[0][0];
                } else if (i == 0) {
                    cost[i][j] = matrix[i][j] + cost[i][j - 1];
                } else if (j == 0) {
                    cost[i][j] = matrix[i][j] + cost[i - 1][j];
                } else {
                    cost[i][j] = matrix[i][j] + Math.min(cost[i][j - 1], cost[i - 1][j]);
                }
            }
        }
        return cost[m - 1][n - 1];
    }

    static private int[][] minimumCostPathV3(int[][] matrix) {

        //cost[i][j] means cost of path from (0,0) to (i,j)
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cost = new int[m][n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    cost[0][0] = matrix[0][0];
                } else if (i == 0) {
                    cost[i][j] = matrix[i][j] + cost[i][j - 1];
                } else if (j == 0) {
                    cost[i][j] = matrix[i][j] + cost[i - 1][j];
                } else {
                    cost[i][j] = matrix[i][j] + Math.min(cost[i][j - 1], cost[i - 1][j]);
                }
            }
        }
        return cost;
    }
    static private List<Path> minimumCostPathListV2(int[][] matrix) {

        //cost[i][j] means cost of path from (0,0) to (i,j)
        int m = matrix.length;
        int n = matrix[0].length;

        List<Path> pathList = new ArrayList<>();
        pathList.add(new Path(m-1,n-1));

        for (int i = m-1; i >0;) {
            for (int j = n-1; j >= 0;) {
                if(i == 0 && j == 0){
                   break;
                }else if(i == 0){
                   j--;
                }else if (j == 0){
                    i--;
                }else{
                    if(matrix[i-1][j] < matrix[i][j-1]){
                        i--;
                    }else{
                        j--;
                    }
                }

                pathList.add(new Path(i,j));
            }
        }
        return pathList;
    }

    static private int minimumCostPathRecursive(int [][] matrix, int i, int j, int [][] cost ){

        //cost[i][j] means cost of path from (0,0) to (i,j)
        if (cost[i][j] != 0) {
            return cost[i][j];
        } else {
            if (i == 0 && j == 0) {
                cost[i][j] = matrix[i][j];
            } else if (i == 0) {
                cost[i][j] = matrix[i][j] + minimumCostPathRecursive(matrix,i,j - 1, cost);
            } else if (j == 0) {
                cost[i][j] = matrix[i][j] + minimumCostPathRecursive(matrix,i-1,j , cost);
            } else {
                cost[i][j] = matrix[i][j] + Math.min(minimumCostPathRecursive(matrix,i-1,j , cost),
                                            minimumCostPathRecursive(matrix,i,j-1 , cost));
            }
        }
        return  cost[i][j];
    }

    static class Path{

        int i;
        int j;

        public Path(int i, int j){
            this.i = i;
            this.j= j;
        }

        public String toString(){
            return "("+i + ","+j + ")";
        }
    }

    static private int minimumCostPathRecursivePath(int [][] matrix, int i, int j, int [][] cost , List<Path> pathList){

        //cost[i][j] means cost of path from (0,0) to (i,j)
        if (cost[i][j] != 0) {
            return cost[i][j];
        } else {
            if (i == 0 && j == 0) {
                cost[i][j] = matrix[i][j];
            } else if (i == 0) {
                cost[i][j] = matrix[i][j] + minimumCostPathRecursive(matrix,i,j - 1, cost);
            } else if (j == 0) {
                cost[i][j] = matrix[i][j] + minimumCostPathRecursive(matrix,i-1,j , cost);
            } else {
                cost[i][j] = matrix[i][j] + Math.min(minimumCostPathRecursive(matrix,i-1,j , cost),
                        minimumCostPathRecursive(matrix,i,j-1 , cost));
            }
        }
        return  cost[i][j];
    }
}