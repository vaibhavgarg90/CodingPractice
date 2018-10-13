package leetcode;

public class WildCardMatcher {

    private static Boolean[][] matrix;

    private static boolean isMatch(String s, String p) {
        if (s.isEmpty()) {
            return p.isEmpty();
        }

        if (p.isEmpty()) {
            return s.isEmpty();
        }

        StringBuilder pattern = new StringBuilder();
        pattern.append("" + p.charAt(0));

        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                if (p.charAt(i - 1) != '*') {
                    pattern.append("*");
                }
            } else {
                pattern.append("" + p.charAt(i));
            }
        }

        matrix = new Boolean[s.length()][pattern.length()];

        Boolean matched = match(s, pattern.toString(), 0, 0);

        return ((matched == null) ? false : matched.booleanValue());
    }

    private static Boolean match(String s, String p, int i, int j) {
        if (i == s.length()) {
            if (j == p.length()) {
                return true;
            }

            if (j == p.length() - 1 && p.charAt(j) == '*') {
                return true;
            }

            return false;
        }

        if (j == p.length()) {
            return false;
        }

        if (matrix[i][j] == null) {
            if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
                matrix[i][j] = match(s, p, i + 1, j + 1);
            } else if (p.charAt(j) == '*') {
                Boolean empty = match(s, p, i, j + 1);
                if (empty != null && empty.booleanValue()) {
                    matrix[i][j] = true;
                } else {
                    matrix[i][j] = match(s, p, i + 1, j);
                }
            } else {
                matrix[i][j] = false;
            }
        }

        return matrix[i][j];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "*a?a*"));
    }
}
