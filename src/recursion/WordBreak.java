package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a valid sentence without any spaces between the words and a dictionary
 * of valid English words, find all possible ways to break the sentence in
 * individual dictionary words.
 * <p>
 * Example
 * <p>
 * Consider the following dictionary
 * { i, like, sam, sung, samsung, mobile, ice,
 * cream, icecream, man, go, mango}
 * <p>
 * Input: "ilikesamsungmobile"
 * Output: i like sam sung mobile
 * i like samsung mobile
 * <p>
 * Input: "ilikeicecreamandmango"
 * Output: i like ice cream and man go
 * i like ice cream and mango
 * i like icecream and man go
 * i like icecream and mango
 *
 * @author vaibhav
 */
public class WordBreak {

    private static List<List<String>> breakWordsFromIndex(final List<String> dictionary, final String line, final int index) {
        if (index == line.length()) {
            return null;
        }

        int max = line.length() - 1;
        List<List<String>> wordsList = null;

        for (int i = index; i <= max; i++) {
            String curWord = line.substring(index, i + 1);

            if (dictionary.contains(curWord)) {
                if (i == max) {
                    List<String> listWithSingleWord = new ArrayList<>();
                    listWithSingleWord.add(curWord);
                    wordsList = new ArrayList<>();
                    wordsList.add(listWithSingleWord);
                } else {
                    List<List<String>> wordsAfter = breakWordsFromIndex(dictionary, line, i + 1);
                    if (wordsAfter != null) {
                        for (List<String> curWordsAfter : wordsAfter) {
                            List<String> curWordsList = new ArrayList<>();
                            curWordsList.add(curWord);
                            curWordsList.addAll(curWordsAfter);

                            if (wordsList == null) {
                                wordsList = new ArrayList<>();
                            }

                            wordsList.add(curWordsList);
                        }
                    }
                }
            }
        }

        return wordsList;
    }

    private static List<List<String>> breakWords(final List<String> dictionary, final String line) {
        return breakWordsFromIndex(dictionary, line, 0);
    }

    public static void main(String[] args) {
        String[] dict = {
                "i", "don't", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango"
        };

        List<String> dictionary = Arrays.asList(dict);

        String line = "idon'tlikesamsungmobile";

        System.out.println(breakWords(dictionary, line));
    }
}
