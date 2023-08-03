package algorithm.backtrack;
/**
  Arranging N Queen in N * N size chess board

  Example of arranging 4 Queen in 4*4 board
  | 0 1 0 0 |
  | 0 0 0 1 |
  | 1 0 0 0 |
  | 0 0 1 0 |

   Example of arranging 8 Queen in 8*8 board
  | 1 0 0 0 0 0 0 0 |
  | 0 0 0 0 1 0 0 0 |
  | 0 0 0 0 0 0 0 1 |
  | 0 0 0 0 0 1 0 0 |
  | 0 0 1 0 0 0 0 0 |
  | 0 0 0 0 0 0 1 0 |
  | 0 1 0 0 0 0 0 0 |
  | 0 0 0 1 0 0 0 0 |
 */
class NQueenProblem{

    //arrange queen
    private static boolean arrangeQueen(int[][] board,int size, int rowIndex) {
        if(rowIndex>=size){
            return true;
        }
        for(int colIndex=0;colIndex<size;colIndex++){
            if(isSafeCell(board,rowIndex,colIndex,size)){
                board[rowIndex][colIndex]=1;
                if(arrangeQueen(board,size,rowIndex+1)){
                    return true;
                }else{
                    board[rowIndex][colIndex]=0;
                }
            }
        }
        return false;
    }

    //implementation method...
    static void  solveNQueenProblem(int size){
        int [][] board = new int[size][size];
        if(arrangeQueen(board,size,0)){
            printArrangement(board,size);
        }else{
            System.out.print("Not possible to arrange "+size + " on chess board");
        }
    }

    /**
        printing the arrangement...
     */
    private static void printArrangement(int[][] board, int size) {
        for(int i = 0; i< size; i++){
            for(int j = 0; j<size; j++){
                if(board[i][j]!=0){
                    System.out.print("("+i+","+j +")");
                }
            }
        }
    }

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


        //checking the any occupied position in right diagonal

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