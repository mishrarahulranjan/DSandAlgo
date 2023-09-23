package algorithm.dp;

/**
 * You have n dice, and each die has k faces numbered from 1 to k.
 *
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways)
 * to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large,
 * return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3
 */

public class DiceThrowProblem {
    public static void main(String[] args){
        /*
        int noOfDice (n)    = 1;
        int faces (m)      = 6;
        int targetSum (x)  = 3;
        */
        System.out.println("1.number of ways to get target sum is "+solveDiceProblem(1,6,3));
        System.out.println("2.number of ways to get target sum is "+solveDiceProblem(4, 2, 1));
        System.out.println("3.number of ways to get target sum is "+solveDiceProblem(2, 2, 3));
        System.out.println("4.number of ways to get target sum is "+solveDiceProblem(6, 3, 8));
        System.out.println("5.number of ways to get target sum is "+solveDiceProblem(4, 2, 5));
        System.out.println("6.number of ways to get target sum is "+solveDiceProblem(4, 3, 5));
    }

    private static int solveDiceProblem(int n, int m, int x) {

        int [][] table = new int[n+1][x+1];

        for(int j = 1; j <= m && j <= x; j++){
            table[1][j] = 1;
        }

        for(int i = 2; i <= n;i ++){
            for(int j = 1; j <= x; j++){
                for(int k = 1; k < j && k <= m; k++)
                    table[i][j] += table[i-1][j-k];
            }
        }

       return table[n][x];
    }
}
