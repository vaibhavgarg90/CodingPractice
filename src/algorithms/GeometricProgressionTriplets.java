package algorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an array and you need to find number of triplets of indices (i, j, k) such that the elements
 * at those indices are in geometric progression for a given common ratio and i < j < k.
 * <p>
 * Example 0:
 * Input:
 * arr = [1, 4, 16, 64] and r = 4
 * Output:
 * 2
 * Explanation:
 * [1,4, 16] and [4, 16, 64] at indices (0, 1, 2) and (1, 2, 3).
 */
public class GeometricProgressionTriplets {

    static boolean willOverflow(long a, long b) {
        return (a > (Long.MAX_VALUE / b));
    }

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Integer> cache = new HashMap<>();

        for (Long num : arr) {
            if (cache.containsKey(num)) {
                cache.put(num, cache.get(num).intValue() + 1);
            } else {
                cache.put(num, 1);
            }
        }

        long total = 0;
        int c1, c2, c3;
        long count;

        for (long a : arr) {
            if (!cache.containsKey(a)) {
                continue;
            }

            count = 0;
            c1 = cache.get(a);

            if (r == 1) {
                if (c1 >= 3) {
                    count = 1;

                    for (int i = 0; i < 3; i++) {
                        count *= c1;
                        c1--;
                    }

                    count /= 6;
                }
            } else if (!willOverflow(a, r) && !willOverflow(a * r, r)) {
                long b = a * r;
                long c = b * r;

                if (cache.containsKey(b) && cache.containsKey(c)) {
                    c2 = cache.get(b);
                    c3 = cache.get(c);

                    count = c1 * c2 * c3;
                }
            }

            total += count;
            cache.remove(a);
        }

        return total;
    }

    public static void main(String[] args) {
        File f1 = new File("/Users/vaibhav/Desktop/test1_2325652489.txt");
        File f2 = new File("/Users/vaibhav/Desktop/test2_1339347780085.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f1)))) {
            String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(nr[0]);

            long r = Long.parseLong(nr[1]);

            String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Long> arr = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                long arrItem = Long.parseLong(arrItems[i]);
                arr.add(arrItem);
            }

            System.out.println(countTriplets(arr, r));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
