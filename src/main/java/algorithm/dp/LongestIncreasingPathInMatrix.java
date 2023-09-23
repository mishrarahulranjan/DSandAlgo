package algorithm.dp;

import java.util.Arrays;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 */

public class LongestIncreasingPathInMatrix {

    /* Driver program to test above function */
    public static void main(String[] args){
        int mat[][] = {
                { 1, 2, 3, 4 },
                { 2, 2, 3, 4 },
                { 3, 2, 3, 4 },
                { 4, 5, 6, 7 },
        };
        int n = 4, m = 4;
        System.out.println(wrapper(mat, n, m));
    }

    static int LIP(int dp[][], int mat[][], int n,int m, int x, int y){
        // If value not calculated yet.
        if (dp[x][y] < 0) {
            int result = 0;

            // If reach bottom right cell, return 1.
            if (x == n - 1 && y == m - 1)
                return dp[x][y] = 1;

            // If reach the corner of the matrix.
            if (x == n - 1 || y == m - 1)
                result = 1;

            // If value greater than below cell.
            if (x + 1 < n && mat[x][y] < mat[x + 1][y])
                result = 1 + LIP(dp, mat, n, m, x + 1, y);

            // If value greater than left cell.
            if (y + 1 < m && mat[x][y] < mat[x][y + 1])
                result = Math.max(result, 1 + LIP(dp, mat, n, m, x, y + 1));

            dp[x][y] = result;
        }

        return dp[x][y];
    }

    static int wrapper(int mat[][], int n, int m){
        int dp[][] = new int[10][10];
        for (int i = 0; i < 10; i++)
            Arrays.fill(dp[i], -1);

        return LIP(dp, mat, n, m, 0, 0);
    }
}
