package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubSetSumProblem {

    private static ArrayList<List<Integer>> gobalResult = new ArrayList<>();

    public static void main(String args[]){

        int set[] = {15, 22, 14, 26, 32, 9, 16, 8};
        int target = 53;

        ArrayList<List<Integer>> result = subSetSum(set,target);
        printResult(result);
    }

    private static void printResult(ArrayList<List<Integer>> result) {
        System.out.println(result);
    }

    private static ArrayList<List<Integer>> subSetSum(int[] set, int targetSum) {
        subSetSum(set,targetSum,0,0,new ArrayList<>());
        return gobalResult;
    }

    private static void subSetSum(int[] set, int targetSum,int sum, int startIndex,ArrayList<Integer> result ) {

        if(sum == targetSum){
            gobalResult.add(result.stream().collect(Collectors.toList()));
            result.remove(result.size()-1);

        }else{
            if(startIndex<set.length){
                if(sum+set[startIndex]<= targetSum){
                    int insertIndex = result.size();
                    for(int i=startIndex;i<set.length;i++){
                        if(sum+set[i] <= targetSum){
                            if(result.size()>insertIndex){
                                result.set(insertIndex,set[i]);
                            }else{
                                result.add(set[i]);
                            }
                            subSetSum(set,targetSum,sum+set[i],i+1,result);
                        }
                    }
                }else{
                    subSetSum(set,targetSum,sum,startIndex+1,result);
                }
            }else{
                result.remove(result.size()-1);
            }
        }

    }
}
