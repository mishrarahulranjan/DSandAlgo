package algorithm.dp;

/**
 * Gold mine (problem)
 * Given a mine of n rows and m columns where mine[i][j] represents the amount of gold
 * that is present there, we want to enter from the top of the mine and take as much
 * gold as possible when exiting from the bottom, knowing that we can only move to the bottom,
 * to the bottom-left, or to the bottom-right. We can exit from anywhere from the last row.
 */
public class GoldMineProblem {

    public static void main(String[] args) {

        int[][] mine = {
                            {3, 2, 12, 15, 10},
                            {6, 19, 7, 11, 17},
                            {8, 5, 12, 32, 21},
                            {3, 20, 2, 9, 7}
                        };

        System.out.println("Maximum Mining is "+gMine(mine));
        System.out.println("Recursive Maximum Mining is "+gMineRecursive(mine));
    }

    private static int gMineRecursive(int[][] mine){
        int n= mine[0].length;
        int m = mine.length;
        int max =0;
        for (int j=0;j <n;j++){
            max= Math.max(max,gMineRec(mine,m-1,j));
        }
        return max;
    }
    private static int gMineRec(int [][] mine, int m,int j){
        if(m < 0 || j < 0 || j+1 > mine[0].length) {
            return 0;
        }else{
            return mine[m][j]+max(gMineRec(mine,m-1,j-1),gMineRec(mine,m-1,j),gMineRec(mine,m-1,j+1));
        }
    }

    private static int gMine(int[][] mine){

        int m = mine.length;
        int n= mine[0].length;

        int[][] gMine = new int[m][n];

        for(int i=0;i<n;i++){
            gMine[0][i] = mine[0][i];
        }

        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                int top_left =0;
                int top_right = 0;
                if(j-1 >=0){
                    top_left=gMine[i-1][j-1];
                }
                if(j+1 < n){
                    top_left=gMine[i-1][j+1];
                }

                int top = gMine[i-1][j];
                gMine[i][j] = mine[i][j]+ max(top, top_left, top_right);
            }
        }

        int max=gMine[m-1][0];
        for(int j=1;j<n;j++){
            if(max < gMine[m-1][j]){
                max=gMine[m-1][j];
            }
        }
        return max;
    }

    static int max(int a, int b, int c){
        int d = Math.max(a,b);

        return Math.max(c,d);
    }
}
