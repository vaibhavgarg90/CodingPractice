package moengage;

public class NumBits {

    static int getCount(int num) {
        int mask = 1;
        int count = 0;

        while (mask > 0) {
            if ((num & mask) >= 1) {
                count++;
            }

            mask = mask << 1;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(getCount(-48));
    }

}
