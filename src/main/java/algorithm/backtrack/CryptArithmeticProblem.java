package algorithm.backtrack;

import java.util.HashMap;
import java.util.Map;
/**
                 SEND
        +        MORE
              --------
                MONEY
              --------

**/

public class CryptArithmeticProblem {

    public static void main(String[] args){

        char[] inputExp1     =  {'S','E','N','D'};
        char[] inputExp2     =  {'M','O','R','E'};

        char[] outputExp     =  {'M','O','N','E','Y'};

        Map<Character,Integer> mapper = new HashMap<>();

        StringBuilder sb = new StringBuilder(64);
        sb.append(inputExp1);
        sb.append(inputExp2);
        sb.append(outputExp);
        for(char c: sb.toString().toCharArray()){
            mapper.put(c,-1);
        }
        Character[] lettersToAssign= mapper.keySet().toArray(new Character[0]);

        if(exhaustiveSolve(lettersToAssign,inputExp1,inputExp2,outputExp,mapper,0)){
            System.out.println("Solution exists"+ mapper);
        }else{
            System.out.println("No solution exists");
        }
    }

     private static boolean exhaustiveSolve(Character[] lettersToAssign,char[] inputExp1,char[] inputExp2,char[] outputExp ,  Map<Character,Integer> mapper ,int index){

        if (index == lettersToAssign.length) {
            return isPuzzledSolved(inputExp1,inputExp2,outputExp,mapper);
        }
        for (int digit = 0; digit <= 9; digit++)   {
            if (assignLetterToDigit(lettersToAssign[index], digit,mapper))
            {
                if (exhaustiveSolve(lettersToAssign,inputExp1,inputExp2,outputExp,mapper,index+1)) return true;
                unassignedLetterFromDigit(lettersToAssign[index],mapper);
            }
        }
        return false;
    }

    private static boolean assignLetterToDigit(char character, int digit,Map<Character,Integer> mapper) {
        if(mapper.containsValue(digit)){
            return false;
        }
        mapper.put(character,digit);
        return true;
    }

    private static void unassignedLetterFromDigit(char character, Map<Character,Integer> mapper) {
         mapper.remove(character);
    }

    private static boolean isPuzzledSolved( char[] inputExp1,char[] inputExp2,char[] outputExp,Map<Character,Integer> mapper ){

        int num1=getNumber(inputExp1,mapper);
        int num2=getNumber(inputExp2,mapper);
        int num3=getNumber(outputExp,mapper);
        return num3== (num1+num2);
    }

    private static int getNumber(char[] expression,Map<Character,Integer> mapper){
        int num=0;
        for(char c:expression){
            num= num*10+mapper.get(c);
        }
        return num;
    }
}
