public class Program {
    private static final int MAX_MOVES = 8;
    private static final int N = 12;

    private static int[] MoveX = { +1, -1, +1, -1, +2, +2, -2, -2 };
    private static int[] MoveY = { +2, +2, -2, -2, +1, -1, +1, -1 };

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        /* инициализация */
        int[][] a = new int[12][12];
        a[0][0] = 1;
        int x = 0;
        int y = 0;
        int p = 1;

        /* повтаря "алчната" стъпка, докато попълни цялата дъска */
        while (p < 12 * 12) {
            int min = Integer.MAX_VALUE;
            int choose = -1;
            for (int i = 0; i < MAX_MOVES; i++) {
                int temp = countMoves(a, x + MoveX[i], y + MoveY[i]);
                if (temp < min) {
                    min = temp;
                    choose = i;
                }
            }

            x += MoveX[choose];
            y += MoveY[choose];
            a[x][y] = ++p;
        }

        /* отпечатва резултата */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%1$4d", a[i][j]);
            }

            System.out.println();
        }
    }

    private static int countMoves(int[][] a, int x, int y) {
        int i, number = 0;
        if (x < 0 || y < 0 || x >= N || y >= N || a[x][y] != 0) {
            return Integer.MAX_VALUE; /* невалиден ход */
        }

        for (i = 0; i < MAX_MOVES; i++) {
            int nx = x + MoveX[i], ny = y + MoveY[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < N && a[nx][ny] == 0) {
                number++;
            }
        }

        return number;
    }
}
