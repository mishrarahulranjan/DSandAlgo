package algorithm.backtrack;

public class SudokuProblem {

    private static int GRID_SIZE = 9;

    public static void main(String args[]){

        int grid[][] = {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
        };

        if(solveSudoku(grid,0,0)){
            System.out.println("Solution is available for this sudoku");
        }else{
            System.out.println("no solution found for this sudoku");
        }
        printGrid(grid);
    }

    private static void printGrid(int[][] grid) {
        for(int i=0;i<9;i++){
            for(int j=0; j< 9;j++){
                System.out.print(grid[i][j]+ " ");
            }
            System.out.println();
        }
    }

    private static boolean solveSudoku(int[][] grid, int row, int col) {

        if(row >=GRID_SIZE-1 && col>=GRID_SIZE){
            return true;
        }else if(col>=GRID_SIZE){
            row++;
            col=0;
        }else if(row>=GRID_SIZE){
           System.out.println(row);
        }

        if(grid[row][col]==0){
            for(int number=1;number<=GRID_SIZE;number++) {
                if (isSafeNumber(grid, row, col, number)) {
                    grid[row][col] = number;
                    if (solveSudoku(grid, row, col + 1)){
                        return true;
                    }else{
                        grid[row][col] = 0;
                    }
                }
            }
        }else{
            return solveSudoku(grid, row, col + 1);
        }

        return false;
    }


    private static boolean isSafeNumber(int[][] grid, int rowIndex, int colIndex, int number) {

        for(int row=0;row<GRID_SIZE;row++){
            if(grid[row][colIndex]==number){
                return false;
            }
        }

        for(int col=0;col<GRID_SIZE;col++){
            if(grid[rowIndex][col]==number){
                return false;
            }
        }

        // Corresponding square has
        // unique number (box-clash)
        int sqrt = (int)Math.sqrt(GRID_SIZE);
        int boxRowStart = rowIndex - rowIndex % sqrt;
        int boxColStart = colIndex - colIndex % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++)
            {
                if (grid[r][d] == number)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
