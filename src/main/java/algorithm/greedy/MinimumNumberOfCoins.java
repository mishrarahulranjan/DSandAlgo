package algorithm.greedy;

import java.util.Vector;

public class MinimumNumberOfCoins {
    public static void main(String[] args){
        int[] denominations = {2, 5, 10, 20,50, 100, 500, 1000};
        int sum = 102;
        System.out.print("Following is minimal number " +"of change for " + sum + ": ");
        findMin(sum,denominations);
    }

    static void findMin(int sum,int[] denominations){
        Vector<Integer> ans = new Vector<>();
        for (int i = denominations.length-1; i >= 0; i--){
            while (sum >= denominations[i]){
                sum -= denominations[i];
                ans.add(denominations[i]);
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            System.out.print(" " + ans.elementAt(i));
        }
    }
}
