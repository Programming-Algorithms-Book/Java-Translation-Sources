public class Program {
    private static final int Maxn = 100; /* Максимален брой булеви променливи */
//    private static final int Maxk = 100; /* Максимален брой дизюнкти */

    private static final int N = 4; /* Брой на булевите променливи */
    private static final int K = 5; /* Брой на дизюнктите */

    private static int[][] expr = new int[][] { new int[] { 1, 4, 0 },
            new int[] { -1, 2, 0 }, new int[] { 1, -3, 0 },
            new int[] { -2, 3, -4, 0 }, new int[] { -1, -2, -3, 0 } };

    private static int[] values = new int[Maxn];

    public static void main(String[] args) {
        assign(0);
    }

    private static void printAssignment() {
        System.out.print("Изразът е удовлетворим за: ");
        for (int i = 0; i < N; i++) {
            System.out.printf("X%d=%d ", i + 1, values[i]);
        }

        System.out.println();
    }

    /* поне един литерал трябва да има стойност “истина” във всеки дизюнкт */
    private static int getTrue() {
        for (int i = 0; i < K; i++) {
            int j = 0;
            int ok = 0;
            while (expr[i][j] != 0) {
                int p = expr[i][j];
                if ((p > 0) && (1 == values[p - 1])) {
                    ok = 1;
                    break;
                }

                if ((p < 0) && (0 == values[-p - 1])) {
                    ok = 1;
                    break;
                }

                j++;
            }

            if (ok == 0) {
                return 0;
            }
        }

        return 1;
    }

    /* присвоява стойности на променливите */
    private static void assign(int i) {
        if (i == N) {
            if (getTrue() == 1) {
                printAssignment();
            }

            return;
        }

        values[i] = 1;
        assign(i + 1);
        values[i] = 0;
        assign(i + 1);
    }
}
