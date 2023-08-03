package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 Edit distance (problem)
 Given two strings word1 and word2, calculate their edit distance.
 The edit distance in this problem is defined as the minimum number of insertions,
 deletions, and substitutions of characters to go from word1 to word2.
*/
public class EditDistance {
    public static void main(String[] args){

        String word1 = "inside";
        String word2 = "index";
        System.out.println("1.edit Distance is "+dist(word1,word2,0,0));
        System.out.println("2.edit Distance is "+dist(word1,word2,0,0, new HashMap<>()));
        System.out.println("3.edit Distance is "+editDistance(word1,word2));
    }

    private static int dist(String word1, String word2, int i, int j){
        if (i == word1.length()){
            return word2.length()-j;
        }else if (j == word2.length()){
            return word1.length()-i;
        }else if(word1.charAt(i) == word2.charAt(j)){
            return dist(word1, word2, i+1, j+1);
        }else{
            return 1 + min( dist(word1, word2, i+1, j),
                            dist(word1, word2, i, j+1),
                            dist(word1, word2, i+1, j+1));
        }
    }

    private static int dist(String word1, String word2, int i, int j, Map<String,Integer> lookUp){
        String key = i+"_"+j;
        if(!lookUp.containsKey(key)){
            if (i == word1.length()){
                return word2.length()-j;
            }else if (j == word2.length()){
                return word1.length()-i;
            }else if(word1.charAt(i) == word2.charAt(j)){
                int value = dist(word1, word2, i+1, j+1);
                lookUp.put(key, value);
            }else{
                int value =  1 + min(dist(word1, word2, i+1, j), dist(word1, word2, i, j+1), dist(word1, word2, i+1, j+1));
                lookUp.put(key, value);
            }
        }
        return lookUp.get(key);
    }

    private static int editDistance(String s1, String s2){

        int m       = s1.length();
        int n       = s2.length();
        int[][] dp  = new int[m+1][n+1];

        for (int i=0; i<m+1;i++){
            dp[i][0] = i;
        }

        for (int j=0; j<n+1;j++){
            dp[0][j] = j;
        }

        for(int i =1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1+ min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]);
                }
            }
        }

        return dp[m][n];
    }

    private static int min(int i, int i1, int i2) {
        int d = Math.min(i, i1);
        return Math.min(d,i2);
    }
}
