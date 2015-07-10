public class Program {
    private static final String X = "acbcacbcaba"; /* Първа редица */
    private static final String Y = "abacacacababa"; /* Втора редица */

    public static void main(String[] args) {
        System.out.printf("Дължина на най-дългата обща подредица: %d\n",
                lcsLength());
    }

    /* Намира дължината на най-дългата обща подредица */
    private static int lcsLength() {
        int m = X.length(); /* Дължина на първата редица */
        int n = Y.length(); /* Дължина на втората редица */
        int[][] f = new int[m + 1][n + 1]; /* Целева функция */

        /* Начална инициализация */
        for (int i = 1; i <= m; i++) {
            f[i][0] = 0;
        }

        for (int j = 0; j <= n; j++) {
            f[0][j] = 0;
        }

        /* Основен цикъл */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }

        return f[m][n];
    }
}
