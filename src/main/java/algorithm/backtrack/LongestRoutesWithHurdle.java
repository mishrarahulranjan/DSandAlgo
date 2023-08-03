package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

public class LongestRoutesWithHurdle {

    private static int n        = 4;
    private static int[] xMove  = {1,0,-1,0};
    private static int[] yMove  = {0,1,0,-1};

    static class Position {
        int x;
        int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Counter {
        boolean status;
        int value;
        public Counter(int value){
            this.value = value;
        }

        public void inCr(){
            this.value+=1;
        }

        public void setValue(int val){
            this.value=val;
        }
        public void setFlag(){
            this.status= true;
        }

        public boolean isFlagSet() {
            return status;
        }
    }

    public static boolean isSafe(int x, int y, int[][] arr,boolean[][]  visited,int row, int col){
        return (x>=0 && x<row && y>=0 && y<col && arr[x][y]!= 0 && !visited[x][y]);
    }

    public static List<Position> getAllPositions(int x, int y, int[][] arr,boolean[][]  visited){
        List<Position> positions =new ArrayList<>(n);
        for(int i=0;i<n;i++){
            if(isSafe(x+xMove[i],y+yMove[i],arr,visited,arr.length,arr[0].length)){
                positions.add(new Position(x+xMove[i],y+yMove[i]));
            }
        }
        return positions;
    }
    public static void main(String[] args){


        int mat[][] = {
                            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                            { 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
                            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
                       };
        boolean[][]  visited= new boolean[mat.length][mat[0].length];
        Counter counter = new Counter(0);
        Counter maxCounter = new Counter(Integer.MIN_VALUE);
        findLongestPath(mat, 0, 0, 1, 7,visited,maxCounter,counter);
        if(maxCounter.isFlagSet()){
            System.out.println("length of longest path is "+maxCounter.value);
        }else{
            System.out.println("No solution exists");
        }

    }

    private static boolean findLongestPath(int[][] mat, int xS, int yS, int xD, int yD,
                                           boolean[][]  visited,Counter maxCounter,Counter counter) {
        if(xS==xD && yS==yD){
            return true;
        }

        List<Position> positions = getAllPositions(xS,yS,mat,visited);
        for(Position pos: positions) {
            visited[pos.x][pos.y] = true;
            int previousValue = counter.value;
            counter.inCr();
            if (findLongestPath(mat, pos.x, pos.y, xD, yD, visited, maxCounter, counter)) {
                maxCounter.setFlag();
                if (maxCounter.value < counter.value) {
                    maxCounter.value  = counter.value;
                }
            }
            visited[pos.x][pos.y] = false;
            counter.setValue(previousValue);
        }
        return false;
    }
}
