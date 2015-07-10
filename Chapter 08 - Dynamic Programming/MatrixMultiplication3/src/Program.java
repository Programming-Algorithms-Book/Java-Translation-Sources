public class Program {

    /* Размерности на матриците */
    private static int[] R = { 12, 13, 35, 3, 34, 2, 21, 10, 21, 6 };

    /* Брой матрици */
    private static int N = R.length - 1;

    /* Таблица - целева функция */
    private static int[][] M = new int[N + 1][N + 1];

    /* Ред на умножение на матриците */
    private static Order[] MatrixOrder = new Order[(N + 1) * (N + 1)];

    /* Брой действия за пресмятането */
    private static int count;

    public static void main(String[] args) {
        solve();
        count = 0;
        buildOrder(1, N);
        System.out.printf("Минималният брой умножения е: %d\n", M[1][N]);
        printMatrix();
        System.out.println();
        printMultiplyPlan();
        System.out.println();
        System.out.print("Ред на умножение на матриците: ");
        getOrder(1, N);
    }

    /*
     * Формира таблица, съдържаща минималния брой умножения, необходими за
     * умножението на всяка двойка матрици, както и индексът, за който се
     * постига
     */
    private static void solve() {
        /* Инициализация */
        for (int i = 0; i < MatrixOrder.length; ++i) {
            MatrixOrder[i] = new Order(0, 0);
        }

        for (int i = 1; i <= N; i++) {
            M[i][i] = 0;
        }

        /* Основен цикъл */
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= N - j; i++) {
                M[i][i + j] = Integer.MAX_VALUE;
                for (int k = i; k < i + j; k++) {
                    int t = M[i][k] + M[k + 1][i + j]
                            + (R[i - 1] * R[k] * R[i + j]);
                    /* Подобряване на текущото решение */
                    if (t < M[i][i + j]) {
                        M[i][i + j] = t;
                        M[i + j][i] = k;
                    }
                }
            }
        }
    }

    /* Формира алгоритъм за умножение */
    private static int buildOrder(int ll, int rr) {
        int ret = count++;
        if (ll < rr) {
            MatrixOrder[ret].setLeft(buildOrder(ll, M[rr][ll]));
            MatrixOrder[ret].setRight(buildOrder(M[rr][ll] + 1, rr));
        } else {
            MatrixOrder[ret].setLeft(ll);
            ;
            MatrixOrder[ret].setRight(rr);
            ;
        }

        return ret;
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

    /* Извежда план за умножение на матриците */
    private static void printMultiplyPlan() {
        System.out.println("План за умножение на матриците:");
        for (int i = 0; i < count; i++) {
            if (MatrixOrder[i].getLeft() == MatrixOrder[i].getRight()) {
                System.out.printf("L[%d] = M%d\n", i, MatrixOrder[i].getLeft());
            } else {
                System.out.printf("L[%d] = L[%d] * L[%d]\n", i,
                        MatrixOrder[i].getLeft(), MatrixOrder[i].getRight());
            }
        }
    }

    /* Изразява реда на умножение с помощта на скоби */
    private static void getOrder(int ll, int rr) {
        if (ll == rr) {
            System.out.printf("M%d", ll);
        } else {
            System.out.print("(");
            getOrder(ll, M[rr][ll]);
            System.out.print("*");
            getOrder(M[rr][ll] + 1, rr);
            System.out.print(")");
        }
    }
}
