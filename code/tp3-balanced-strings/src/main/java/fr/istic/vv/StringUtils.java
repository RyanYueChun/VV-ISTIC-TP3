package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    /**
     * this function check if a string is balaneced based on
     * thoses caracteres ({,(, ]}
     * I use a specific treatment with java stack to verify this condition
     * @param str string variable
     * @return
     */
    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            // si un symbole ouvert, on le push dans le stack
            switch (character) {
                case '(':
                case '{':
                case '[':
                    stack.push(character);
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }

}
