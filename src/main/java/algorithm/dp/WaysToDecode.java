package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 *Ways to decode (problem)
 * A string made of letters can be encoded by replacing each letter by its position in
 * the alphabet (e.g.: ELD -> 5124), but when decoding, a same encoded string can have multiple
 * ways to be decoded (e.g.: 5124 can be decoded as 5 1 2 4 (EABD), or 5 12 4 (ELD), or 5 1 24 (EAX)).
 *
 * Given an encoded string s made of numbers, find the number of possible ways to decode it by
 * following this decoding map:
 *
 * 1 -> A,   2 -> B,   3 -> C,   4 -> D,   5 -> E,   6 -> F,   7 -> G,   8 -> H,   9 -> I,
 * 10 -> J,   11 -> K,   12 -> L,   13 -> M,   14 -> N,   15 -> O,   16 -> P,   17 -> Q,   18 -> R,
 * 19 -> S,   20 -> T,   21 -> U,   22 -> V,   23 -> W,   24 -> X,   25 -> Y,   26 -> Z
 *
 * Example:
 * input:
 * s = "512810120129"
 *
 * output: 4
 * explanation: There are 4 possible ways to decode s:
 * 5   1   2   8   10   1   20   1   2   9
 * 5   1   2   8   10   1   20   12   9
 * 5   12   8   10   1   20   1   2   9
 * 5   12   8   10   1   20   12   9
 */

public class WaysToDecode {

    public static void main(String[] args){

        String input = "512810120129";

        System.out.println("1.number fo ways to decode is "+waysDecodeRecursive(input,0));
        System.out.println("2.number fo ways to decode is "+waysDecodeMemoization(input,0, new HashMap<>()));
        System.out.println("3.number fo ways to decode is "+waysDecodeTabular(input));
    }

    private static boolean isValid(String input, int i){
        String str= (input.charAt(i)+"")+(input.charAt(i+1)+"");
        int val = Integer.parseInt(str);
        return val >= 10 && val <=26;
    }

    private static int waysDecodeRecursive(String input, int i){
        if(i == input.length()){
            return 1;
        }else if(input.charAt(i)== '0'){
            return 0;
        }else if((i+1 < input.length()) && isValid(input,i)){
            return waysDecodeRecursive(input,i+1) + waysDecodeRecursive(input,i+2);
        }else{
            return waysDecodeRecursive(input,i+1);
        }
    }

    private static int waysDecodeMemoization(String input, int i, Map<Integer, Integer> lookUp){
        if(!lookUp.containsKey(i)){
            if(i == input.length()){
                return 1;
            }else if(input.charAt(i)== '0'){
                return 0;
            }else if((i+1 < input.length()) && isValid(input,i)){
                int val= waysDecodeMemoization(input,i+1,lookUp) + waysDecodeMemoization(input,i+2,lookUp);
                lookUp.put(i,val);
            }else{
                int val= waysDecodeMemoization(input,i+1,lookUp);
                lookUp.put(i,val);
            }
        }
        return lookUp.get(i);
    }

    private static int waysDecodeTabular(String input){

       int n = input.length();
       int [] dp = new int[input.length()];

       if(input.charAt(0) == '0') return 0;
       else if (n ==1) return 1;

       dp[0] = 1;

       if(input.charAt(1) != '0'){
           dp[1] = 1;
       }
       if(isValid(input,0)){
           dp[1] += 1;
       }

       for(int i =2; i < n;i++){
           if(input.charAt(i) != '0'){
               dp[i] += dp[i-1];
           }
           if(isValid(input,i-1)){
               dp[i] +=dp[i-2];
           }
       }
       return dp[n-1];
    }
}
