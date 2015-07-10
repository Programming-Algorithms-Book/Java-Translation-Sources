public class Program {
    private static final int MAX_N = 30;
    private static final int MAX_CAPACITY = 1000;
    private static final int TOTAL_CAPACITY = 15;
    private static final int N = 6;

    /* Таблица - целева функция */
    private static int[][] F = new int[MAX_N][MAX_CAPACITY];

    /* Тегло на предметите */
    private static int[] Weights = new int[] { 0, 1, 2, 3, 5, 6, 7 };

    /* Цена на предметите */
    private static int[] Values = new int[] { 0, 1, 10, 19, 22, 25, 30 };

    public static void main(String[] args) {
        System.out.printf("Брой предмети: %d\n", N);
        System.out.printf("Максимално допустимо общо тегло: %d\n",
                TOTAL_CAPACITY);
        calculate();
        System.out.printf("Таблица f[i, j]: ");
        printTable();
        System.out.printf("Максимална постигната стойност: %d\n",
                F[N][TOTAL_CAPACITY]);
        System.out.printf("Вземете предметите с номера: ");
        printSet();
    }

    /* Пресмята стойностите на целевата функция */
    private static void calculate() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= TOTAL_CAPACITY; j++) {
                if (j >= Weights[i]
                        && F[i - 1][j] < F[i - 1][j - Weights[i]] + Values[i]) {
                    F[i][j] = F[i - 1][j - Weights[i]] + Values[i];
                } else {
                    F[i][j] = F[i - 1][j];
                }
            }
        }
    }

    /* Извежда съдържанието на таблицата F[i][j] */
    private static void printTable() {
        for (int i = 1; i <= N; i++) {
            System.out.println();
            for (int j = 0; j <= TOTAL_CAPACITY; j++) {
                System.out.printf("%1$4d", F[i][j]);
            }
        }

        System.out.println();
    }

    private static void printSet() {
        int i = N, j = TOTAL_CAPACITY;
        while (j != 0) {
            if (F[i][j] == F[i - 1][j]) {
                i--;
            } else {
                System.out.printf("%d ", i);
                j -= Weights[i];
                i--;
            }
        }

        System.out.println();
    }
}
