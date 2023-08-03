package algorithm.backtrack;

import java.util.Arrays;

public class MagnetPuzzleProblem {

    public static void main(String[] args) {

         int[] top         = { 2, -1, -1 };
         int[] bottom      = { -1, -1, 2 };
         int[] left        = { -1, -1, 2, -1 };
         int[] right       = { 0, -1, -1, -1 };

         char[][]  rules   = { { 'T', 'T', 'T' },
                                    { 'B', 'B', 'B' },
                                    { 'T', 'L', 'R' },
                                    { 'B', 'L', 'R' }
                                  };

        solveMagnetPuzzle(top, left, bottom, right, rules);


        top = new int[]{1, -1, -1, 2, 1, -1};
        bottom = new int[]{2, -1, -1, 2, -1, 3};
        left = new int[]{2, 3, -1, -1, -1};
        right = new int[]{-1, -1, -1, 1, -1};
        rules = new char[][]{{'L', 'R', 'L', 'R', 'T', 'T'},
                {'L', 'R', 'L', 'R', 'B', 'B'},
                {'T', 'T', 'T', 'T', 'L', 'R'},
                {'B', 'B', 'B', 'B', 'T', 'T'},
                {'L', 'R', 'L', 'R', 'B', 'B'}
        };
        solveMagnetPuzzle(top, left, bottom, right, rules);
    }

    private static void printSolution(char [][] board){
        Arrays.stream(board).forEach(System.out::println);
    }

    private static void solveMagnetPuzzle(int[] top, int[] left, int[] bottom, int[] right, char[][] rules) {

        char[][] board = new char[left.length][top.length];

        // initialize all cells by `X`
        for (int i = 0; i < left.length; i++) {
            Arrays.fill(board[i], 'X');
        }

        //logic to solve magnet
        if (!solvePuzzle(top, left, bottom, right, rules,board,0,0)){
            System.out.println("Solution does not exist");
            return;
        }

        printSolution(board);

    }

    private static boolean solvePuzzle(int[] top, int[] left, int[] bottom, int[] right, char[][] rules,char[][] board, int col, int row) {

        if(col == board[0].length){
            col=0;
            row++;
        }
        if(row == board.length){
            return isValidConfiguration(top, left, bottom, right,board);
        }

        if(rules[row][col]=='L'){
            if(isSafe(board,row,col,'+',top,left,bottom,right) && isSafe(board,row,col+1,'-',top,left,bottom,right)){
                board[row][col]='+';
                board[row][col+1]='-';
                if(solvePuzzle(top, left, bottom, right, rules,board,col+2,row)){
                    return true;
                }

                board[row][col]='X';
                board[row][col+1]='X';
            }else if(isSafe(board,row,col,'-',top,left,bottom,right)  && isSafe(board,row,col+1,'+',top,left,bottom,right)){
                board[row][col]='-';
                board[row][col+1]='+';
                if(solvePuzzle(top, left, bottom, right, rules,board,col+2,row)){
                    return true;
                }
                board[row][col]='X';
                board[row][col+1]='X';
            }
        }else if (rules[row][col]=='T'){
            if(isSafe(board,row,col,'+',top,left,bottom,right) && isSafe(board,row+1,col,'-',top,left,bottom,right)){
                board[row][col]='+';
                board[row+1][col]='-';
                if(solvePuzzle(top, left, bottom, right, rules,board,col+1,row)){
                    return true;
                }
                board[row][col]='X';
                board[row+1][col]='X';

            }else if(isSafe(board,row,col,'-',top,left,bottom,right) && isSafe(board,row+1,col,'+',top,left,bottom,right)){
                board[row][col]='-';
                board[row+1][col]='+';
                if(solvePuzzle(top, left, bottom, right, rules,board,col+1,row)){
                    return true;
                }
                board[row][col]='X';
                board[row+1][col]='X';
            }

        }
        return solvePuzzle(top, left, bottom, right, rules,board,col+1,row);
    }

    private static boolean isValidConfiguration(int[] top, int[] left, int[] bottom, int[] right, char[][] board) {

        //check the top, left
        for(int i=0;i<top.length;i++){
            if(top[i]>-1){
                if(top[i] < countTheSignVertically(board,i,'+')){
                    return false;
                }
            }
        }

        for(int i=0;i<left.length;i++){
            if(left[i]>-1){
                if(left[i] < countTheSignHorizontally(board,i,'+')){
                    return false;
                }
            }
        }

        //check the bottom, right
        for(int i=0;i<bottom.length;i++){
            if(bottom[i]>-1){
                if(bottom[i] < countTheSignVertically(board,i,'-')){
                    return false;
                }
            }
        }

        for(int i=0;i<right.length;i++){
            if(right[i]>-1){
                if(right[i] < countTheSignHorizontally(board,i,'-')){
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isSafe(char[][] board, int row, int col, char sign,int[] top, int[] left, int[] bottom, int[] right) {

        if((row-1 >=0 && board[row-1][col] == sign) || (col-1>=0 && board[row][col-1] == sign)
                ||(col+1<board[0].length && board[row][col+1] == sign)){
            return false;
        }

        if(sign =='+'){
            //count the left
            if(top[col]>-1){
               if(top[col] <= countTheSignVertically(board,col,sign)){
                   return false;
               }
            }

            //count the top
            if(left[row]>-1){
                if(left[row] <= countTheSignHorizontally(board,row,sign)){
                    return false;
                }
            }
        }else{

            //count the bottom
            if(bottom[col]>-1){
                if(bottom[col] <= countTheSignVertically(board,col,sign)){
                    return false;
                }
            }

            //count the right

            if(right[row]>-1){
                if(right[row] <= countTheSignHorizontally(board,row,sign)){
                    return false;
                }
            }
        }

        return true;
    }

    private static int countTheSignVertically(char[][] board, int col, char sign) {
        int count =0;

        for(int  row=0;row< board.length;row++){
            if(board[row][col] ==sign){
                count++;
            }
        }

        return count;

    }

    private static int countTheSignHorizontally(char[][] board,int row, char sign) {
        int count =0;

        for(int  col=0;col< board[0].length;col++){
           if(board[row][col] ==sign){
               count++;
           }
        }

        return count;

    }
}
