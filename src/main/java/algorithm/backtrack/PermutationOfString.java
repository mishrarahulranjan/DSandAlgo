package algorithm.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PermutationOfString {

    public static void main(String[] args){
        String str = "ABCD";
        System.out.println(permute(str));
    }

    private static List<String> permute(String str) {
        List<String> dataList = new ArrayList<>();

        Queue<String> dataQueue = new LinkedList<>();
        dataQueue.add(0+"");
        dataQueue.add(str);
        int index= 0;
        boolean addIndexFlag = false;

        while(!dataQueue.isEmpty()){
            String val = dataQueue.remove();
            if(isIndex(val)){
                index = Integer.parseInt(val);
                addIndexFlag = true;
           }else{
                if(index == val.length()){
                    dataList.add(val);
                    for(String valStr: dataQueue){
                        dataList.add(valStr);
                    }
                    break;
                }
                for (int i=index; i<val.length(); i++){
                    if(addIndexFlag){
                        dataQueue.add(index+1+"");
                    }

                    dataQueue.add(swap(val,index,i));
                    addIndexFlag = false;
                }
            }
        }
        return dataList;
    }

    private static boolean isIndex(String val) {
       return val.matches("-?\\d+(\\.\\d+)?");
    }

    private static String swap(String val, int index, int i) {

        StringBuilder tempBuilder = new StringBuilder(val);
        char c1 = val.charAt(index);
        char c2 = val.charAt(i);
        tempBuilder.setCharAt(index, c2);
        tempBuilder.setCharAt(i, c1);

        return tempBuilder.toString();
    }

}
