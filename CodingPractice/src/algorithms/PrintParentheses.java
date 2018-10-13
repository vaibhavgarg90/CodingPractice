package algorithms;

/**
 * Given integer number n, print all valid parentheses.
 *
 * @author vaibhav
 */
public class PrintParentheses {

    static void _printParentheses(int n, int open, int close, StringBuilder curStr) {
        if (close == n) {
            System.out.println(curStr.toString());
            return;
        }

        StringBuilder prevStr = new StringBuilder(curStr);

        if (open > close) {
            curStr.append(")");
            _printParentheses(n, open, close + 1, curStr);
        }

        if (open < n) {
            prevStr.append("(");
            _printParentheses(n, open + 1, close, prevStr);
        }
    }

    static void printAllValid(int n) {
        _printParentheses(n, 0, 0, new StringBuilder());
    }

    public static void main(String[] args) {
        for (int n = 1; n < 5; n++) {
            printAllValid(n);
            System.out.println("\n");
        }
    }
}
