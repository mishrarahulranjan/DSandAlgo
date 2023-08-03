package algorithm.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixTraversal {

    private static List<List<Integer>> dataList= new ArrayList<>();

    public static void main(String args[]){

        int[][] matrix = {
                            { 1, 2, 3 },
                            { 4, 5, 6 }
                        };

        List<Integer> list = new ArrayList<>();

        printMatrix(matrix, matrix.length-1, matrix[0].length-1, 0, 0, list);
        System.out.println(dataList);
    }

    public static void printMatrix(int matrix[][], int m, int n,
                                   int i, int j, List<Integer> list)
    {
        //return if i or j crosses matrix size
        if(i > m || j > n)
            return;
        list.add(matrix[i][j]);
        if(i == m && j == n){
            dataList.add(list.stream().collect(Collectors.toList()));
        }
        printMatrix(matrix, m, n, i+1, j, list);
        printMatrix(matrix, m, n, i, j+1, list);
        list.remove(list.size()-1);
    }
}
