public class Program {
    private static int[] X = { 100, 10, 15, 5, 25, 22, 12, 22 }; /* Редица */
    /* Нулевият елемент на x[] не се използва! */
    private static int N = X.length - 1; /* Брой елементи в редицата */
    private static int Start = -1;

    /* Дължина на максималната редица с начало x[i] */
    private static int[] Lns = new int[N + 1];

    /* Индекс на следващ елемент */
    private static int[] Next = new int[N + 1];

    public static void main(String[] args) {
        System.out.printf(
                "Дължина на най-дългата ненамаляваща подредица: %d\n",
                lnsLength());
        System.out.print("Подредицата: ");
        lnsPrint(Start);
    }

    /* Намира дължината на най-дългата ненамаляваща подредица */
    private static int lnsLength() {
        /* Максимална (за момента) дължина на ненамаляваща подредица */
        int len = 0;
        for (int i = N; i >= 1; i--) {
            int l = 0;

            /*
             * В момента на разглеждане на xi, /* l е дължината на максималната
             * подредица с начало xj:
             */
            /* 1) i < j <= n и */
            /* 2) xi <= xj */
            for (int j = i + 1; j <= N; j++) {
                if (X[j] >= X[i] && Lns[j] > l) {
                    l = Lns[j];
                    Next[i] = j;
                }
            }

            Lns[i] = l + 1;
            if (Lns[i] > len) {
                len = Lns[i];
                Start = i;
            }
        }

        return len;
    }

    /* Извежда най-дългата ненамаляваща подредица */
    private static void lnsPrint(int start) {
        for (; Lns[start] >= 1; start = Next[start]) {
            System.out.printf(" %d", X[start]);
        }
    }
}
