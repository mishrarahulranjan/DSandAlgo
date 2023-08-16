package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 Square matrix of ones (problem)
 Given a matrix of ones and zeros, find the area of the greatest square submatrix full of ones.

 A square matrix is a matrix whose the number of rows is equal to the number of columns.

 Example:
 input:
 matrix = [
 [0, 0, 1, 1, 1, 0],
 [1, 0, 1, 1, 1, 1],
 [0, 1, 1, 1, 1, 0],
 [1, 1, 1, 1, 0, 1],
 [0, 1, 0, 1, 1, 1]
 ]
output: 9

 */
public class SquareMatrixOfOne {

    public static void main(String[] args) {

        int[][] matrix = {
                {0, 0, 1, 1, 1, 0},
                {1, 0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0, 1},
                {0, 1, 0, 1, 1, 1}
        };

        System.out.println("1.square Matrix is " + squareMatrix(matrix));
        System.out.println("2.square Matrix is " + squareMatrixMemoization(matrix));
        System.out.println("3.square matrix is "+squareMatrixTabulation(matrix));
    }

    private static int squareMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max_size = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max_size = Math.max(max_size, matrixSizeRecursive(matrix, i, j));
            }
        }
        return max_size * max_size;
    }


    private static int matrixSizeRecursive(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || matrix[i][j] == 0) {
            return 0;
        } else {
            return 1 + min(matrixSizeRecursive(matrix, i - 1, j),
                    matrixSizeRecursive(matrix, i, j - 1),
                    matrixSizeRecursive(matrix, i - 1, j - 1));
        }
    }

    private static int squareMatrixMemoization(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max_size = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max_size = Math.max(max_size, matrixSizeMemoization(matrix, i, j, new HashMap<String, Integer>()));
            }
        }
        return max_size * max_size;
    }


    private static int matrixSizeMemoization(int[][] matrix, int i, int j, Map<String, Integer> lookUp) {

        String key = i + "_key_" + j;

        if (!lookUp.containsKey(key)) {
            if (i < 0 || j < 0 || matrix[i][j] == 0) {
                return 0;
            } else {
                int val = 1 + min(matrixSizeRecursive(matrix, i - 1, j),
                        matrixSizeRecursive(matrix, i, j - 1),
                        matrixSizeRecursive(matrix, i - 1, j - 1));

                lookUp.put(key, val);
            }
        }

        return lookUp.get(key);

    }

    private static int min(int matrixSizeRecursive, int matrixSizeRecursive1, int matrixSizeRecursive2) {
        int c = Math.min(matrixSizeRecursive, matrixSizeRecursive1);
        return Math.min(c, matrixSizeRecursive2);
    }


    private static int squareMatrixTabulation(int[][] matrix) {
        int m= matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = matrix[0][0];

        for(int j=0;j< n;j++){
            dp[0][j] = matrix[0][j];
        }

        for(int i=0;i< m;i++){
            dp[i][0] = matrix[i][0];
        }

        int max=0;

        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if(matrix[i][j]==0){
                    dp[i][j] =0;
                }else{
                    dp[i][j] =1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]);
                }
                if(max <  dp[i][j] ){
                    max= dp[i][j];
                }
            }
        }

        return max*max;

    }




}
