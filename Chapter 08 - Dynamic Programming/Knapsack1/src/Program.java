public class Program {
    private static final int NOT_CALCULATED = -1;
    private static final int MAX_N = 30;
    private static final int MAX_CAPACITY = 1000;
    private static final int TOTAL_CAPACITY = 70; /* Обща вместимост на раницата */
    private static final int N = 8; /* Брой предмети */

    private static boolean[][] Set = new boolean[MAX_CAPACITY][MAX_N];
    private static int[] Fn = new int[MAX_CAPACITY]; /* Целева функция */
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
        int functionCur;
        /* Пресмятане на най-голямата стойност на F */
        for (bestI = functionBest = 0, i = 1; i <= N; i++) {
            if (k >= Weights[i]) {
                if (Fn[k - Weights[i]] == NOT_CALCULATED) {
                    calculateFunction(k - Weights[i]);
                }

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
        int i, sumM;

        /* Инициализиране */
        /* Иниц. на целевата функция */
        for (i = 0; i <= TOTAL_CAPACITY; i++) {
            Fn[i] = NOT_CALCULATED;
        }

        /* Дали не можем да вземем всички предмети? */
        for (sumM = Weights[1], i = 2; i <= N; i++) {
            sumM += Weights[i];
        }

        if (sumM <= TOTAL_CAPACITY) {
            System.out.println("Можете да вземете всички предмети!");
            return;
        } else {
            calculateFunction(TOTAL_CAPACITY); /* Пресмятане на стойността */

            /* Отпечатване на резултата */
            System.out.print("Вземете предметите с номера: ");
            for (i = 1; i <= N; i++) {
                if (Set[TOTAL_CAPACITY][i]) {
                    System.out.printf("%d ", i);
                }
            }

            System.out.printf("\nМаксимална постигната стойност: %d\n",
                    Fn[TOTAL_CAPACITY]);
        }
    }
}
