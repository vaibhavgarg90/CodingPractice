package algorithms;

import java.util.ArrayList;

/**
 * Given numRows, generate the first numRows of Pascal’s triangle.
 * <p>
 * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1.
 * <p>
 * Example:
 * <p>
 * Given numRows = 5,
 * <p>
 * Return:
 * <pre>
 * [
 *      [1],
 *      [1,1],
 *      [1,2,1],
 *      [1,3,3,1],
 *      [1,4,6,4,1]
 * ]
 * </pre>
 */
public class PascalTriangle {

    private static ArrayList<ArrayList<Integer>> solve(int A) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();

        if (A == 0) {
            return lists;
        }

        if (A == 1) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            lists.add(list);
            return lists;
        }

        if (A == 2) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            lists.add(list);

            list = new ArrayList<>();
            list.add(1);
            list.add(1);
            lists.add(list);
            return lists;
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        lists.add(list);

        list = new ArrayList<>();
        list.add(1);
        list.add(1);
        lists.add(list);

        ArrayList<Integer> prev = list;

        while (A > 2) {
            list = new ArrayList<>();
            list.add(1);

            int j = 1;
            int n = prev.size();

            while (j < n) {
                int num = prev.get(j - 1) + prev.get(j);
                list.add(num);
                j++;
            }

            list.add(1);
            lists.add(list);
            prev = list;

            A--;
        }

        return lists;
    }

    public static void main(String[] args) {
        System.out.println(solve(0));
        System.out.println(solve(1));
        System.out.println(solve(2));
        System.out.println(solve(3));
        System.out.println(solve(4));
        System.out.println(solve(5));

        System.out.println("Hello".compareTo("Hello"));
        System.out.println("hello".compareTo("hellO"));
        System.out.println("hello".compareTo("Hello"));
    }
}
