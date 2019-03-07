package leetcode;

public class RegularExpressionMatcher {

    private static Boolean[][] matrix;

    private static boolean isMatch(String s, String p) {
        matrix = new Boolean[s.length()][p.length()];
        Boolean matched = match(s, p, 0, 0);
        return (matched == null) ? false : matched.booleanValue();
    }

    private static Boolean match(String s, String p, int i, int j) {
        if (i == s.length()) {
            if (j == p.length()) {
                return true;
            }

            int k = j + 1;

            while (k < p.length()) {
                if (p.charAt(k) != '*') {
                    return false;
                }

                k += 2;
            }

            if (k == p.length()) {
                return (p.charAt(k - 1) == '*');
            } else {
                return true;
            }
        }

        if (j >= p.length()) {
            return false;
        }

        if (matrix[i][j] != null) {
            return matrix[i][j];
        }

        boolean first_match = ((i < s.length()) && ((p.charAt(j) == s.charAt(i)) || (p.charAt(j) == '.')));

        if (((j + 1) < p.length()) && (p.charAt(j + 1) == '*')) {
            matrix[i][j] = (match(s, p, i, j + 2) || (first_match && match(s, p, i + 1, j)));
        } else {
            matrix[i][j] = first_match && match(s, p, i + 1, j + 1);
        }

        return matrix[i][j];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("ab", ".*c"));
    }
}
