public class Program {
    // private static final int MAX_COINS = 100;
    private static final int MAX_SUM = 100;
    private static final int N = 5; /* Общ брой налични монети */
    private static final int SUM = 6; /* Сума, която искаме да получим */

    /* Целева функция */
    private static int[][] Values = new int[MAX_SUM][MAX_SUM];

    /* Налични типове монети */
    private static int[] Coins = new int[] { 1, 2, 2, 3, 3, 4, 6 };

    public static void main(String[] args) {
        sort();
        System.out.printf(
                "Броят на представянията на %d с наличните монети е %d.\n",
                SUM, count(SUM, N - 1));
    }

    private static void sort() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Coins[i] > Coins[j]) {
                    int oldValue = Coins[i];
                    Coins[i] = Coins[j];
                    Coins[j] = oldValue;
                }
            }
        }
    }

    /* Намира броя на представянията на sum при използване на първите k монети */
    private static int count(int sum, int k) {
        int j;
        if (sum <= 0 || k < 0) {
            return 0;
        }

        if (Values[sum][k] > 0) {
            return Values[sum][k];
        } else {
            if (Coins[k] == sum) {
                Values[sum][k] = 1;
            }

            Values[sum][k] += count(sum - Coins[k], k - 1);
            j = k;
            while (j >= 0 && Coins[j] == Coins[k]) {
                j--;
            }

            Values[sum][k] += count(sum, j);
        }

        return Values[sum][k];
    }
}
