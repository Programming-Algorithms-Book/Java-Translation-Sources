public class Program {
    private static final int NOT_CALCULATED = -1;
    private static final int MAX_N = 30;
    private static final int MAX_CAPACITY = 1000;
    private static final int TOTAL_CAPACITY = 70; /* Обща вместимост на раницата */
    private static final int N = 8; /* Брой предмети */

    private static boolean[][] Set = new boolean[MAX_CAPACITY][MAX_N];
    private static int[] Fn = new int[MAX_CAPACITY];
    private static int[] Weights = new int[] { 0, 30, 15, 50, 10, 20, 40, 5, 65 }; /* Тегла */
    private static int[] Values = new int[] { 0, 5, 3, 9, 1, 2, 7, 1, 12 }; /* Стойности */

    public static void main(String[] args) {
        System.out.printf("Брой предмети: %d\n", N);
        System.out.printf("Вместимост на раницата: %d\n", TOTAL_CAPACITY);
        calculate();
    }

    /* Пресмята стойността на функцията за k */
    private static void calculateFunction(int k) {
        int i;
        int bestI;
        int functionBest;

        /* Пресмятане на най-голямата стойност на F */
        for (bestI = functionBest = 0, i = 1; i <= N; i++) {
            if (k >= Weights[i]) {
                if (Fn[k - Weights[i]] == NOT_CALCULATED) {
                    calculateFunction(k - Weights[i]);
                }

                int functionCur;
                if (!Set[k - Weights[i]][i]) {
                    functionCur = Values[i] + Fn[k - Weights[i]];
                } else {
                    functionCur = 0;
                }

                if (functionCur > functionBest) {
                    bestI = i;
                    functionBest = functionCur;
                }
            }
        }

        /* Регистриране на най-голямата стойност на функцията */
        Fn[k] = functionBest;
        if (bestI > 0) {
            for (int p = 0; p < N; p++) {
                Set[k][p] = Set[k - Weights[bestI]][p];
            }

            Set[k][bestI] = true;
        }
    }

    private static void calculate() {
        int maxValue,
        /* Максимална постигната стойност */
        maxIndex; /* Индекс, за който е постигната */

        /* Пресмятане на стойностите на целевата функция */
        /* Търсим макс. стойност на Fn(i) */
        for (int i = 1; i <= TOTAL_CAPACITY; i++) {
            maxValue = maxIndex = 0;
            for (int j = 1; j <= N; j++) {
                if (Weights[j] <= i && !Set[i - Weights[j]][j]) {
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
                 * чрез добавяне на елемента maxIndex
                 */
                for (int p = 0; p < N; p++) {
                    Set[i][p] = Set[i - Weights[maxIndex]][p];
                }

                Set[i][maxIndex] = true;
            }

            if (Fn[i] < Fn[i - 1]) {
                /* Побират се всички предмети и още */
                Fn[i] = Fn[i - 1];
                for (int p = 0; p < N; p++) {
                    Set[i][p] = Set[i - 1][p];
                }
            }
        }

        /* Извеждане на резултата */
        System.out.print("Вземете предметите с номера: ");
        for (int i = 1; i <= N; i++) {
            if (Set[TOTAL_CAPACITY][i]) {
                System.out.printf("%d ", i);
            }
        }

        System.out.printf("\nМаксимална постигната стойност: %d\n",
                Fn[TOTAL_CAPACITY]);
    }
}
