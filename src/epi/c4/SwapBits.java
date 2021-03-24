package epi.c4;

public class SwapBits {

  private static int swapBits(final int n, short i, short j) {
    if ((i < 0) || (j < 0) || (i == j)) {
      return n;
    }
    if (((n >> i) & 0x1) == ((n >> j) & 0x1)) {
      return n;
    }
    int mask = (1 << i) | (1 << j);
    return n ^ mask;
  }

  public static void main(String[] args) {
    System.out.println(swapBits(32, (short) 5, (short) 4));
    System.out.println(swapBits(32, (short) 5, (short) 6));
    System.out.println(swapBits(31, (short) 4, (short) 5));
    System.out.println(swapBits(31, (short) 4, (short) 3));
  }
}
