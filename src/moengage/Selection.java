package moengage;

class Selection {

    private static int[][] countBenefit;
    private static int[][] timeElapsed;

    private static int max(int a, int b) {
        return Math.max(a, b);
    }

    private static int min(int a, int b) {
        return Math.min(a, b);
    }

    private static boolean canSchedule(int timeElapsed, int timeRequired, int maxEndTime) {
        return (timeElapsed + timeRequired <= maxEndTime);
    }

    private static void print(int count, int maxBenefit) {
        System.out.print("\t");
        for (int i = 0; i <= maxBenefit; i++) {
            System.out.print(i + "\t");
        }

        System.out.println();

        for (int i = 0; i <= count; i++) {
            System.out.print(i + "\t");

            int[] benefits = countBenefit[i];

            for (int j = 0; j <= maxBenefit; j++) {
                System.out.print(benefits[j] + "\t");
            }

            System.out.println();
        }


        System.out.println();

        for (int i = 0; i <= count; i++) {
            System.out.print(i + "\t");

            int[] elapsed = timeElapsed[i];

            for (int j = 0; j <= maxBenefit; j++) {
                System.out.print(elapsed[j] + "\t");
            }

            System.out.println();
        }
    }

    private static int selection(int count, int maxBenefit, int maxProcessingTime, int[] maxEndTime, int[] benefit, int[] timeRequired) {
        countBenefit = new int[count + 1][maxBenefit + 1];
        timeElapsed = new int[count + 1][maxBenefit + 1];

        for (int n = 0; n <= count; n++) {
            for (int b = 0; b <= maxBenefit; b++) {
                if (n == 0 || b == 0) {
                    countBenefit[n][b] = 0;
                    timeElapsed[n][b] = 0;
                    continue;
                }

                if (b < benefit[n - 1]) {
                    countBenefit[n][b] = countBenefit[n - 1][b];
                    timeElapsed[n][b] = timeElapsed[n - 1][b];
                    continue;
                }

                int val1 = 0;
                int val2 = 0;

                // weight is included
                if (canSchedule(timeElapsed[n - 1][b - benefit[n - 1]], timeRequired[n - 1], min(maxEndTime[n - 1], maxProcessingTime))) {
                    val1 = benefit[n - 1] + countBenefit[n - 1][b - benefit[n - 1]];
                    countBenefit[n][b] = val1;
                    timeElapsed[n][b] = timeElapsed[n - 1][b - benefit[n - 1]] + timeRequired[n - 1];
                }

                // weight is excluded
                val2 = countBenefit[n - 1][b];
                if (val2 > val1) {
                    countBenefit[n][b] = val2;
                    timeElapsed[n][b] = timeElapsed[n - 1][b];
                }
            }
        }

        return countBenefit[count][maxBenefit];
    }

    public static void main(String[] args) {
        int[] maxEndTime = new int[]{1, 2, 4, 10, 15, 15};
        int[] benefit = new int[]{2, 3, 1, 10, 13, 7};
        int[] timeRequired = new int[]{1, 1, 2, 10, 10, 5};

        int count = maxEndTime.length;
        int maxBenefit = 0;
        int maxProcessingTime = 0;

        for (int i = 0; i < count; i++) {
            maxBenefit += benefit[i];
            maxProcessingTime = max(maxProcessingTime, maxEndTime[i]);
        }

        int maxComputedBenefit = selection(count, maxBenefit, maxProcessingTime, maxEndTime, benefit, timeRequired);
        print(count, maxBenefit);
        System.out.println();
        System.out.println(maxComputedBenefit);
    }
}