package algorithm.dp;
/**
House robber (problem)
============================
Given an array of integers arr where arr[i] represents the amount of money in the house i,
AAAayou are asked to find the maximum amount of money that a robber can steal knowing that
he can't steal two adjacent houses because the security systems would automatically call the police

Example:
input: arr = [2, 10, 3, 6, 8, 1, 7]
output: 25
explanation: The greatest amount of money that a robber can get is 25, by the stealing
the house 1, 4, and 6 (arr[1]+arr[4]+arr[6] = 10+8+7 = 25)
**/

public class HouseRobberProblem {

    public static void main(String[] args) {
        int [] arr = {2, 10, 3, 6, 8, 1, 7};
        System.out.println("1.rob==>"+rob(arr,0, new int[arr.length]));

        System.out.println("2.rob==>"+robV2(arr));
    }

    private static int rob(int[] arr, int i,int[] lookUp) {

        if(i >= arr.length) {
            return 0;
        }

        if(lookUp[i] != 0){
            return lookUp[i];
        }else{
            lookUp[i] = Math.max(arr[i]+rob(arr,i+2,lookUp), rob(arr,i+1,lookUp));
            return lookUp[i];
        }

    }

    private static int robV2(int[] arr) {

        int[] dp = new int [arr.length];
        dp[0] = arr[0];
        dp[1] = arr[1];

        for(int i=2;i< arr.length;i++){
            dp[i] = Math.max(dp[i-1],arr[i]+dp[i-2]);
        }

        return dp[arr.length-1];
    }
}
