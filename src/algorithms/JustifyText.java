package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters
 * and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * <p>
 * Pad extra spaces ‘ ‘ when necessary so that each line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line do not divide evenly between words, the empty slots on the left
 * will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * <p>
 * Your program should return a list of strings, where each string represents a single line.
 * <p>
 * Example:
 * <pre>
 * words = ["This", "is", "an", "example", "of", "text", "justification."] and L = 16
 * </pre>
 * <p>
 * Return the formatted lines as:
 * <pre>
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * </pre>
 */
public class JustifyText {

    private static ArrayList<String> fullJustify(List<String> A, int B) {
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> cur = new ArrayList<>();
        int i = 0;
        int len = 0;

        while (i < A.size()) {
            String s = A.get(i);

            if (len + 1 + s.length() <= B) {
                if (!cur.isEmpty()) {
                    len++;
                }
                len += s.length();
                cur.add(s);
            } else {
                int spaces;
                int size = cur.size();

                if (B == len) {
                    spaces = size - 1;
                } else {
                    spaces = B - len + size - 1;
                }

                double parts = (double) size - 1;

                StringBuilder sb = new StringBuilder();
                sb.append(cur.get(0));

                if (cur.size() == 1) {
                    for (int k = 0; k < spaces; k++) {
                        sb.append(" ");
                    }
                } else {
                    for (int j = 1; j < size; j++) {
                        int space = (int) Math.ceil(spaces / parts);

                        for (int k = 0; k < space; k++) {
                            sb.append(" ");
                        }

                        spaces -= space;
                        parts--;
                        sb.append(cur.get(j));
                    }
                }

                text.add(sb.toString());

                cur = new ArrayList<>();
                cur.add(s);
                len = s.length();
            }

            i++;
        }

        len = 0;
        String s;

        StringBuilder sb = new StringBuilder();
        if (!cur.isEmpty()) {
            s = cur.get(0);
            sb.append(s);
            len = s.length();
        }

        for (int j = 1; j < cur.size(); j++) {
            s = cur.get(j);
            sb.append(" ").append(cur.get(j));
            len += 1 + s.length();
        }


        for (int k = 0; k < B - len; k++) {
            sb.append(" ");
        }

        text.add(sb.toString());

        return text;
    }

    public static void main(String[] args) {
        String[] s = new String[]{
                "am", "fasgoprn", "lvqsrjylg", "rzuslwan", "xlaui", "tnzegzuzn", "kuiwdc", "fofjkkkm", "ssqjig",
                "tcmejefj", "uixgzm", "lyuxeaxsg", "iqiyip", "msv", "uurcazjc", "earsrvrq", "qlq", "lxrtzkjpg",
                "jkxymjus", "mvornwza", "zty", "q", "nsecqphjy"};

        List<String> list = fullJustify(Arrays.asList(s), 14);

        System.out.println(list);
    }
}
