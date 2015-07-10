public class Program {
    private static int[] X = { 100, 10, 15, 5, 25, 22, 12, 22 }; /* Редица */
    /* Нулевият елемент на x[] не се използва! */

    /* Брой елементи в редицата */
    private static int N = X.length - 1;

    /* LNS[i] - минимален елемент, който може да стои на позиция i */
    private static int[] Lns = new int[N + 1];

    public static void main(String[] args) {
        System.out.printf(
                "Дължина на най-дългата ненамаляваща подредица: %d\n",
                lnsLength());
    }

    /* Намира дължината на най-дългата ненамаляваща подредица */
    private static int lnsLength() {
        int k = 1;
        Lns[1] = X[1];
        for (int i = 2; i <= N; i++) {
            /* случай 1 */
            if (X[i] < Lns[1]) {
                Lns[1] = X[i];
            } else if (X[i] >= Lns[k]) {
                /* случай 2 */
                Lns[++k] = X[i];
            } else {
                /* случай 3 */
                int l = 1;
                int r = k; /* двоично търсене */
                while (l < r - 1) {
                    int med = (l + r) / 2;
                    if (Lns[med] <= X[i]) {
                        l = med;
                    } else {
                        r = med;
                    }
                }

                Lns[r] = X[i];
            }
        }

        return k;
    }
}
