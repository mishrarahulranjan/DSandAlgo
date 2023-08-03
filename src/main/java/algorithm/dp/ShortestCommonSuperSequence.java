package algorithm.dp;

/**
 * Shortest common supersequence (problem)
 * Given two strings s1 and s2, find the length of their shortest common supersequence, the shortest string that has both s1 and s2 subsequences.
 * A string A is a supersequence of a string B if B is a subsequence of A.
 * Example:
 * input:
 * s1 = "abdacebfcab"
 * s2 = "acebfca"
 *
 * output: 11
 * explanation: The shortest common supersequence of s1 and s2 is "abdacebfcab", its length is 11
 */

public class ShortestCommonSuperSequence {

    public static void main(String[] args){
        String s1 = "abdacebfcab";
        String s2 = "acebfca";

        System.out.println("1.sccs is "+scss(s1,s2,0,0));

        System.out.println("2.sccs is "+scss(s1,s2));
    }

    private static int scss(String s1, String s2, int i, int j) {
        if (i == s1.length()){
            return s2.length()-j;
        }else if (j == s2.length()){
            return s1.length()-i;
        }else if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + scss(s1, s2, i + 1, j + 1);
        }else{
            return 1 + Math.min(scss(s1, s2, i+1, j), scss(s1, s2, i, j+1));
        }
    }


    private static int scss(String s1, String s2){

        int m = s1.length();
        int n = s2.length();
        int[][] scs = new int[m+1][n+1];

        for(int i=0;i<m+1;i++){
            scs[i][0] = i;
        }

        for(int j=0;j<n+1;j++){
            scs[0][j] =j;
        }

        for(int i =1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    scs[i][j] = 1+ scs[i-1][j-1];
                }else{
                    scs[i][j] = 1+ Math.min(scs[i-1][j],scs[i][j-1]);
                 }
            }
        }

        return scs[m][n];
    }
}
