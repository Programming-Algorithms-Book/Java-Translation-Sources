public class Program {
    /* Максимален брой булеви променливи */
    private static final int MaxN = 100;

    /* Максимален брой дизюнкти */
//    private static final int MaxK = 100;

    private static final int N = 4; /* Брой на булевите променливи */
    private static final int K = 5; /* Брой на дизюнктите */

    private static int[][] Expr = new int[][] { new int[] { 1, 4, 0 },
            new int[] { -1, 2, 0 }, new int[] { 1, -3, 0 },
            new int[] { -2, 3, -4, 0 }, new int[] { -1, -2, -3, 0 } };

    private static int[] Values = new int[MaxN];

    public static void main(String[] args) {
        assign(0);
    }

    private static void printAssign() {
        System.out.print("Изразът е удовлетворим за: ");
        for (int i = 0; i < N; i++) {
            System.out.printf("X%d=%d ", i + 1, Values[i]);
        }

        System.out.println();
    }

    /* поне един литерал трябва да има стойност “истина” във всеки дизюнкт */
    private static int getTrue(int t) {
        for (int i = 0; i < K; i++) {
            int j = 0;
            boolean isOk = false;
            while (Expr[i][j] != 0) {
                int p = Expr[i][j];
                if ((p > t) || (-p > t)) {
                    isOk = true;
                    break;
                }

                if ((p > 0) && (1 == Values[p - 1])) {
                    isOk = true;
                    break;
                }

                if ((p < 0) && (0 == Values[-p - 1])) {
                    isOk = true;
                    break;
                }

                j++;
            }

            if (isOk == false) {
                return 0;
            }
        }

        return 1;
    }

    /* присвоява стойности на променливите */
    private static void assign(int i) {
        if (getTrue(i) == 0) {
            return;
        }

        if (i == N) {
            printAssign();
            return;
        }

        Values[i] = 1;
        assign(i + 1);
        Values[i] = 0;
        assign(i + 1);
    }
}
