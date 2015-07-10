public class Program {
    // private static final int MAX_COINS = 100;
    private static final int MAX_SUM = 100;
    private static final int SUM = 6; /* Сума, която искаме да получим */
    private static final int N = 5; /* Общ брой налични монети */

    /* Целева функция */
    private static int[][] Values = new int[MAX_SUM][MAX_SUM];

    /* Съществува ли монета с такава стойност */
    private static boolean[] Exist = new boolean[MAX_SUM];

    /* Налични типове монети */
    private static int[] Coins = { 1, 2, 3, 4, 6 };

    public static void main(String[] args) {
        init();
        System.out.printf(
                "Броят на представянията на %d с наличните монети е %d\n", SUM,
                count(SUM, SUM));
    }

    /* Инициализираща функция */
    private static void init() {
        /* Друго представяне на стойностите на монетите за по-бърз достъп */
        for (int i = 0; i < N; i++) {
            Exist[Coins[i]] = true;
        }
    }

    /* Намира броя на представянията на sum */
    private static int count(int sum, int max) {
        if (sum <= 0) {
            return 0;
        }

        if (Values[sum][max] > 0) {
            return Values[sum][max];
        } else {
            if (sum < max) {
                max = sum;
            }

            /* Съществува монета с такава стойност */
            if (sum == max && Exist[sum]) {
                Values[sum][max] = 1;
            }

            /* Пресмятаме всички */
            for (int i = max; i > 0; i--) {
                if (Exist[i]) {
                    Values[sum][max] += count(sum - i, i);
                }
            }
        }

        return Values[sum][max];
    }
}
