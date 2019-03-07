package algorithms;

/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the
 * first non-whitespace character is found. Then, starting from this character, takes
 * an optional initial plus or minus sign followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace
 * characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the
 * 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range
 * of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: "42"
 * Output: 42
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * <p>
 * <p>
 * Example 4:
 * <p>
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * <p>
 * <p>
 * Example 5:
 * <p>
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Therefore INT_MIN (−231) is returned.
 */
public class Atoi {

    private static int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int[] arr = new int[str.length()];
        int len = 0;
        boolean neg = false;
        boolean pos = false;
        boolean found = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (!found && (c != ' ') && (c != '-') && (c != '+') && (c < '0' || c > '9')) {
                break;
            }

            if (c == '-') {
                if (found || neg || pos) {
                    break;
                }
                neg = true;
            } else if (c == '+') {
                if (found || neg || pos) {
                    break;
                }
                pos = true;
            } else if (c >= '0' && c <= '9') {
                arr[len++] = c - '0';
                found = true;
            } else if (found || neg || pos) {
                break;
            }
        }

        if (len == 0) {
            return 0;
        }

        int num = 0;
        for (int i = 0; i < len; i++) {
            if (((num * 10) / 10) != num) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            num *= 10;

            if ((num + arr[i]) < 0) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            num += arr[i];
        }

        return neg ? (-1 * num) : num;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("   -124567U890"));
    }
}
