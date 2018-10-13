package algorithms;

/**
 * Reverse bits of an 32 bit unsigned integer
 * <p>
 * Example 1:
 * <p>
 * x = 0,
 * <p>
 * 00000000000000000000000000000000
 * =>        00000000000000000000000000000000
 * return 0
 * <p>
 * Example 2:
 * <p>
 * x = 3,
 * <p>
 * 00000000000000000000000000000011
 * =>        11000000000000000000000000000000
 * return 3221225472
 * <p>
 * Since java does not have unsigned int, use long.
 *
 * @author vaibhav
 */
public class ReverseBits {

    private static long reverse(long x) {
        long reverse = 0;

        for (int i = 0; i < 32; i++) {
            reverse <<= 1;

            if ((x & (1 << i)) != 0) {
                reverse |= 1;
            }
        }

        return reverse;
    }

    public static void main(String[] args) {
        //System.out.println(reverse(4294967295L));
        System.out.println(reverse(3L));
    }
}
