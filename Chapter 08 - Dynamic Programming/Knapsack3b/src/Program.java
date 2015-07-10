public class Program {
    private static final int MAX_N = 30;
    private static final int MAX_M = 1000;
    private static final int M = 14;
    private static final int N = 9;

    private static int[][] F = new int[MAX_N][MAX_M];
    private static int[] Set = new int[MAX_N];

    /* Тегло на предметите */
    private static int[] Weights = new int[] { 0, 6, 3, 10, 2, 4, 8, 1, 13, 3 };

    /* Цена на предметите */
    private static int[] Values = new int[] { 0, 5, 3, 9, 1, 2, 7, 1, 12, 3 };

    public static void main(String[] args) {
        System.out.printf("Брой предмети: %d\n", N);
        System.out.printf("Максимално допустимо обща маса: %d\n", M);
        calculate();
        System.out.printf("Таблица F[i, j]: ");
        printTable();
        System.out.printf("Максимална постигната стойност: {0}\n", F[N][M]);
        System.out.println("Следват всевъзможните множества от решения:");
        printAllSolutions(N, M, 0);
    }

    private static void calculate() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (j >= Weights[i]
                        && F[i - 1][j] < F[i - 1][j - Weights[i]] + Values[i]) {
                    F[i][j] = F[i - 1][j - Weights[i]] + Values[i];
                } else {
                    F[i][j] = F[i - 1][j];
                }
            }
        }
    }

    /* Извежда съдържанието на таблицата F[i, j] */
    private static void printTable() {
        for (int i = 1; i <= N; i++) {
            System.out.println();
            for (int j = 0; j <= M; j++) {
                System.out.printf("%1$4d", F[i][j]);
            }
        }

        System.out.println();
    }

    private static void printAllSolutions(int i, int j, int k) {
        /* Извежда ВСИЧКИ възможни множества от предмети, за които */
        /* се постига максимална стойност на целевата функция */
        if (0 == j) {
            System.out.print("Вземете следните предмети: ");
            for (i = 0; i < k; i++) {
                System.out.printf("%d ", Set[i]);
            }

            System.out.println();
        } else {
            if (F[i][j] == F[i - 1][j]) {
                printAllSolutions(i - 1, j, k);
            }

            if (j >= Weights[i]
                    && F[i][j] == F[i - 1][j - Weights[i]] + Values[i]) {
                Set[k] = i;
                printAllSolutions(i - 1, j - Weights[i], k + 1);
            }
        }
    }
}
