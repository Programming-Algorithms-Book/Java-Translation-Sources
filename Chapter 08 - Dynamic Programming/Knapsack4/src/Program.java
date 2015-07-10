public class Program {

    /* Максимална вместимост на раницата */
    private static final int MAX_CAPACITY = 1000;

    /* Обща вместимост на раницата */
    private static final int TOTAL_CAPACITY = 70;
    private static final int NUMBER_OF_ITEMS = 8;

    /* Целева функция */
    private static int[] F = new int[MAX_CAPACITY];

    /* Последен добавен предмет при достигане на максимума */
    private static int[] Best = new int[MAX_CAPACITY];

    /* Тегло на предметите */
    private static int[] Weights = new int[] { 0, 30, 15, 50, 10, 20, 40, 5, 65 };

    /* Стойност на предметите */
    private static int[] Values = new int[] { 0, 5, 3, 9, 1, 2, 7, 1, 12 };

    public static void main(String[] args) {
        System.out.printf("Брой предмети: %d\n", NUMBER_OF_ITEMS);
        System.out.printf("Максимална допустима обща маса: %d\n",
                TOTAL_CAPACITY);
        calculate();
        System.out.printf("Максимална постигната стойност: %d\n",
                F[TOTAL_CAPACITY]);
        printSet();
    }

    private static void calculate() {
        /* Основен цикъл */
        for (int j = 1; j <= NUMBER_OF_ITEMS; j++) {
            for (int i = 1; i <= TOTAL_CAPACITY; i++) {
                if (i >= Weights[j]) {
                    if (F[i] < F[i - Weights[j]] + Values[j]) {
                        F[i] = F[i - Weights[j]] + Values[j];
                        Best[i] = j;
                    }
                }
            }
        }
    }

    /* Извежда едно възможно множество от предмети, за което */
    /* се постига максимална стойност на целевата функция */

    private static void printSet() {
        int value = TOTAL_CAPACITY;
        System.out.print("Вземете следните предмети: ");
        while (value != 0) {
            System.out.printf("%d ", Best[value]);
            value -= Weights[Best[value]];
        }

        System.out.println();
    }
}
