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

        int ans = waysToParenthesize(symbols, operators);
        System.out.println("ans==> "+ans);
    }

    static int waysToParenthesize(String symbolSequence, String operatorSequence)
    {
        int n = symbolSequence.length();
        int T[][] = new int[n][n];
        int F[][] = new int[n][n];

        for(int g=0;g<n;g++){
            for(int i=0, j=g;j<n;i++,j++){
                if(g==0){
                    char ch = symbolSequence.charAt(i);
                    if(ch== 'T'){
                        T[i][j] =1;
                        F[i][j] = 0;
                    }else{
                        T[i][j] = 0;
                        F[i][j] = 1;
                    }
                }else{
                    for(int k=i;k<j;k++){
                        char opStr = operatorSequence.charAt(k);

                        int lt = T[i][k];
                        int rt = T[k+1][j];
                        int lf = F[i][k];
                        int rf = F[k+1][j];
                        if(opStr == '&'){
                            T[i][j] += lt*rt;
                            F[i][j] += lf*rt+lt*rf+lf*rf;
                        }else if(opStr == '|'){
                            T[i][j] += lt*rt+lf*rt+lt*rf;
                            F[i][j] += lf*rf;
                        }else if (opStr == '^'){
                            T[i][j] += lt*rf + lf*rt;
                            F[i][j] += lf*rf + lt*rt;
                        }
                    }

                }
            }
        }


        return T[0][n-1];
    }
}
