package algorithm.dp;

/**
 * Longest increasing subsequence (problem)
 * Given an array of integers arr, find the length of its longest increasing subsequence,
 * its longest subsequence where each element is strictly greater than the previous one.
 * Example 1:
 * input:arr = [7, 5, 2, 4, 7, 2, 3, 6, 4, 5, 12, 1, 7]
 * output: 5
 * explanation: A possible longest increasing subsequence is [2, 3, 4, 5, 7], its length is 5
 *
 * Example 2:
 * input:
 * arr = [8, 5, 5, 3]
 * output: 1
 * explanation: The longest increasing subsequences that we can make are those that contain one element only,
 * ]like [8] Constraints: len(arr) >= 1
 */
public class LongestIncreasingSequence {

    public static void main(String[] args){

        int[] arr = {7, 5, 2, 4, 7, 2, 3, 6, 4, 5, 12, 1, 7};
        //int[] arr = {8, 5, 5, 3};

        System.out.println("Longest Increasing Sequence.. is "+lisRecursive(arr,0,Integer.MIN_VALUE));
    }

    private static int lis(int [] arr){
        int sequenceCount =0;
        for(int i=0;i< arr.length;i++){
            int localSequence=1;
            int k =i;
            for(int j=i+1;j< arr.length;j++){
                if(arr[k]<arr[j]){
                    localSequence++;
                    k=j;
                }
            }
            sequenceCount = Math.max(sequenceCount,localSequence);
        }
        return sequenceCount;
    }

    private static int lisRecursive(int [] arr, int i,int prev){
        if( i== arr.length){
            return 0;
        }else if (arr[i] <= prev){
            return lisRecursive(arr,i+1, prev);
        }else{
            return Math.max(lisRecursive(arr,i+1, prev),1+lisRecursive(arr,i+1, arr[i]));
        }
    }

    private static int lisMemoization(int [] arr){
        int sequenceCount =0;
        for(int i=0;i< arr.length;i++){
            int localSequence=1;
            int k =i;
            for(int j=i+1;j< arr.length;j++){
                if(arr[k]<arr[j]){
                    localSequence++;
                    k=j;
                }
            }
            sequenceCount = Math.max(sequenceCount,localSequence);
        }
        return sequenceCount;
    }

    private static int lisTabular(int [] arr){
        int sequenceCount =0;
        for(int i=0;i< arr.length;i++){
            int localSequence=1;
            int k =i;
            for(int j=i+1;j< arr.length;j++){
                if(arr[k]<arr[j]){
                    localSequence++;
                    k=j;
                }
            }
            sequenceCount = Math.max(sequenceCount,localSequence);
        }
        return sequenceCount;
    }
}
