package algorithm.greedy;

import java.util.Arrays;

public class FractionalKnapsack {

    static class ItemValue {
        int value;
        int weight;
        public ItemValue(int val, int wt) {
            this.weight = wt;
            this.value = val;
        }
    }

    public static void main(String[] args) {

        ItemValue[] arr = {
                                new ItemValue(60, 10),
                                new ItemValue(100, 20),
                                new ItemValue(120, 30)
                          };

        int capacity = 50;
        double maxValue = getMaxValue(arr, capacity);
        System.out.println("Maximum value we can obtain = "+ maxValue);
    }

    private static double getMaxValue(ItemValue[] arr,int capacity) {

        Arrays.sort(arr, (item1, item2) -> {
            double cpr1  = (double) item1.value / (double) item1.weight;
            double cpr2 = (double) item2.value / (double) item2.weight;
            return (int) (cpr2 -cpr1);
        });

        double totalValue = 0d;

        for (ItemValue i : arr) {
            int curWt  = (int)i.weight;
            int curVal = (int)i.value;

            if (capacity - curWt >= 0) {
                capacity    -= curWt;
                totalValue  += curVal;
            }else{
                double fraction = ((double)capacity / (double)curWt);
                totalValue  += (curVal * fraction);
                capacity    -= (curWt * fraction);
                break;
            }
        }
        return totalValue;
    }
}
