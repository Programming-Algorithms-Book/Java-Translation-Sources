public class Program {
    /* Височини на дърветата */
    private static int[] X = { 0, 10, 20, 15, 40, 5, 4, 300, 2, 1 };

    /* Брой крайпътни дървета */
    private static int N = X.length - 1;
    private static St[] Max1 = new St[N + 1];
    private static St[] Max2 = new St[N + 1];

    private static int[] X2 = new int[N + 1];
    private static int[] Rez = new int[N];
    private static int top;
    private static long bestLen, bestSum;

    public static void main(String[] args) {
        initStArray(Max1);
        initStArray(Max2);

        solve();
        buildSequence();
        print();
    }

    /* Търси нарастваща редица */
    private static void findIncSequence(St[] max, int[] x) {
        /* Основен цикъл */
        for (int i = 1; i <= N; i++) {
            max[i].setLength(0);
            max[i].setSum(0);
            for (int j = 0; j < i; j++) {
                if (x[j] <= x[i]) {
                    if ((max[j].getLength() + 1 > max[i].getLength())
                            || ((max[j].getLength() + 1 == max[i].getLength()) && (max[j]
                                    .getSum() + x[i] > max[i].getSum()))) {
                        max[i].setBack(j);
                        max[i].setLength(max[j].getLength() + 1);
                        max[i].setSum(max[j].getSum() + x[i]);
                    }
                }
            }
        }
    }

    /* Построява обърнато копие на редицата */
    private static void reverse(int[] x2, int[] x) {
        for (int i = 1; i <= N; i++) {
            x2[i] = x[N - i + 1];
        }
    }

    /* Намира търсената редица */
    private static void solve() {
        /* стъпка (1) */
        findIncSequence(Max1, X);

        /* стъпка (2) */
        reverse(X2, X);
        findIncSequence(Max2, X2);

        /* стъпка (3) */
        bestLen = 0;
        bestSum = 0;
        for (int i = 1; i <= N; i++) {
            if ((Max1[i].getLength() + Max2[N - i + 1].getLength() > bestLen)
                    || ((Max1[i].getLength() + Max2[N - i + 1].getLength() == bestLen) && (Max1[i]
                            .getSum() + Max2[N - i + 1].getSum() > bestSum))) {
                bestLen = Max1[i].getLength() + Max2[N - i + 1].getLength();

                /* Трябва да се намали с 1 */
                bestSum = Max1[i].getSum() + Max2[N - i + 1].getSum();
                top = i;
            }
        }
    }

    /* Построява търсената редица */
    private static void buildSequence() {
        int t = top;
        int len = 0;
        /* Построяване на нарастващата част на редицата */
        for (int l = Max1[t].getLength(); t != 0; t = Max1[t].getBack()) {
            Rez[l - len++] = X[t];
        }

        /* Построяване на намаляващата част на редицата */
        for (t = Max2[N - top + 1].getBack(); t != 0; t = Max2[t].getBack()) {
            Rez[++len] = X2[t];
        }
    }

    /* Извежда резултата на екрана */
    private static void print() {
        System.out.printf(
                "Максимален брой дървета, които могат да се запазят: %d\n",
                bestLen - 1);
        for (int i = 1; i < bestLen; i++) {
            System.out.printf("%d ", Rez[i]);
        }

        System.out.println();
    }

    private static void initStArray(St[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = new St(0, 0, 0);
        }
    }
}
