public class Program {
    private static final int MAX = 100;
    private static final int N = 10;

    private static int[][] F = new int[MAX][MAX]; /* Целева функция */

    /* Намира броя на представянията на n като сума от естествени числа */
    public static void main(String[] args) {
        System.out
                .printf("Броят на представянията на %d като сума от естествени числа е: %d",
                        N, getNum(N));
    }

    private static int getNum(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (1 == j) {
                    F[i][j] = 1;
                } else if (1 == i) {
                    F[i][j] = 1;
                } else if (i < j) {
                    F[i][j] = F[i][i];
                } else if (i == j) {
                    F[i][j] = 1 + F[i][i - 1];
                } else {
                    F[i][j] = F[i][j - 1] + F[i - j][j];
                }
            }
        }

        return F[n][n];
    }
}
