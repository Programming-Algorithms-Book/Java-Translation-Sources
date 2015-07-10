public class Program {
    private static int[] X = { 100, 10, 15, 5, 25, 22, 12, 22 }; /* Редица */
    /* Нулевият елемент на x[] не се използва! */
    private static int N = X.length - 1; /* Брой елементи в редицата */
    private static int[][] Lns = new int[N + 1][N + 1]; /* Целева функция */

    public static void main(String[] args) {
        int length = lsLength();
        System.out.printf("Дължина на най-дългата ненамаляваща подредица: %d",
                length);
        System.out.println();
        System.out.print("Подредицата (обърната): ");
        lnsPrint(length);
        System.out.println();
        System.out.print("Подредицата: ");
        lnsPrint2(N, length);
    }

    /* Намира дължината на най-дългата ненамаляваща подредица */
    private static int lsLength() {
        /* Начална инициализация */
        for (int i = 0; i <= N; i++) {
            Lns[i][0] = -1;
            for (int j = 1; j <= N; j++) {
                Lns[i][j] = Integer.MAX_VALUE;
            }
        }

        /* Основен цикъл */
        int r = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (Lns[i - 1][j - 1] <= X[i] && X[i] <= Lns[i - 1][j]
                        && Lns[i - 1][j - 1] <= Lns[i - 1][j]) {
                    Lns[i][j] = X[i];
                    if (r < j) {
                        r = j;
                    }
                } else {
                    Lns[i][j] = Lns[i - 1][j];
                }
            }
        }

        return r;
    }

    /* Извежда най-дългата ненамаляваща подредица (в обратен ред) */
    private static void lnsPrint(int j) {
        int i = N;
        do {
            if (Lns[i][j] == Lns[i - 1][j]) {
                i--;
            } else {
                System.out.printf("%d ", X[i]);
                j--;
            }
        } while (i > 0);
    }

    /* Извежда най-дългата ненамаляваща подредица */
    private static void lnsPrint2(int i, int j) {
        if (i == 0) {
            return;
        }

        if (Lns[i][j] == Lns[i - 1][j]) {
            lnsPrint2(i - 1, j);
        } else {
            lnsPrint2(i, j - 1);
            System.out.printf("%d ", X[i]);
        }
    }
}
