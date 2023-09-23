package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a rope of length n meters, cut the rope in different parts of integer lengths in a way
 * that maximizes product of lengths of all parts.
 * You must make at least one cut. Assume that the length of rope is more than 2 meters.
 * Examples:
 * Input: n = 2
 * Output: 1 (Maximum obtainable product is 1*1)
 *
 * Input: n = 3
 * Output: 2 (Maximum obtainable product is 1*2)
 */

public class MaximumRopeCutting {
   public static void main(String[] args){
        System.out.println("1.Maximum Product is "+ maxProdRecursion(10));
        System.out.println("2.Maximum Product is "+ maxProdMemoization(10, new HashMap<>()));
        System.out.println("3.Maximum Product is "+ maxProdTabulation(10));
    }

    static int maxProdRecursion(int n){
        if (n == 0 || n == 1) {
            return 0;
        }

        int max_val = 0;
        for (int i = 1; i < n; i++){
            max_val = Math.max(max_val, (Math.max(i * (n - i),maxProdRecursion(n - i) * i)));
        }
        return max_val;
    }

    static int maxProdMemoization(int n, Map<Integer,Integer> lookUp){
       if(!lookUp.containsKey(n)){
           if (n == 0 || n == 1) {
               return 0;
           }
           int max_val = 0;
           for (int i = 1; i < n; i++){
               max_val = Math.max(max_val,
                                 (Math.max(i * (n - i),maxProdMemoization(n - i,lookUp) * i))
                         );
           }
           lookUp.put(n,max_val) ;
       }
       return lookUp.get(n);
    }

    static int maxProdTabulation(int n){

        int[] val   = new int[n+1];
        val[0]      = val[1] = 0;

        for (int i = 1; i <= n; i++){
            int max_val = 0;
            for (int j = 1; j <= i; j++){
                max_val = Math.max(max_val,
                                   Math.max((i-j)*j, j*val[i-j])
                          );
            }
            val[i] = max_val;
        }
        return val[n];
    }
}
