package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 Minimum cost for tickets (problem).
 We are about to travel for a period of n days, during which
 We will use the train in some days that you can find in a given set train_days.

 And to use the train, we have to possess either a 1-day pass, a 7-days pass, or a 30-days pass,
 each one of them has a price that you can find in a given array prices,
 where prices[0] represents the price of a 1-day pass, prices[1] represents the price of a 7-days pass,
 and prices[2] represents the price of a 30-days pass.

 You are asked to find the minimum amount of money that we need to use the train during all the train days.
 Example:
 input:
 train_days = [1, 3, 8, 9, 22, 23, 28, 31]
 costs = [4, 10, 25]
 n = 32
 output: 28
 explanation: The cheapest way is by buying

   - 1-day pass on day 1, we use it on day 1
   - 7-days pass on day 3, we use it during days 3, 8, and 9
   - 7-days pass on day 22, we use it during days 22, 23, and 28
   - 1-day pass on day 31, we use it on day 31

 costs[0]+costs[1]+costs[1]+costs[0] = 4+10+10+4 = 28

 */
public class MinimumCostOfTicket {

    public static void main(String[] args){
        int[] trainDays = {1, 3, 8, 9, 22, 23, 28, 31};
        int[] costs = {4, 10, 25};

        int n = 32;

        System.out.println("1.Minimum Cost Of Ticket is "+costOfTicket(trainDays, costs,  n,  0));
        System.out.println("2.Minimum Cost Of Ticket Memoization is "+costOfTicketMemoization(trainDays, costs,  n,  0, new HashMap<>()));
        System.out.println("3.Minimum Cost Of Ticket is "+costOfTicketTabulation(trainDays, costs,  n));
    }

    private static int costOfTicket(int[] trainDays, int costs[], int n, int day){
        if(day>=n){
            return 0;
        }else if(!isTrainDays(trainDays,day)){
            return costOfTicket(trainDays,costs,n,day+1);
        }else{
            return min(costs[0]+costOfTicket(trainDays,costs,n,day+1),
                       costs[1]+costOfTicket(trainDays,costs,n,day+7),
                       costs[2]+costOfTicket(trainDays,costs,n,day+30)
                      );
        }
    }

    private static int costOfTicketMemoization(int[] trainDays, int costs[], int n, int day, Map<Integer,Integer> lookUp){

        if(!lookUp.containsKey(day)){
            if(day>=n){
                return 0;
            }else if(!isTrainDays(trainDays,day)){
                int val = costOfTicketMemoization(trainDays,costs,n,day+1,lookUp);
                lookUp.put(day,val);
            }else{
                int val = min(costs[0]+costOfTicketMemoization(trainDays,costs,n,day+1,lookUp),
                        costs[1]+costOfTicketMemoization(trainDays,costs,n,day+7,lookUp),
                        costs[2]+costOfTicketMemoization(trainDays,costs,n,day+30,lookUp));
                lookUp.put(day,val);
            }
        }

       return lookUp.get(day);
    }

    private static int costOfTicketTabulation(int[] trainDays, int costs[], int n){
        int[] dp = new int[n];

        for(int i=0;i<n;i++){
            if(!isTrainDays(trainDays,i)){
                if(i-1 >= 0){
                    dp[i] = dp[i-1];
                }else{
                    dp[i] = 0;
                }
            }else{

                int day_cost1 =0;
                if(i-1 >= 0){
                    day_cost1=dp[i-1];
                }

                int week_cost1 =0;
                if(i-7 >= 0){
                    week_cost1=dp[i-7];
                }

                int month_cost1 =0;
                if(i-30 >= 0){
                    month_cost1=dp[i-30];
                }
                int day_cost   = costs[0]+day_cost1;
                int week_cost  = costs[1]+week_cost1;
                int month_cost = costs[2]+month_cost1;
                dp[i] = min(day_cost,week_cost,month_cost);
            }
        }

       return dp[n-1];
    }

    private static boolean isTrainDays(int[] trainDays, int day){
        for(int trainDay:trainDays){
            if(trainDay == day){
                return true;
            }
        }
        return false;
    }

    private static int min(int a, int b, int c){
        int d= Math.min(a,b);
        return Math.min(d,c);
    }
}
