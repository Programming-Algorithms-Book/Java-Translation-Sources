public class Program {
    private static final int K = 4; /* Брой групи */

    /* Редица (нулевият елемент не се ползва) */
    private static int[] S = { 0, 23, 15, 89, 170, 25, 1, 86, 80, 2, 27 };

    /* Брой елементи в редицата */
    private static int N = S.length - 1;

    /* Префиксни суми */
    private static int[] P = new int[N + 1];

    /* Целева функция */
    private static int[][] F = new int[N + 1][N + 1];

    /* За възстановяване на решението */
    private static int[][] B = new int[N + 1][N + 1];

    public static void main(String[] args) {
        System.out.printf("Максимална сума в някоя от групите: %d\n",
                doPartition(K));
        printPartition(N, K);
    }

    /* Извършва оптимално разделяне на k групи */
    private static long doPartition(int k) {
        P[0] = 0;
        /* Пресмятане на префиксните суми */
        for (int i = 1; i <= N; i++) {
            P[i] = P[i - 1] + S[i];
        }

        /* Установяване на граничните условия */
        for (int i = 1; i <= N; i++) {
            F[i][1] = P[i];
        }

        for (int j = 1; j <= k; j++) {
            F[1][j] = S[1];
        }

        /* Основен цикъл */
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= k; j++) {
                F[i][j] = Integer.MAX_VALUE;
                for (int l = 1; l <= i - 1; l++) {
                    int m = Math.max(F[l][j - 1], P[i] - P[l]);
                    if (m < F[i][j]) {
                        F[i][j] = m;
                        B[i][j] = l;
                    }
                }
            }
        }

        return F[N][k];
    }

    private static void print(int from, int to) {
        System.out.println();
        for (int i = from; i <= to; i++) {
            System.out.printf("%d ", S[i]);
        }
    }

    private static void printPartition(int n, int k) {
        if (k == 1) {
            print(1, n);
        } else {
            printPartition(B[n][k], k - 1);
            print(B[n][k] + 1, n);
        }
    }
}
