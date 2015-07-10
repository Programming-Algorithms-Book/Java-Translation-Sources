public class Program {
    private static final long NotCalculated = Long.MAX_VALUE;
    private static final long L1 = 3;
    private static final long L2 = 6;
    private static final long L3 = 8;
    private static final long C1 = 20;
    private static final long C2 = 30;
    private static final long C3 = 40;
    private static final int End = 6;

    /* Разстояние от началната гара */
    private static long[] Dist = { 0, 3, 7, 8, 13, 15, 23 };
    private static int N = Dist.length - 1;

    /* Минимална цена на билета от началната до текущата гара */
    private static long[] MinPrice = new long[N];

    private static int start = 2;

    public static void main(String[] args) {
        /* Иницализация */
        int i;
        for (i = 0; i < start; i++) {
            MinPrice[i] = 0;
        }

        for (; i < N; i++) {
            MinPrice[i] = NotCalculated;
        }

        /* Решаване на задачата */
        start--;
        System.out.printf("Минимална цена: %d\n", calc(End - 1));
    }

    private static long calc(int cur) {
        long price;
        int i;
        if (MinPrice[cur] == NotCalculated) {
            /*
             * Търсим най-лявата гара и пресмятаме евентуалната цена, ако вземем
             * билет тип 1
             */
            for (i = cur - 1; i >= start && (Dist[cur] - Dist[i]) <= L1; i--) {
            }

            if (++i < cur) {
                if ((price = calc(i) + C1) < MinPrice[cur]) {
                    MinPrice[cur] = price;
                }
            }

            /*
             * Търсим най-лявата гара и пресмятаме евентуалната цена, ако вземем
             * билет тип 2
             */
            for (; i >= start && (Dist[cur] - Dist[i]) <= L2; i--) {
            }

            if (++i < cur) {
                if ((price = calc(i) + C2) < MinPrice[cur]) {
                    MinPrice[cur] = price;
                }
            }

            /*
             * Търсим най-лявата гара и пресмятаме евентуалната цена, ако вземем
             * билет тип 3
             */
            for (; i >= start && (Dist[cur] - Dist[i]) <= L3; i--) {
            }

            if (++i < cur) {
                if ((price = calc(i) + C3) < MinPrice[cur]) {
                    MinPrice[cur] = price;
                }
            }
        }

        return MinPrice[cur];
    }
}
