package algorithm.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given a set of n types of rectangular 3-D boxes, where the i^th box has height h(i),
 * width w(i) and depth d(i) (all real numbers). You want to create a stack of boxes
 * which is as tall as possible, but you can only stack a box on top of another box
 * if the dimensions of the 2-D base of the lower box are each strictly larger than those of the 2-D base of the higher box.
 * Of course, you can rotate a box so that any side functions as its base.
 * It is also allowable to use multiple instances of the same type of box.
 *
 * EXAMPLE:
 * Input: cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * Output: 190
 * Explanation:
 * Cuboid 1 is placed on the bottom with the 53x37 side facing down with height 95.
 * Cuboid 0 is placed next with the 45x20 side facing down with height 50.
 * Cuboid 2 is placed next with the 23x12 side facing down with height 45.
 * The total height is 95 + 50 + 45 = 190.
 */

public class BoxStackingProblem {

    public static void main(String[] args){

        int [][]  originalCuboids  = {  {50,45,20},
                                {95,37,53},
                                {45,23,12}
                            };

        int[][] cuboids= createRotations(originalCuboids);

        Arrays.sort(cuboids, new Comparator<int[]>() {
            @Override
            public int compare(int[] box1, int[] box2) {
                return box2[0]*box2[1] - box1[0]*box1[1];
            }
        });

        for(int[] cuboid : cuboids ){
            System.out.println(cuboid[0]+ ","+cuboid[1]+","+cuboid[2]);
        }

        int maxHeight = lis(cuboids);

        System.out.println("max height of box stacking problem is "+maxHeight);
    }

    private static int[][] createRotations(int[][] originalCuboids) {
        int [][]  cuboids = new int [originalCuboids.length*3][3];
        int i =0;
        for(int[] cuboid : originalCuboids){

            cuboids[i]      = cuboid;

            //rotated cuboid
            int [] rotatedCuboid1 = new int[3];
            rotatedCuboid1[0] = cuboid[2];
            rotatedCuboid1[1] = cuboid[0];
            rotatedCuboid1[2] = cuboid[1];


            cuboids[i+1]    = rotatedCuboid1;

            //rotated cuboid
            int [] rotatedCuboid2 = new int[3];
            rotatedCuboid2[0] = cuboid[1];
            rotatedCuboid2[1] = cuboid[2];
            rotatedCuboid2[2] = cuboid[0];

            cuboids[i+2]    = rotatedCuboid2;
            i= i+3;

        }

        return cuboids;
    }

    private static int lis(int [][] arr){
        int[] dp = new int [arr.length];

        for (int i=0; i < arr.length; i++) {
            dp[i] = arr[i][2];
        }

        for (int i=1;i<arr.length;i++){
            for(int j=0;j<i;j++){
                if((arr[i][0] <arr[j][0] &&  arr[i][1] <arr[j][1]) ){
                    dp[i] = Math.max(dp[i], dp[j] + arr[i][2]);
                }
            }
        }

        return Arrays.stream(dp).max().orElse(0);
    }
}
