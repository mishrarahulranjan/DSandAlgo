package algorithm.backtrack;

import java.util.*;

public class RemoveInvalidParentheses {

    public static void main(String[] args){

            String expression1 = "()v)";
            System.out.println(removeInvalidParenthesis(expression1));

            String expression2 = "()())()";
            System.out.println(removeInvalidParenthesis(expression2));
    }

    static boolean isParenthesis(char c){
            return ((c == '(') || (c == ')'));
    }

    private static  List<String>  removeInvalidParenthesis(String expression) {

            List<String> resultList = new ArrayList<>();

            if (expression.isEmpty()) return resultList;

            // visit set to ignore already visited string
            HashSet<String> visit = new HashSet<String>();

            // queue to maintain BFS
            Queue<String> q = new LinkedList<>();
            q.add(expression);
            visit.add(expression);
            boolean validExpLevel= false;

            while (!q.isEmpty()){
                expression = q.peek(); q.remove();
                if (isValidStringWithParenthesis(expression)){
                    resultList.add(expression);
                    validExpLevel= true;
                }else if(!validExpLevel){
                    // checking one level down
                    for (int i = 0; i < expression.length(); i++){
                        if (!isParenthesis(expression.charAt(i))) continue;
                        String temp = expression.substring(0, i) + expression.substring(i + 1);
                        if (!visit.contains(temp)) {
                            q.add(temp);
                            visit.add(temp);
                        }
                    }
                }
            }

            return resultList;
    }
    private static boolean isValidStringWithParenthesis(String expression){
            int no_paranthesis =0;
            for(int i=0;i<expression.length();i++){
                if(expression.charAt(i)== '('){
                    no_paranthesis--;
                }else if(expression.charAt(i)== ')'){
                    no_paranthesis++;
                }
                if(no_paranthesis>0){
                    return false;
                }
            }
            return no_paranthesis==0;
    }
}
