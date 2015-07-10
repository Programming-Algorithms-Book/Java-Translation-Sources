public class Program {
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int UP_LEFT = 3;

    /* Първа редица */
    private static final String X = "acbcacbcaba";

    /* Втора редица */
    private static final String Y = "abacacacababa";

    /* Дължина на първата редица */
    private static int M = X.length();

    /* Дължина на втората редица */
    private static int N = Y.length();

    /* Указател към предходен елемент */
    private static int[][] B = new int[M + 1][N + 1];

    /* Целева функция */
    private static int[][] F = new int[M + 1][N + 1];

    public static void main(String[] args) {
        System.out.printf("Дължина на най-дългата обща подредица: %d\n",
                lcsLength());
        System.out
                .print("PrintLCS:  Максимална обща подредица (в обратен ред): ");
        printLcs();
        System.out.println();
        System.out.print("PrintLCS2: Максимална обща подредица: ");
        printLcs2(X.length(), Y.length());
        System.out.println();
        System.out.print("PrintLCS3: Максимална обща подредица: ");
        printLcs3(X.length(), Y.length());
    }

    /* Намира дължината на най-дългата обща подредица */
    private static int lcsLength() {
        /* Начална инициализация */
        for (int i = 1; i <= M; i++) {
            F[i][0] = 0;
        }

        for (int j = 0; j <= N; j++) {
            F[0][j] = 0;
        }

        /* Основен цикъл */
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    F[i][j] = F[i - 1][j - 1] + 1;
                    B[i][j] = UP_LEFT;
                } else if (F[i - 1][j] >= F[i][j - 1]) {
                    F[i][j] = F[i - 1][j];
                    B[i][j] = UP;
                } else {
                    F[i][j] = F[i][j - 1];
                    B[i][j] = LEFT;
                }
            }
        }

        return F[M][N];
    }

    /* Намира една възможна максимална обща подредица (обърната) */
    private static void printLcs() {
        int i = X.length();
        int j = Y.length();
        while (i > 0 && j > 0) {
            switch (B[i][j]) {
                case UP_LEFT:
                    System.out.print(X.charAt(i - 1));
                    i--;
                    j--;
                    break;
                case UP:
                    i--;
                    break;
                case LEFT:
                    j--;
                    break;
            }
        }
    }

    /* Намира една възможна максимална обща подредица */
    private static void printLcs2(int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }

        if (B[i][j] == UP_LEFT) {
            printLcs2(i - 1, j - 1);
            System.out.print(X.charAt(i - 1));
        } else if (B[i][j] == UP) {
            printLcs2(i - 1, j);
        } else {
            printLcs2(i, j - 1);
        }
    }

    /* Намира една възможна максимална обща подредица */
    private static void printLcs3(int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }

        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            printLcs3(i - 1, j - 1);
            System.out.print(X.charAt(i - 1));
        } else if (F[i][j] == F[i - 1][j]) {
            printLcs3(i - 1, j);
        } else {
            printLcs3(i, j - 1);
        }
    }
}
