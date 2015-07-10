public class Program {
    private static final long NotSolved = Long.MAX_VALUE;

    /* Размерности на матриците */
    private static long[] R = new long[] { 12, 13, 35, 3, 34, 2, 21, 10, 21, 6 };

    /* Брой матрици */
    private static int N = R.length - 1;

    /* Таблица - целева функция */
    private static long[][] M = new long[N + 1][N + 1];

    public static void main(String[] args) {
        System.out.printf("Минималният брой умножения е: %d\n",
                solveMemoization());
        printMatrix();
    }

    private static long solveMemo(int i, int j) {
        /* Стойността вече е била пресметната */
        if (M[i][j] != NotSolved) {
            return M[i][j];
        }

        /* В този интервал няма матрица */
        if (i == j) {
            M[i][j] = 0;
        } else {
            /* Пресмятаме рекурсивно */
            for (int k = i; k <= j - 1; k++) {
                long q = solveMemo(i, k) + solveMemo(k + 1, j)
                        + (R[i - 1] * R[k] * R[j]);
                if (q < M[i][j]) {
                    M[i][j] = q;
                }
            }
        }

        return M[i][j];
    }

    private static long solveMemoization() {
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                M[i][j] = NotSolved;
            }
        }

        return solveMemo(1, N);
    }

    /* Извежда матрицата на минимумите на екрана */
    private static void printMatrix() {
        System.out.println("Матрица на минимумите:");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.printf("%1$8d", M[i][j]);
            }

            System.out.println();
        }
    }
}
