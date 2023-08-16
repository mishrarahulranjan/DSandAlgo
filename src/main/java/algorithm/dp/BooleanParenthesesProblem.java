package algorithm.dp;

import java.util.Arrays;

/**
 * Given a boolean expression with the following symbols.
 *
 * Symbols
 *     'T' ---> true
 *     'F' ---> false
 * And following operators filled between symbols
 *
 * Operators
 *     &   ---> boolean AND
 *     |   ---> boolean OR
 *     ^   ---> boolean XOR
 * Count the number of ways we can parenthesize the expression so that the value of expression
 * evaluates to true.
 * Let the input be in form of two arrays one contains the symbols (T and F) in order
 * and the other contains operators (&, | and ^}
 * Input: symbol[]    = {T, F, T}
 *        operator[]  = {^, &}
 * Output: 2
 * The given expression is "T ^ F & T", it evaluates true
 * in two ways "((T ^ F) & T)" and "(T ^ (F & T))"
 */

public class BooleanParenthesesProblem {
    public static void main(String[] args){
        String symbols = "TTFT";
        String operators = "|&^";

        StringBuilder S = new StringBuilder();
        int j = 0;

        for (int i = 0; i < symbols.length(); i++)
        {
            S.append(symbols.charAt(i));
            if (j < operators.length())
                S.append(operators.charAt(j++));
        }

        // We obtain the string  T|T&F^T
        int N = S.length();

        // There are 4 ways
        // ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and
        // (T|((T&F)^T))
        System.out.println("number of ways is "+countWays(N, S.toString()));
    }

    private static boolean countWays(int N, String str) {
        int dp[][][] = new int[N + 1][N + 1][2];

        for (int row[][] : dp)
            for (int col[] : row)
                Arrays.fill(col, -1);
        return parenthesis_count(str, 0, N - 1, true, dp);
    }

    private static boolean parenthesis_count(String str, int i, int j, boolean isTrue, int[][][] dp) {

        if (i > j)
            return false;

        if (i == j)
        {
            if (isTrue)
            {
                return (str.charAt(i) == 'T') ? true : false;
            }
            else
            {
                return (str.charAt(i) == 'F') ? true : false;
            }
        }

        if (dp[i][j][isTrue] != -1)
            return dp[i][j][isTrue];

        int temp_ans = 0;

        int leftTrue, rightTrue, leftFalse, rightFalse;

        for (int k = i + 1; k <= j - 1; k = k + 2)
        {

            if (dp[i][k - 1][1] != -1)
                leftTrue = dp[i][k - 1][1];
            else
            {
                // Count number of True in left Partition
                leftTrue = parenthesis_count(str, i, k - 1,
                        1, dp);
            }
            if (dp[i][k - 1][0] != -1)
                leftFalse = dp[i][k - 1][0];
            else
            {

                // Count number of False in left Partition
                leftFalse = parenthesis_count(str, i, k - 1,
                        0, dp);
            }
            if (dp[k + 1][j][1] != -1)
                rightTrue = dp[k + 1][j][1];
            else
            {

                // Count number of True in right Partition
                rightTrue = parenthesis_count(str, k + 1, j,
                        1, dp);
            }
            if (dp[k + 1][j][0] != -1)
                rightFalse = dp[k + 1][j][0];
            else
            {

                // Count number of False in right Partition
                rightFalse = parenthesis_count(str, k + 1,
                        j, 0, dp);
            }

            // Evaluate AND operation
            if (str.charAt(k) == '&')
            {
                if (isTrue == 1)
                {
                    temp_ans
                            = temp_ans + leftTrue * rightTrue;
                }
                else
                {
                    temp_ans = temp_ans
                            + leftTrue * rightFalse
                            + leftFalse * rightTrue
                            + leftFalse * rightFalse;
                }
            }
            // Evaluate OR operation
            else if (str.charAt(k) == '|')
            {
                if (isTrue == 1)
                {
                    temp_ans = temp_ans
                            + leftTrue * rightTrue
                            + leftTrue * rightFalse
                            + leftFalse * rightTrue;
                }
                else
                {
                    temp_ans
                            = temp_ans + leftFalse * rightFalse;
                }
            }

            // Evaluate XOR operation
            else if (str.charAt(k) == '^')
            {
                if (isTrue == 1)
                {
                    temp_ans = temp_ans
                            + leftTrue * rightFalse
                            + leftFalse * rightTrue;
                }
                else
                {
                    temp_ans = temp_ans
                            + leftTrue * rightTrue
                            + leftFalse * rightFalse;
                }
            }
            dp[i][j][isTrue] = temp_ans;
        }
        return temp_ans;
    }
}
