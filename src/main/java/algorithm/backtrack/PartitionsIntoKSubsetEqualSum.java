package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartitionsIntoKSubsetEqualSum {

    public static void main(String[] args){
        int[] arr = {4, 3, 2, 3, 5, 2, 1};
        int N = arr.length;
        int K = 5;
        List<List<Integer>> result= isKPartitionPossible(arr, N, K);
        if (result.size()==K){
            System.out.println("Partitions into equal sum is possible.");
            System.out.println(result);
        }else{
            System.out.println("Partitions into equal sum is not possible.");
        }
    }

    static boolean isKPartitionPossibleRec(int[] arr, int[] subSetSum, boolean[] token,
                                           int partitionSum, int K, int N, int curIdx,List<List<Integer>> globalResult){
        if(curIdx == K ) return true;

        for(int i=0;i<N;i++){
            if(token[i]) continue;
            if((subSetSum[curIdx]+arr[i]) <=partitionSum){
                token[i]=true;
                subSetSum[curIdx]+=arr[i];
                globalResult.get(curIdx).add(arr[i]);
                boolean flag = false;
                if(subSetSum[curIdx] == partitionSum){
                     flag= isKPartitionPossibleRec(arr, subSetSum, token,partitionSum,
                            K, N,curIdx+1,globalResult);
                }else{
                     flag= isKPartitionPossibleRec(arr, subSetSum, token,partitionSum,
                            K, N,curIdx,globalResult);
                }


                if(flag) return true;
                subSetSum[curIdx]-=arr[i];
                token[i]=false;
                globalResult.get(curIdx).remove(globalResult.get(curIdx).size()-1);
            }
        }
        return false;
    }

    static List<List<Integer>>  isKPartitionPossible(int[] arr, int N, int K) {
        final List<List<Integer>> globalResult = new ArrayList<>(K);

        if (K == 1){
            globalResult.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
            return globalResult;
        }

        if (N < K) return globalResult;

        int sum = 0;

        for (int i = 0; i < N; i++){
            sum += arr[i];
        }

        if (sum % K != 0) return globalResult;

        int subset = sum / K;
        int []subsetSum = new int[K];
        boolean []token = new boolean[N];

        for(int k=0;k<K;k++){
            globalResult.add(new ArrayList<Integer>());
        }

       isKPartitionPossibleRec(arr, subsetSum, token,subset,
                K, N,0,globalResult);

        //removing the empty values
        for(int i=0;i<globalResult.size();i++){
            List<Integer> ls = globalResult.get(i);
            if(ls.isEmpty()){
                globalResult.remove(ls);
            }
        }

        return globalResult;
    }

}
