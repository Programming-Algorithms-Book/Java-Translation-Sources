public class Program {
    // // private static final int MaxN = 30;
    private static final int MAX_CAPACITY = 1000;
    private static final int TOTAL_CAPACITY = 15; /* Обща вместимост на раницата */
    private static final int N = 6; /* Брой предмети */

    /* Тегло на предметите */
    private static int[] Weights = new int[] { 0, 1, 2, 3, 5, 6, 7 };

    /* Стойност на предметите */
    private static int[] Values = new int[] { 0, 1, 10, 19, 22, 25, 30 };

    public static void main(String[] args) {
        System.out.printf("Брой предмети: %d\n", N);
        System.out.printf("Максимална допустима обща маса: %d\n",
                TOTAL_CAPACITY);
        System.out.printf("Максимална постигната ценност: %d\n", calculate());
    }

    /* Пресмята стойностите на целевата функция */
    private static int calculate() {
        int[] f = new int[MAX_CAPACITY]; /* Целева функция */
        int[] oldF = new int[MAX_CAPACITY];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= TOTAL_CAPACITY; j++) {
                if (j >= Weights[i]
                        && oldF[j] < oldF[j - Weights[i]] + Values[i]) {
                    f[j] = oldF[j - Weights[i]] + Values[i];
                } else {
                    f[j] = oldF[j];
                }
            }

            for (int k = 0; k < TOTAL_CAPACITY; k++) {
                oldF[k] = f[k];
            }
        }

        return f[TOTAL_CAPACITY];
    }
}
