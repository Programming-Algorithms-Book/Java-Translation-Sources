public class Program {
    private static final double NotCalculated = -1;
    private static final double P = 0.5; /* Вероятност A да спечели отделен мач */
    private static final int N = 5;

    private static double[][] Ps = new double[N + 1][N + 1];

    public static void main(String[] args) {
        pDynamic2(N, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%.6f ", Ps[i][j]);
            }

            System.out.println();
        }
    }

    private static double pDynamic2(int i, int j) {
        for (int k = 1; k <= i; k++) {
            for (int l = 1; l <= j; l++) {
                Ps[k][l] = NotCalculated;
            }
        }

        for (int k = 1; k <= i; k++) {
            Ps[k][0] = 0.0;
        }

        for (int k = 1; k <= j; k++) {
            Ps[0][k] = 1.0;
        }

        return pDyn(i, j);
    }

    /* Динамично оптимиране */
    private static double pDyn(int i, int j) {
        if (Ps[i][j] < 0) {
            Ps[i][j] = (P * pDyn(i - 1, j)) + ((1 - P) * pDyn(i, j - 1));
        }

        return Ps[i][j];
    }
}
