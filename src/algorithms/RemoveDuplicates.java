package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Remove duplicate elements from the sorted list.
 */
public class RemoveDuplicates {

    private static int removeDuplicates(ArrayList<Integer> a) {
        if (a == null || a.isEmpty()) {
            return 0;
        }

        if (a.size() == 1) {
            return 1;
        }

        int count = a.size();

        Integer prev = null, cur = null;

        Iterator<Integer> it = a.iterator();

        while (it.hasNext()) {
            cur = it.next();

            if (prev != null && prev.equals(cur)) {
                it.remove();
                count--;
            }

            prev = cur;
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(500);
        a.add(500);
        a.add(500);

        int total = removeDuplicates(a);

        System.out.println(a);
        System.out.println(total);
    }
}
