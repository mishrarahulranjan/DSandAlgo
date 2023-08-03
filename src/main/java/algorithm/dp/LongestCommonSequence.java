package algorithm.dp;

/**
 * Longest common subsequence (problem)
 * Given two strings s1 and s2, find the length of their longest common subsequence.
 * A subsequence of a string s is a subset of its characters that are not necessarily adjacent
 * but have to be in the right order.
 Example:
 * input:
 * s1 = "abdacbab"
 * s2 = "acebfca"
 * output: 4
 * explanation: A possible longest common subsequence of s1 and s2 is "abca"
 */

public class LongestCommonSequence {

    public static void main(String[] args){
        String s1   = "AGGTAB";
        String s2   = "GXTXAYB";

        char[] X    = s1.toCharArray();
        char[] Y    = s2.toCharArray();
        int m       = X.length;
        int n       = Y.length;

        int[][] Lmn = new int[m+1][n+1];
        for(int i=0;i<m + 1;i++){
            for(int j=0;j<n + 1;j++){
                Lmn[i][j] = -1;
            }
        }

        System.out.println("Length of LCS  using  Tabulation   is "  +lcsTabulation( X, Y, m, n ) );
        System.out.println("Length of LCS  using  Memoization  is " +lcsMemoization( X, Y, m, n,Lmn ) );
        System.out.println("Length of LCS  using  Recursion    is "   +lcsRecursive( X, Y, m, n ) );
        System.out.println("Length of LCS  using  Recursion-V2 is "   +lcsRecursiveV2( X, Y, 0, 0 ) );

        System.out.println("LCS using tabulation               is " +lcsStringTabulation( X, Y, m, n ) );

        StringBuilder sb = new StringBuilder();
        lcsStringRecursive( X, Y, m, n ,sb);
        System.out.println("LCS using recursion                is " + sb.reverse());
    }

    private static int lcsRecursive(char[] x, char[] y, int m, int n) {
        if(m==0 || n==0){
            return 0;
        }else if(x[m-1]==y[n-1]){
            return 1+lcsRecursive(x,y,m-1,n-1);
        }else{
            return max(lcsRecursive(x,y,m-1,n),lcsRecursive(x,y,m,n-1));
        }
    }

    private static int lcsRecursiveV2(char[] x, char[] y, int i, int j) {
        if(i==x.length || j== y.length){
            return 0;
        }else if(x[i]==y[j]){
            return 1+lcsRecursiveV2(x,y,i+1,j+1);
        }else{
            return max(lcsRecursiveV2(x,y,i+1,j),lcsRecursiveV2(x,y,i,j+1));
        }
    }

    private static int lcsStringRecursive(char[] x, char[] y, int m, int n,StringBuilder sb) {
        if(m==0 || n==0){
            return 0;
        }else if(x[m-1]==y[n-1]){
            sb.append(x[m-1]);
            return 1+lcsStringRecursive(x,y,m-1,n-1,sb);
        }else{
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int l1 = lcsStringRecursive(x,y,m-1,n,sb1);
            int l2 = lcsStringRecursive(x,y,m,n-1,sb2);
            if(l1> l2){
                sb.append(sb1);
                return l1;
            }else{
                sb.append(sb2);
                return l2;
            }
        }
    }

    private static int lcsMemoization(char[] x, char[] y, int m, int n,int[][] dp) {

        if (dp[m][n] == -1){
            if(m==0 || n==0){
                dp[m][n]=0;
            }else if(x[m-1]==y[n-1]){
                dp[m][n]=1+lcsRecursive(x,y,m-1,n-1);
            }else{
                dp[m][n]=max(lcsRecursive(x,y,m-1,n),lcsRecursive(x,y,m,n-1));
            }
        }

        return dp[m][n];
    }

    private static  int lcsTabulation(char[] x, char[] y, int m, int n) {
        int[][] Lmn = new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0){
                    Lmn[i][j] = 0;
                }else if (x[i-1] == y[j-1]){
                    Lmn[i][j] = 1+Lmn[i-1][j-1];
                }else{
                    Lmn[i][j] = max(Lmn[i-1][j],Lmn[i][j-1]);
                }
            }
        }
        return Lmn[m][n];
    }

    private static  String lcsStringTabulation(char[] x, char[] y, int m, int n) {
        int[][] Lmn = new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0){
                    Lmn[i][j] = 0;
                }else if (x[i-1] == y[j-1]){
                    Lmn[i][j] = 1+Lmn[i-1][j-1];
                }else{
                    Lmn[i][j] = max(Lmn[i-1][j],Lmn[i][j-1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i=m;
        int j=n;

        while (i>0 && j>0){
            if(x[i-1] == y[j-1]){
                sb.append(x[i-1]);
                i--;
                j--;
            }else if(Lmn[i-1][j] > Lmn[i][j-1]){
                i--;
            }else{
                j--;
            }
        }

        return sb.reverse().toString();
    }

    private static int max(int num1, int num2) {
        return Math.max(num1, num2);
    }
}
