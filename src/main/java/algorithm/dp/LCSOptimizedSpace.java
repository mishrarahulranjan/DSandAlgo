package algorithm.dp;

public class LCSOptimizedSpace {

    public static void main(String[] args){
        String X = "AGGTAB";
        String Y = "GXTXAYB";

        System.out.println("Length of LCS is " +lcs(X, Y));
        System.out.println("Length of LCS is " +lcs2(X, Y));
    }

    public static int lcs(String X,String Y){

        int m = X.length(), n = Y.length();
        int L[][] = new int[2][n+1];

        int bi=0;

        for (int i = 0; i <= m; i++) {
            bi = i %2;
            for (int j = 0; j <= n; j++){
                if (i == 0 || j == 0)
                    L[bi][j] = 0;
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    L[bi][j] = L[1 - bi][j - 1] + 1;
                else
                    L[bi][j] = Math.max(L[1 - bi][j],L[bi][j - 1]);
            }
        }
        return L[bi][n];
    }

    public static int lcs2(String text1, String text2) {
        int[] dp = new int[text2.length()+1];
        for(int i = 0; i< text1.length(); i++){
            int prev = dp[0];
            for(int j = 1; j < dp.length; j++){
                int temp = dp[j];
                if(text1.charAt(i) != text2.charAt(j-1)){
                    dp[j] = Math.max(dp[j-1], dp[j]);
                }else{
                    dp[j] = prev +1;
                }
                prev = temp;
            }
        }
        return dp[dp.length-1];
    }
}
