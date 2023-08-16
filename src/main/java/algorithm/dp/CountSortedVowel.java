package algorithm.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 Count sorted vowel strings (problem):
 Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u)
 and are lexicographically sorted. A string s is lexicographically sorted if each character comes before
 or same as the next one in the alphabet.

 Example 1:
    Input: n = 2
    Output: 15

 Explanation: sorted vowel strings of size 2 are: aa, ae, ai, ao, au, ea, ee, ei, eo, eu, ii, io, iu, oo, ou, uu

 Example 2:
    Input: n = 9
    Output: 715

 */
public class CountSortedVowel {

    private static char[] vowels = {'a','e','i','o','u'};

    public static void main(String[] args){
        int n = 9;
        System.out.println("1. Count sorted vowel is "+sortedVowel(n,'a'));
        System.out.println("2. Count sorted vowel is "+sortedVowelMemoization(n,'a',new HashMap<>()));
        System.out.println("3. Count sorted vowel is "+sortedVowelTabulation(n));
    }

    private static int sortedVowel(int n,char character){
        if (n==0){
            return 1;
        }else{
            int nb=0;
            for(char vowel:vowels){
                if(character <= vowel){
                    nb+=sortedVowel(n-1,vowel);
                }
            }
            return nb;
        }
    }

    private static int sortedVowelMemoization(int n,char character, Map<String,Integer> lookUp){
        String key = n+"_"+character;

        if(!lookUp.containsKey(key)){
            if (n==0){
                return 1;
            }else{
                int nb=0;
                for(char vowel:vowels){
                    if(character <= vowel){
                        nb+=sortedVowel(n-1,vowel);
                    }
                }
                lookUp.put(key,nb);
            }
        }

        return lookUp.get(key);
    }

    private static int sortedVowelTabulation(int n){
       int[][] dp = new int[n][5];
       for(int i=0;i<5;i++){
           dp[0][i] = 1;
       }

       for(int i=1;i<n;i++){
           dp[i][0] = dp[i-1][0]+dp[i-1][1]+dp[i-1][2]+dp[i-1][3]+dp[i-1][4];
           dp[i][1] = dp[i-1][1]+dp[i-1][2]+dp[i-1][3]+dp[i-1][4];
           dp[i][2] = dp[i-1][2]+dp[i-1][3]+dp[i-1][4];
           dp[i][3] = dp[i-1][3]+dp[i-1][4];
           dp[i][4] = dp[i-1][4];
       }
       return Arrays.stream(dp[n-1]).sum();
    }
}
