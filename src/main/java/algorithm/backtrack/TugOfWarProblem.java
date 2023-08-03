package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TugOfWarProblem {

    private static int minimumDiff = Integer.MAX_VALUE;

    public static void main (String[] args){

        int data[] = {23, 45, -34, 12, 0, 98,
                -99, 4, 189, -1, 4};
        List<List<Integer>> subSetList = solveTugOfWar(data);
        printSubSet(subSetList);
    }

    private static void printSubSet(List<List<Integer>> subSetList) {
        subSetList.stream().forEach(System.out::println);
    }

    private static List<List<Integer>>  solveTugOfWar(int[] data) {

        List<List<Integer>> resultList = new ArrayList<>(2);
        int sum = Arrays.stream(data).sum();
        int no_selected = 0,curr = 0,current_sum=0;
        boolean solution[] = new boolean[data.length];
        boolean curr_sol[] = new boolean[data.length];
        
        solve(data,sum,no_selected,curr,current_sum,solution,curr_sol);

        //find the applicable set
        List<Integer> set1 = new ArrayList<>();
        List<Integer> set2 = new ArrayList<>();
        for(int i=0;i<solution.length;i++){
            if(solution[i]){
                set1.add(data[i]);
            }else{
                set2.add(data[i]);
            }
        }
        resultList.add(set1);
        resultList.add(set2);
        return resultList;
    }

    private static void solve(int[] data, int sum, int no_selected,
                              int curr, int current_sum,boolean solution[],boolean curr_sol[]) {
        if(curr == data.length){
            return;
        }

        solve(data,sum,no_selected,curr+1,current_sum,solution,curr_sol);

        current_sum+=data[curr];
        no_selected++;
        curr_sol[curr]= true;
        if(no_selected == data.length/2){
            if(Math.abs(sum-2*current_sum) < minimumDiff){
                minimumDiff = Math.abs(sum-2*current_sum);
                for(int i=0;i<data.length;i++){
                    solution[i]=curr_sol[i];
                }
            }
        }else{
            solve(data,sum,no_selected,curr+1,current_sum,solution,curr_sol);
        }

        curr_sol[curr]= false;

    }
}
