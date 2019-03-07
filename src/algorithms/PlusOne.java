package algorithms;

import java.util.ArrayList;

/**
 * Add 1 to the number with each digit specified in the list in reversed order.
 */
public class PlusOne {

    private static ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        int startIndex = -1;
        int i;

        for (i = 0; i < A.size(); i++) {
            if (A.get(i) != 0) {
                startIndex = i;
                break;
            }
        }

        if (i == A.size()) {
            ArrayList<Integer> toReturn = new ArrayList<>();
            toReturn.add(1);
            return toReturn;
        }

        A = new ArrayList<>(A.subList(startIndex, A.size()));

        boolean carry = false;

        for (i = A.size() - 1; i >= 0; i--) {
            int num = A.get(i);

            if (num == 9) {
                A.set(i, 0);
                carry = true;
            } else {
                A.set(i, num + 1);
                carry = false;
                break;
            }
        }

        if (carry) {
            A.add(0, 1);
        }

        return A;
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        A.add(0);
        A.add(0);
        System.out.println(plusOne(A));
    }

}
