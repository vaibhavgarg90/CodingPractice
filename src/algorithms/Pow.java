package algorithms;

public class Pow {

    private static int pow(int x, int n, int d) {
        if (n == 0) {
            return 1 % d;
        }

        if (n == 1) {
            long num = x;
            num = num % d;
            if (num < 0) {
                num += d;
            }

            return (int) num;
        }

        long result = 1;
        long prod = x % d;
        long longd = (long) d;

        while (n != 0) {
            if ((n % 2) == 1)
                result = (result * prod) % longd;
            n /= 2;
            prod = (prod * prod) % longd;
        }

        return (int) ((result + d) % d);
    }

    public static void main(String[] args) {
        System.out.println(pow(71045970, 41535484, 58170284));
    }
}
