public class Program {
    private static final int MAX_N = 30;
    private static final int MAX_CAPACITY = 1000;
    private static final int TOTAL_CAPACITY = 70; /* Обща вместимост на раницата */
    private static final int N = 8; /* Брой предмети */

    private static int[][] Set = new int[MAX_CAPACITY][MAX_N / 8];
    private static int[] Fn = new int[MAX_CAPACITY];
    private static int[] Weights = new int[] { 0, 30, 15, 50, 10, 20, 40, 5, 65 }; /* Тегла */
    private static int[] Values = new int[] { 0, 5, 3, 9, 1, 2, 7, 1, 12 }; /* Стойности */

    public static void main(String[] args) {
        System.out.printf("Брой предмети: %d\n", N);
        System.out.printf("Вместимост на раницата: %d\n", TOTAL_CAPACITY);
        calculate();
    }

    private static void calculate() {
        int maxValue,
        /* Максимална постигната стойност */
        maxIndex; /* Индекс, за който е постигната */

        /* Пресмятаме стойностите на целевата функция */
        for (int i = 1; i <= TOTAL_CAPACITY; i++) {
            /* Търсене макс. стойност на F(i) */
            maxValue = maxIndex = 0;
            for (int j = 1; j <= N; j++) {
                if (Weights[j] <= i
                        && ((Set[i - Weights[j]][j >> 3] & (1 << (j & 7))) == 0)) {
                    if (Values[j] + Fn[i - Weights[j]] > maxValue) {
                        maxValue = Values[j] + Fn[i - Weights[j]];
                        maxIndex = j;
                    }
                }
            }

            if (maxIndex > 0) {
                /* Има ли предмет с тегло по-малко от i? */
                Fn[i] = maxValue;
                /*
                 * Новото множество set[i] се получава от set[i-m[maxIndex]]
                 * чрез прибавяне на елемента maxIndex
                 */
                int count = (N >> 3) + 1;
                for (int p = 0; p < count; p++) {
                    Set[i][p] = Set[i - Weights[maxIndex]][p];
                }

                Set[i][maxIndex >> 3] |= 1 << (maxIndex & 7);
            }

            if (Fn[i] < Fn[i - 1]) {
                /* Побират се всички предмети и още */
                Fn[i] = Fn[i - 1];
                int count = (N >> 3) + 1;
                for (int p = 0; p < count; p++) {
                    Set[i][p] = Set[i - 1][p];
                }
            }
        }

        /* Извеждане на резултата */
        System.out.print("Вземете предметите с номера: ");
        for (int i = 1; i <= N; i++) {
            if ((Set[TOTAL_CAPACITY][i >> 3] & (1 << (i & 7))) != 0) {
                System.out.printf("%d ", i);
            }
        }

        System.out.printf("\nМаксимална постигната стойност: %d\n",
                Fn[TOTAL_CAPACITY]);
    }
}
