public class Program {
    private static final long NotSolved = -1;
    private static final int N = 10;
    private static final int K = 7;

    /* Целева функция */
    private static long[] F = new long[N + 1];

    /* Степените на k */
    private static long[] Pow = new long[N + 1];

    public static void main(String[] args) {
        init();
        F[0] = 1;
        F[1] = K;
        F[2] = (K * K) - 1;
        System.out.println((K - 1) * solve(N - 1));
    }

    private static void init() {
        Pow[0] = 1;
        for (int i = 1; i <= N; i++) {
            Pow[i] = K * Pow[i - 1];
            F[i] = NotSolved;
        }
    }

    private static long solve(int s) {
        if (F[s] == NotSolved) {
            F[s] = Pow[s - 2];
            for (int i = 1; i < s - 1; i++) {
                F[s] += solve(i - 1) * (K - 1) * Pow[s - i - 2];
            }

            F[s] = Pow[s] - F[s];
        }

        return F[s];
    }
}
