package algorithms;

/**
 * Checks whether the characters other than alphabets in the given string form a palindrome or not.
 */
public class Palindrome {

    private static int isPalindrome(String A) {
        int i = 0, j = A.length() - 1;
        char start, end;

        int pal = 1;

        while (i < j) {
            start = Character.toLowerCase(A.charAt(i));
            end = Character.toLowerCase(A.charAt(j));

            if (!((start >= 'a' && start <= 'z') || (start >= '0' && start <= '9'))) {
                i++;
                continue;
            }

            if (!((end >= 'a' && end <= 'z') || (end >= '0' && end <= '9'))) {
                j--;
                continue;
            }

            if (start != end) {
                pal = 0;
                break;
            }

            i++;
            j--;
        }

        return pal;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("123aaaaa231"));
    }
}
