public class Program {
    /* Максимален размер на дъската */
    private static final int MAX_N = 10;
    /* Максимален брой правила за движение на коня */
    // private static final int MaxD = 10;

    /* Размер на дъската */
    private static final int N = 6;

    /* Стартова позиция */
    private static final int START_X = 1;
    private static final int START_Y = 1;

    /* Правила за движение на коня */
    private static final int MAX_DIFF = 8;
    private static int[] DiffX = { 1, 1, -1, -1, 2, -2, 2, -2 };
    private static int[] DiffY = { 2, -2, 2, -2, 1, 1, -1, -1 };

    private static int[][] Board = new int[MAX_N][MAX_N];
    private static int newX, newY;

    public static void main(String[] args) {
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                Board[i][j] = 0;
            }
        }

        nextMove(START_X - 1, START_Y - 1, 1);
        System.out.println("Задачата няма решение.");
    }

    private static void printBoard() {
        int i, j;
        for (i = N; i > 0; i--) {
            for (j = 0; j < N; j++) {
                System.out.printf("%1$3d", Board[i - 1][j]);
            }

            System.err.println();
        }

        System.exit(0);
    }

    private static void nextMove(int x, int y, int i) {
        int k;
        Board[x][y] = i;
        if (i == N * N) {
            printBoard();
            return;
        }

        for (k = 0; k < MAX_DIFF; k++) {
            newX = (int) (x + DiffX[k]);
            newY = (int) (y + DiffY[k]);
            if ((newX >= 0 && newX < N && newY >= 0 && newY < N)
                    && (0 == Board[newX][newY])) {
                nextMove(newX, newY, i + 1);
            }
        }

        Board[x][y] = 0;
    }
}
