public class Program {
    /* Максимален брой върхове в графа */
    private static final int MAX_N = 200;

    /* Брой върхове в графа */
    private static final int N = 5;

    /* Матрица на съседство на графа */
    private static int[][] A = { { 0, 1, 0, 0, 1 }, { 1, 0, 1, 1, 1 },
            { 0, 1, 0, 1, 0 }, { 0, 1, 1, 0, 1 }, { 1, 1, 0, 1, 0 } };

    private static int[] Col = new int[MAX_N];

    private static int maxCol, count = 0;

    public static void main(String[] args) {
        int i;
        for (maxCol = 1; maxCol <= N; maxCol++) {
            for (i = 0; i < N; i++) {
                Col[i] = 0;
            }

            nextCol(0);
            if (count > 0) {
                break;
            }
        }

        System.out.printf("Общ брой намерени оцветявания с %d цвята: %d \n",
                maxCol, count);
    }

    private static void showSol() {
        int i;
        count++;
        System.out.println("Минимално оцветяване на графа: ");
        for (i = 0; i < N; i++) {
            System.out.printf("Връх %d - с цвят %d \n", i + 1, Col[i]);
        }
    }

    private static void nextCol(int i) {
        int k;

        if (i == N) {
            showSol();
            return;
        }

        for (k = 1; k <= maxCol; k++) {
            Col[i] = k;
            int success = 1;
            for (int j = 0; j < N; j++) {
                if (1 == A[i][j] && Col[j] == Col[i]) {
                    success = 0;
                    break;
                }
            }

            if (success == 1) {
                nextCol(i + 1);
            }

            Col[i] = 0;
        }
    }
}
