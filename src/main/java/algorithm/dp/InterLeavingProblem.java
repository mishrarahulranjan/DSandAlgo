package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 Interleaving string (problem)
 Given 3 strings s1, s2, and s3, check if s3 can be formed by an interleaving of s1 and s2.

 An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 s = s1 + s2 + ... + sn
 t = t1 + t2 + ... + tm
 |n - m| <= 1

 The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 Example:
 input:
 s1 = "aabcc"
 s2 = "dbbca"
 s3 = "aadbbcbcac"

 output: true

 explanation:
 s1 = "aabcc" = "aa" + "bc" + "c"
 s2 = "dbbca" = "dbbc" + "a"
 s3 = "aadbbcbcac" = "aa" + "dbbc" + "bc" + "a" + "c"
 You can see that we could make s3 by taking "aa" from s1, "dbbc" from s2, "bc" from s1, "a" from s2, and "c" from s1
 */
public class InterLeavingProblem {
    public static void main(String[] args){

        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        System.out.println("1.interleaving is possible? "+interLeaving(s1,s2,s3,0,0));
        System.out.println("2.interleaving is possible? "+interLeavingMemoization(s1,s2,s3,0,0, new HashMap<>()));
        System.out.println("3.interleaving is possible? "+interLeavingTabulation(s1,s2,s3));
    }

    private static boolean interLeaving(String s1,String s2,String s3, int i, int j) {

        if(s1.length()+s2.length() != s3.length()){
            return false;
        }else if(i == s1.length() && j==s2.length()){
            return true;
        }else{
            boolean check_s1 = ((i<s1.length()) && (s1.charAt(i) == s3.charAt(i+j)) && interLeaving(s1,s2,s3,i+1,j));
            boolean check_s2 = ((j<s2.length()) && (s2.charAt(j) == s3.charAt(i+j))&& interLeaving(s1,s2,s3,i,j+1));

            return check_s1 || check_s2;
        }
    }

    private static boolean interLeavingMemoization(String s1,String s2,String s3, int i, int j, Map<String,Boolean> lookUp) {
        String key = i+ "_key_"+j;

        if(!lookUp.containsKey(key)){
            if((s1.length()+s2.length()) != s3.length()){
                return false;
            }else if((i == s1.length()) && (j==s2.length())){
                return true;
            }else{
                boolean check_s1 = ((i<s1.length()) && (s1.charAt(i) == s3.charAt(i+j)) && interLeaving(s1,s2,s3,i+1,j));
                boolean check_s2 = ((j<s2.length()) && (s2.charAt(j) == s3.charAt(i+j))&& interLeaving(s1,s2,s3,i,j+1));

                boolean flag = check_s1 || check_s2;

                lookUp.put(key,flag);
            }
        }

        return lookUp.get(key);
    }

    private static boolean interLeavingTabulation(String s1,String s2,String s3) {
        boolean dp[][] = new boolean[s1.length()+1][s2.length()+1];

        dp[0][0] = true;

        //first row
        for(int j=1;j<=s2.length();j++){
            if((s3.charAt(j-1) == s2.charAt(j-1)) && dp[0][j-1]){
                dp[0][j] = true;
            }
        }

        //first column
        for(int i=1;i<=s1.length();i++){
            if((s1.charAt(i-1) == s1.charAt(i-1)) && dp[i-1][0]){
                dp[i][0] = true;
            }
        }

        for(int i=1;i<s1.length();i++){

            for(int j=1;j<s2.length();j++){

                boolean check_s1 = (s1.charAt(i-1) == s3.charAt(i+j-1)) && dp[i-1][j];

                boolean check_s2 = (s2.charAt(j-1) == s3.charAt(i+j-1)) && dp[i][j-1];

                dp[i][j] = check_s1 || check_s2;
            }
        }


        return dp[s1.length()-1][s2.length()-1];

    }
}
