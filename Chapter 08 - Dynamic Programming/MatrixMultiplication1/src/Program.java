public class Program {
    /* Размерности на матриците */
    private static long[] R = new long[] { 12, 13, 35, 3, 34, 2, 21, 10, 21, 6 };

    /* Брой матрици */
    private static int N = R.length - 1;

    /* Таблица - целева функция */
    private static long[][] M = new long[N + 1][N + 1];

    public static void main(String[] args) {
        System.out.printf("Минималният брой умножения е: %d\n",
                solveRecursive(1, N));
        printMatrix();
    }

    /* Неефективна рекурсивна функция */
    private static long solveRecursive(int i, int j) {
        if (i == j) {
            return 0;
        }

        M[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            long q = solveRecursive(i, k) + solveRecursive(k + 1, j)
                    + (R[i - 1] * R[k] * R[j]);
            if (q < M[i][j]) {
                M[i][j] = q;
            }
        }

        return M[i][j];
    }

    /* Извежда матрицата на минимумите на екрана */
    private static void printMatrix() {
        System.out.print("Матрица на минимумите:");
        for (int i = 1; i <= N; i++) {
            System.out.print("\n");
            for (int j = 1; j <= N; j++) {
                System.out.printf("%1$8d", M[i][j]);
            }
        }
    }
}
