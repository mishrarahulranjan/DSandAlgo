package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 Word break (problem)
 Given a string s and a list of words, check if we can break s into words
 from the list (A same word can be used multiple times).

 Example:

 input:
 s = "catsandogsareanimals"
 words = ["cats", "dog", "sand", "and", "cat", "mals", "san", "dogs", "are", "animal", "ani", "og", "sar"]

 output: true

 explanation: s is also equal to "cat"+"san"+"dogs"+"are"+"ani"+"mals", and all of these parts are in words
 */
public class WordBreakProblem {
    public static void main(String[] args){
        String str      = "catsandogsareanimals";

        String[]  words = { "cats", "dog", "sand",
                            "and","cat", "mals",
                            "san", "dogs", "are",
                            "animal", "ani", "og","sar"};

        Map<String,Boolean> dataMap = new HashMap<>();

        for(String word:words){
            dataMap.put(word,Boolean.TRUE);
        }

        System.out.println("1. work-break-problem is "+isWorkBreakPossible(str,words,0));
        System.out.println("2. work-break-problem is "+isWorkBreakPossibleV2(str,dataMap,0));
        System.out.println("3. work-break-problem is "+isWorkBreakPossibleV3(str,dataMap,0, new HashMap<>()));
        System.out.println("4. work-break-problem is "+isWorkBreakPossibleTabulation(str,words));
    }

    private static boolean isWorkBreakPossible(String str, String[] words, int i) {
        if(i == str.length()) {
            return true;
        }else {
            for(String word:words) {
                if((i+word.length()) < str.length()){
                    if(word.equals(str.substring(i,i+word.length())) &&
                            isWorkBreakPossible(str,words,i+word.length())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private static boolean isWorkBreakPossibleV2(String str, Map<String,Boolean> dataMap, int i) {
        if(i == str.length()) {
            return true;
        }else {
            for(int j = i+1;j<str.length()+1;j++){
                String strVal = str.substring(i,j);
                if(dataMap.containsKey(strVal) && isWorkBreakPossibleV2(str,dataMap,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isWorkBreakPossibleV3(String str, Map<String,Boolean> dataMap, int i, Map<Integer,Boolean> lookUp) {

        if(!lookUp.containsKey(i)){
            if(i == str.length()) {
                return true;
            }else {
                for(int j = i+1;j<str.length()+1;j++){
                    String strVal = str.substring(i,j);
                    if(dataMap.containsKey(strVal) && isWorkBreakPossibleV2(str,dataMap,j)){
                        lookUp.put(i,Boolean.TRUE);
                        return lookUp.get(i);
                    }
                }
                lookUp.put(i,Boolean.FALSE);
            }
        }

        return lookUp.get(i);
    }

    private static boolean isWorkBreakPossibleTabulation(String str, String[] words) {
        int n = str.length();
        Map<String,Boolean> dataMap = new HashMap<>();

        for(String word:words){
            dataMap.put(word,Boolean.TRUE);
        }

        boolean[] dp = new boolean[n+1];

        dp[0]= true;

        for(int i=1;i<n+1;i++ ){
            for(int j=0;j<i;j++){
                String strVal = str.substring(j,i);
                if(dataMap.containsKey(strVal) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}

