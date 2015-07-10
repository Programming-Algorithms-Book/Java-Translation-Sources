public class Program {
    /* Вероятност A да спечели отделен мач */
    private static final double P = 0.5;
    private static final int N = 5;

    private static Double[][] Ps = new Double[N + 1][N + 1];

    public static void main(String[] args) {
        pDynamic(N, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%.6f ", Ps[i][j] != null ? Ps[i][j] : 0.0);
            }

            System.out.println();
        }
    }

    private static double pDynamic(int i, int j) {
        for (int k = 1; k <= i; k++) {
            Ps[0][k] = 1.0;
        }

        for (int k = 1; k <= j; k++) {
            Ps[k][0] = 0.0;
        }

        return pDyn(i, j);
    }

    private static double pDyn(int i, int j) /* Динамично оптимиране */
    {
        if (Ps[i][j] == null) {
            Ps[i][j] = (P * pDyn(i - 1, j)) + ((1 - P) * pDyn(i, j - 1));
        }

        return Ps[i][j];
    }
}
