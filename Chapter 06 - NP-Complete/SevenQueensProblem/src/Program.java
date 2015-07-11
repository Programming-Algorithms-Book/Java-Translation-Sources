public class Program {
    /* Максимален размер на дъската */
    private static final int MAX_N = 100;

    /* Размер на дъската */
    private static final int N = 13;

    private static int[] Col = new int[MAX_N];
    private static int[] Rd = new int[(2 * MAX_N) - 1];
    private static int[] Ld = new int[2 * MAX_N];
    private static int[] Queens = new int[MAX_N];

    public static void main(String[] args) {
        int i;
        for (i = 0; i < N; i++) {
            Col[i] = 1;
        }

        for (i = 0; i < ((2 * N) - 1); i++) {
            Rd[i] = 1;
        }

        for (i = 0; i < 2 * N; i++) {
            Ld[i] = 1;
        }

        generate(0);
        System.out.println("Задачата няма решение!");
    }

    /* Отпечатва намереното разположение на цариците */
    private static void printBoard() {
        int i, j;
        for (i = 0; i < N; i++) {
            System.out.println();
            for (j = 0; j < N; j++) {
                if (Queens[i] == j) {
                    System.out.print("x ");
                } else {
                    System.out.print(". ");
                }
            }
        }

        System.out.println();
        System.exit(0);
    }

    /* Намира следваща позиция за поставяне на царица */
    private static void generate(int i) {
        if (i == N) {
            printBoard();
        }

        int k;
        for (k = 0; k <= N; k++) {
            if (Col[k] != 0 && Rd[i + k] != 0 && Ld[N + i - k] != 0) {
                Col[k] = 0;
                Rd[i + k] = 0;
                Ld[N + i - k] = 0;
                Queens[i] = k;
                generate(i + 1);
                Col[k] = 1;
                Rd[i + k] = 1;
                Ld[N + i - k] = 1;
            }
        }
    }
}
