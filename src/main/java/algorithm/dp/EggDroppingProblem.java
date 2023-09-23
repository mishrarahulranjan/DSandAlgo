package algorithm.dp;

/**
 * You are given k identical eggs , you have access to a building with n floors labeled from 1 to n.
 *
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at
 * a floor higher than f will break, and any egg dropped at or below floor f will not break.
 *
 * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n).
 * If the egg breaks, you can no longer use it. However, if the egg does not break,
 * you may reuse it in future moves.
 *
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 Example 1:

 Input: k = 1, n = 2
 Output: 2
 Explanation:
 Drop the egg from floor 1. If it breaks, we know that f = 0.
 Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
 If it does not break, then we know f = 2.
 Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
 Example 2:

 Input: k = 2, n = 6
 Output: 3
 Example 3:

 Input: k = 3, n = 14
 Output: 4
 */
public class EggDroppingProblem {

    public static void main(String args[]){
        int eggs = 2, floors = 10;
        System.out.println("1. minm trials is " + eggDrop(eggs, floors));

        eggs = 2; floors = 6;
        System.out.println("2. minm trials is " + eggDrop(eggs, floors));

        eggs = 3; floors = 14;
        System.out.println("3. minm trials is " + eggDrop(eggs, floors));
    }
    static int eggDrop(int eggs, int floors) {

        int [][] eggDrops = new int [eggs+1][floors+1];
        for (int i = 1; i <=eggs ; i++) {
            eggDrops[i][0] = 0;
            eggDrops[i][1] = 1;
        }

        for (int i = 1; i <=floors ; i++) {
            eggDrops[1][i] = i;
        }

        for (int i = 2; i <=eggs ; i++) {
            for (int j = 2; j <=floors ; j++) {
                eggDrops[i][j] = Integer.MAX_VALUE;
                int tempResult;
                for (int k = 1; k <=j ; k++) {
                    tempResult = 1 + Math.max(eggDrops[i-1][k-1], eggDrops[i][j-k]);
                    eggDrops[i][j] = Math.min(tempResult,eggDrops[i][j]);
                }
            }
        }
        return eggDrops[eggs][floors];
    }


}
