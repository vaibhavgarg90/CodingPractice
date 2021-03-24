package epi.c4;

import java.util.HashMap;
import java.util.Map;


public class BitCount {

  private static Map<Integer, Integer> bitCache = new HashMap<>();

  public static int countSetBits(final int n) {
    int count = 0;
    int mask = 1;
    while (mask != 0) {
      if ((n & mask) > 0) {
        count++;
      }
      mask = mask << 1;
    }
    return count;
  }

  public static int countSetBitsOptimal(final int n) {
    int count = 0;
    int x = n;
    while (x != 0) {
      count++;
      x &= x - 1;
    }
    return count;
  }

  private static void initBitCache(final int bits) {
    if (bits <= 0) {
      return;
    }
    for (int i = 0; i < (int) Math.pow(2, bits); i++) {
      bitCache.put(i, countSetBitsOptimal(i));
    }
  }

  private static int countSetBitsCached(final int n, final int bits) {
    int mask = ((1 << 31) >> 31) >>> (32 - bits);
    return bitCache.get((n >>> (3 * bits)) & mask)
        + bitCache.get((n >>> (2 * bits)) & mask)
        + bitCache.get((n >>> bits) & mask)
        + bitCache.get(n & mask);
  }

  public static void main(String[] args) {
    int n = 15;
    int bits = 8;
    System.out.println(countSetBits(n));
    System.out.println(countSetBitsOptimal(n));
    initBitCache(bits);
    System.out.println(countSetBitsCached(n, bits));
  }
}
