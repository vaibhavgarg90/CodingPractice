package algorithms;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], return all the occurrences of pat[] in txt[].
 */
public class KMP {

    private static void matchPattern(String txt, String pat) {
        int N = txt.length();
        int M = pat.length();
        int[] lps = computeLPS(pat);

        int i = 0;
        int j = 0;

        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == M) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if ((i < N) && (pat.charAt(j) != txt.charAt(i))) {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    private static int[] computeLPS(String pat) {
        int M = pat.length();
        int[] lps = new int[M];
        int len = 0;
        lps[0] = 0;
        int i = 1;

        while (i < M) {
            if (pat.charAt(len) == pat.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABAB";
        matchPattern(txt, pat);
    }
}
