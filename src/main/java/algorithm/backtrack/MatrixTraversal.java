package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

public class MatrixTraversal {

    public static void main(String[] args){

        int n = 3, m = 3;

        int[][] grid
                = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
                };

        findPaths(grid, 0, 0, n, m,new ArrayList<>());
    }

    public static void display(List<Integer> ans){
        for (int i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static boolean isSafe(int r, int c, int[][] visited, int n, int m){
        return (r < n && c < m && visited[r][c] != -1);
    }

    public static void findPaths(int[][] grid, int r, int c, int n, int m, List<Integer> ans){

        if (r == n - 1 && c == m - 1) {
            ans.add(grid[r][c]);
            display(ans);
            ans.remove(ans.size() - 1);
            return;
        }

        int ch = grid[r][c];

        ans.add(ch);
        grid[r][c]= -1;

        if (isSafe(r + 1, c, grid, n, m)) {
            findPaths(grid, r + 1, c, n, m, ans);
        }

        if (isSafe(r, c + 1, grid, n, m)) {
            findPaths(grid, r, c + 1, n, m, ans);
        }

        grid[r][c] = ch;
        ans.remove(ans.size() - 1);

        return;
    }
}
