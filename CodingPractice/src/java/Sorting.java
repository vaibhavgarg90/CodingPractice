package java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Example usage of in-built sort functionality provided by {@link Collections} class.
 */
public class Sorting {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(4);
        a.add(2);
        a.add(1);
        a.add(5);
        a.add(3);

        Collections.sort(a, Comparator.naturalOrder());
        System.out.println(a);

        Collections.sort(a, Comparator.reverseOrder());
        System.out.println(a);
    }
}
