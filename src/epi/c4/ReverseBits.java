package epi.c4;

public class ReverseBits {

  private static int swapBits (final int n, short i, short j) {
    if ((i < 0) || (j < 0) || (i == j)) return n;
    if (((n >> i) & 0x1) == ((n >> j) & 0x1)) return n;
    int mask = (1 << i) | (1 << j);
    return n ^ mask;
  }

  private static int reverseBits (final int n) {
    int reversed = n;
    for (int i = 0; i < 16; i++) {
      reversed = swapBits(reversed, (short) i, (short) (31 - i));
    }
    return reversed;
  }

  public static void main (String[] args) {
    System.out.println(reverseBits(32));
    System.out.println(1 << 26);
  }
}
