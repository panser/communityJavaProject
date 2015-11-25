package ua.org.gostroy.javacore;

import java.util.*;

/**
 * Created by s.panov on 11/25/2015.
 */
public class ValidBracketsSequence {

    public static void main(String[] args) {

        String str = "[a(bcd)()]";
        System.out.println(isValid(str));
    }

    public static boolean isValid(String s) {

        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack();
        char inStack;

        Set<Character> openBrackets = new HashSet<>(Arrays.asList('(','[','{'));
        Set<Character> closeBrackets = new HashSet<>(Arrays.asList(')',']','}'));
        Map<Character,Character> openCloseBrackets = new HashMap<Character,Character>(){{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};

        for (char c: str) {
            if (openBrackets.contains(c)) {
                stack.push(c);
            } else {
                if (closeBrackets.contains(c)) {
                    if(stack.size() == 0){
                        return false;
                    }
                    inStack = stack.pop();
                    if(openCloseBrackets.get(inStack) == c){
                        continue;
                    }else{
                        return false;
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }
}
