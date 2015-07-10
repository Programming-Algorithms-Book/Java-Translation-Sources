public class Program {
    private static final int MaxN = 100; /* Максимален брой километри */
    private static final int MaxK = 20; /* Максимален брой спирки */
    private static final int N = 15;
    private static final int K = 10;

    private static Dist[] Dist = new Dist[MaxN];

    private static int[] Values = { 0, 12, 21, 31, 40, 49, 58, 69, 79, 90, 101 };

    public static void main(String[] args) {
        initDistArray(Dist);
        solve(N);
        print(N);
    }

    /* Решава задачата чрез динамично оптимиране */
    private static void solve(int n) {
        Dist[0].setValue(0);
        for (int i = 1; i <= n; i++) {
            Dist[i].setValue(Integer.MAX_VALUE);
            for (int j = 1; j <= K && j <= i; j++) {
                if (Dist[i - j].getValue() + Values[j] < Dist[i].getValue()) {
                    Dist[i].setValue(Dist[i - j].getValue() + Values[j]);
                    Dist[i].setLast(j);
                }
            }
        }
    }

    /* Извежда резултата на екрана */
    private static void print(int n) {
        System.out.printf("Обща стойност на пътуването: %d\n",
                Dist[n].getValue());
        System.out.println("Дължина и стойности на отделните отсечки:");
        while (n > 0) {
            System.out.printf("%d %d\n", Dist[n].getLast(),
                    Values[Dist[n].getLast()]);
            n -= Dist[n].getLast();
        }
    }

    private static void initDistArray(Dist[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = new Dist(0, 0);
        }
    }
}
