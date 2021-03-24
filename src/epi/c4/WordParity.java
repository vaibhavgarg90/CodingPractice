package epi.c4;

public class WordParity {

  private static short computeParity(final int n) {
    int x = n;
    x = x ^ (x >> 16);
    x = x ^ (x >> 8);
    x = x ^ (x >> 4);
    x = x ^ (x >> 2);
    x = x ^ (x >> 1);
    return (short) (x & 0x1);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.println(i + " :: " + computeParity(i));
    }
  }
}
