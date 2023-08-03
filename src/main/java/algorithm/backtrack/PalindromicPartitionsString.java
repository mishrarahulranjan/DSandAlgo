package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PalindromicPartitionsString {

    private static List<List<String>> resultList = new ArrayList<>();

    public static void main(String[] args){

        String str = "geeks";

        List<String> partitions = new ArrayList<>();

        palindromicPartition(str,0,partitions);

        System.out.println(resultList);

    }

    private static void palindromicPartition(String str, int index,List<String> partition) {
        String strVal="";

        List<String> temp = new ArrayList<>(partition);
        for(int i=index;i<str.length();i++){
            strVal+=str.charAt(i);
            if(checkPalindrome(strVal)){
                partition.add(strVal);
                if (i < str.length()-1){
                    palindromicPartition(str,i+1,partition);
                }else{
                    resultList.add(partition.stream().collect(Collectors.toList()));
                }
            }

            partition=temp;
        }

    }

    private static boolean checkPalindrome(String strVal) {
        for(int i=0,n=strVal.length();i<= n/2;i++){
            if(strVal.charAt(i) != strVal.charAt(n-1-i)){
                return false;
            }
        }
        return true;
    }
}
