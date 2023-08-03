package algorithm.recursion;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class PermutationOfStringRecursion {

    public static void main(String [] args){

        String strVal = "ABCD";

        List<String> resultCollection = new ArrayList<>();

        permute(strVal,0,resultCollection);

        out.println(resultCollection);
    }

    private static void permute(String strVal,int pivotIndex, List<String> resultCollection) {
        if(pivotIndex == strVal.length()-1){
            resultCollection.add(strVal);
        }else{
            for(int i=pivotIndex;i<strVal.length();i++){
                String swappedStr = swap(strVal,pivotIndex,i);
                permute(swappedStr,pivotIndex+1,resultCollection);
            }
        }
    }

    private static String swap(String strVal, int pivotIndex, int i) {
        StringBuilder sb = new StringBuilder(strVal);
        char tempChar= strVal.charAt(pivotIndex);
        sb.setCharAt(pivotIndex,strVal.charAt(i));
        sb.setCharAt(i,tempChar);
        return sb.toString();
    }
}
