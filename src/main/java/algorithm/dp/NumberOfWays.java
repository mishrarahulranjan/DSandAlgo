package algorithm.dp;

/**
 Given a matrix where a cell has the value of 1 if it's a wall and 0 if not,
 find the number of ways to go from the top-left cell to the bottom-right cell,
 knowing that it's not possible to pass from a wall and we can only go to the right or to the bottom

 Paths in matrix (problem)
 Given a matrix where a cell has the value of 1 if it's a wall and 0 if not,
 find the number of ways to go from the top-left cell to the bottom-right cell,
 knowing that it's not possible to pass from a wall &  we can only go to the right or to the bottom

 Example:
 input:
 matrix = [
 [0, 0, 1, 0, 1],
 [0, 0, 0, 0, 1],
 [0, 0, 1, 0, 0],
 [1, 0, 0, 0, 0]
 ]
 output: 7

*/
public class NumberOfWays {

    public static void main(String[] args) {

      int[][]  matrix = {
                              {0, 0, 1, 0, 1},
                              {0, 0, 0, 0, 1},
                              {0, 0, 1, 0, 0},
                              {1, 0, 0, 0, 0}
                        };

      int[][] ways = new int[matrix.length][matrix[0].length];

      for (int i = 0, len = ways.length; i < len; i++){
        for (int j = 0, len1 = ways[0].length; j < len1; j++){
                ways[i][j] = -1;
        }
      }

      System.out.println("1.noOfWays: "+findWaysV1(matrix,0,0,ways));

        System.out.println("2.noOfWays: "+findWaysV2(matrix));
    }

    static int findWaysV1(int[][] matrix, int i, int j, int[][] ways){

        if(i==matrix.length-1 && j==matrix[0].length-1){
            return 1;
        }

        if (ways[i][j] == -1) {
            if (matrix[i][j] == 0) {
                if (i + 1 < matrix.length && j + 1 < matrix[0].length
                        && matrix[i + 1][j] == 0 && matrix[i][j + 1] == 0) {
                    ways[i][j] = findWaysV1(matrix, i + 1, j, ways) +
                                 findWaysV1(matrix, i, j + 1, ways);
                } else if (i + 1 < matrix.length  && matrix[i + 1][j] == 0) {
                    ways[i][j] = findWaysV1(matrix, i + 1, j, ways);
                } else if (j + 1 < matrix[0].length  && matrix[i][j + 1] == 0) {
                    ways[i][j] = findWaysV1(matrix, i, j + 1, ways);
                } else {
                    ways[i][j] = 0;
                }
            } else {
                return 0;
            }
        }
        return ways[i][j];
    }

    static int findWaysV2(int[][] matrix){

        int[][] path = new int[matrix.length][matrix[0].length];
        if(matrix[0][0]==0){
            path[0][0]=1;
        }

        for(int i =1; i< matrix.length; i++){
            if(matrix[i][0] == 0){
                path[i][0] = path[i-1][0];
            }
        }

        for(int j =1; j< matrix[0].length; j++){
            if(matrix[0][j] == 0){
                path[0][j] = path[0][j-1];
            }
        }

        for (int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[i][j] ==0){
                    path[i][j] = path[i-1][j]+path[i][j-1];
                }
            }
        }



       return path[matrix.length-1][matrix[0].length-1];
    }
}
