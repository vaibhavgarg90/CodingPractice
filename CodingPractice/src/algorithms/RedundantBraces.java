package algorithms;

import java.util.Stack;

/**
 * Write a program to validate if the input string has redundant braces?
 * Return 0/1
 * <p>
 * 0 -> NO
 * 1 -> YES
 * Input will be always a valid expression
 * <p>
 * and operators allowed are only + , * , - , /
 * <p>
 * Example:
 * <p>
 * ((a+b)) has redundant braces so answer will be 1
 * (a+(a+b)) doesn't have have any redundant braces so answer will be 0
 *
 * @author vaibhav
 */
public class RedundantBraces {

    private static int braces(String A) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < A.length(); i++) {
            char cur = A.charAt(i);

            if (cur == ')') {
                if (stack.empty()) {
                    return 1;
                }

                boolean found = false;
                boolean numFound = false;
                boolean opFound = false;

                while (true) {
                    char prev = stack.pop();

                    if (prev == '(') {
                        found = true;
                        break;
                    } else if (prev == '+' || prev == '-' || prev == '*' || prev == '/') {
                        opFound = true;
                    } else {
                        numFound = true;
                    }
                }

                if (!found || !numFound || !opFound) {
                    return 1;
                }

                stack.push('c');
            } else {
                stack.push(cur);
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(braces("(a+(a+b))"));
    }
}
