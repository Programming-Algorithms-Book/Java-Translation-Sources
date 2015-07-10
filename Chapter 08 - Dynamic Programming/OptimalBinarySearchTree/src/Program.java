public class Program {

    /* Честоти на срещане */
    private static int[] F = { 2, 7, 1, 3, 4, 6, 5 };

    /* Брой честоти */
    private static int N = F.length;

    /* Таблица - целева функция */
    private static int[][] M = new int[N + 2][N + 1];

    public static void main(String[] args) {
        solve();
        System.out.printf(
                "Минималната дължина на претегления вътрешен път е: %d\n",
                M[1][N]);
        printMatrix();
        System.out.println();
        System.out.println("Оптимално дърво за претърсване:");
        getOrder(1, N, 0);
    }

    /* Построява оптимално двоично дърво за претърсване */
    private static void solve() {
        /* Инициализация */
        for (int i = 1; i <= N; i++) {
            M[i][i] = F[i - 1];
            M[i][i - 1] = 0;
        }

        M[N + 1][N] = 0;

        /* Основен цикъл */
        for (int j = 1; j <= N - 1; j++) {
            for (int i = 1; i <= N - j; i++) {
                M[i][i + j] = Integer.MAX_VALUE;
                for (int k = i; k <= i + j; k++) {
                    /* Подобряваме текущото решение */
                    int t = M[i][k - 1] + M[k + 1][i + j];
                    if (t < M[i][i + j]) {
                        M[i][i + j] = t;
                        M[i + j + 1][i] = k;
                    }
                }

                for (int k = i - 1; k < i + j; k++) {
                    M[i][i + j] += F[k];
                }
            }
        }
    }

    /* Извежда матрицата на минимумите на екрана */
    private static void printMatrix() {
        System.out.println("Матрица на минимумите:");
        for (int i = 1; i <= N + 1; i++) {
            System.out.println();
            for (int j = 1; j <= N; j++) {
                System.out.printf("%1$8d", M[i][j]);
            }
        }
    }

    /* Извежда оптималното дърво на екрана */
    private static void getOrder(int ll, int rr, long h) {
        if (ll > rr) {
            return;
        }

        if (ll == rr) {
            for (int i = 0; i < h; i++) {
                System.out.print("     ");
            }

            System.out.printf("d%d\n", rr);
        } else {
            getOrder(ll, M[rr + 1][ll] - 1, h + 1);
            for (int i = 0; i < h; i++) {
                System.out.print("     ");
            }

            System.out.printf("d%d\n", M[rr + 1][ll]);
            getOrder(M[rr + 1][ll] + 1, rr, h + 1);
        }
    }
}
