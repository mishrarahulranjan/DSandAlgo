package algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakProblem {

    public static void main(String[] args){

        String str = "iproy";

        List <String> dict= Arrays.asList( "mobile","samsung","sam","sung",
                                           "man","mango", "icecream","and",
                                           "go","i","ilov","lov","eice",
                                           "ice","cream","iproy","proy");

        System.out.println(wordBreak(str.length(),dict,str));
    }


    static List<String> wordBreak(int n, List<String> dict, String s){
        List<String> result = new ArrayList<>();
        solve(n, s, dict, "",result);
        return result;
    }

    static void solve(int n, String s, List<String> dict, String ans,List<String> result ) {
        for(int i = 1; i <= n; i++){
            String prefix=s.substring(0, i);
            if(dict.contains(prefix)){
                if(i == n){
                    ans += prefix;
                    result.add(ans);
                    return;
                }
                solve(n - i, s.substring(i,n), dict, ans+prefix+" ",result);
            }
        }
    }
}

