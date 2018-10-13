package algorithms;

/**
 * Calculate square root of given integer.
 */
public class Sqrt {

    private static int sqrt(int a) {
        if (a <= 0) {
            return 0;
        }

        int low = 1, high = 46340;
        int mid = 0, num = 0;

        while (low <= high) {
            if (low == high) {
                break;
            }

            mid = (low + high) >>> 1;
            num = mid * mid;

            if (num == a) {
                break;
            } else if (num > a) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        mid = (low + high) >>> 1;

        return (mid * mid > a) ? mid - 1 : mid;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(930675566));
    }
}
