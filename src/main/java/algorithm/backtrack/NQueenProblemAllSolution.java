package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
  Arranging N Queen in N * N size chess board.

  Possible solution of arranging 4 Queen in 4*4 board

  | 0 1 0 0 |
  | 0 0 0 1 |
  | 1 0 0 0 |
  | 0 0 1 0 |

  | 0 0 1 0 |
  | 1 0 0 0 |
  | 0 0 0 1 |
  | 0 1 0 0 |

*/
class NQueenProblemAllSolution {

    /**
      arranging queen on chess board
     */
    private static void arrangeQueen(int[][] board,int size, int rowIndex,List<int [][]> resultList ) {

        if(rowIndex>=size){
            resultList.add(Arrays.stream(board).map(int[]::clone).toArray(int[][]::new));
            return;
        }

        // allocating the queen to cell...
        for(int colIndex = 0; colIndex < size; colIndex++){
            if(isSafeCell(board,rowIndex,colIndex,size)){
                board[rowIndex][colIndex]=1;
                arrangeQueen(board,size,rowIndex+1,resultList);
                board[rowIndex][colIndex]=0;
            }
        }
        return;
    }

    /**
        implementation method...
     */
    static void  solveNQueenProblem(int size){

        List<int [][]> resultList = new ArrayList<int [][]>();
        int [][] board = new int[size][size];

        arrangeQueen(board,size,0,resultList);

        if( resultList.size() >= 1){
            printArrangement(resultList,size);
        }else{
            System.out.print("Not possible to arrange "+size + " on chess board");
        }
    }

    /**
        printing the arrangement...
     */
    private static void printArrangement(List<int [][]> resultList , int size) {
        resultList.forEach(t->{
                    System.out.println();
                    for(int i = 0; i< size; i++){
                        for(int j = 0; j<size; j++){
                            if(t[i][j]!=0){
                                System.out.print("("+i+","+j +")");
                            }
                        }
                    }
                });
    }

    /**
     check the position is available or not
     */
    private static boolean isSafeCell(int[][] board, int rowIndex, int colIndex, int size) {

        //checking the previous placed queen in same column
        for(int i=0;i<rowIndex;i++){
            if(board[i][colIndex] ==1){
                return false;
            }
        }

        //checking the any occupied position in left diagonal
        for(int j=colIndex-1, i=rowIndex-1; i>=0 && j>=0;i--,j--){
            if(board[i][j] ==1){
                return false;
            }
        }


        //checking the any occupied position in left diagonal
        for(int j=colIndex+1, i=rowIndex-1; i>=0 && j<size;i--,j++){
            if(board[i][j] ==1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        int boardSize = 4; //board dimension
        solveNQueenProblem(boardSize);
    }
}