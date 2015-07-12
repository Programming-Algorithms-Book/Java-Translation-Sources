import java.util.Random;

public class Program {
    private static Random Rand = new Random();

    /* Извежда масива на екрана */
    public static void printArray(int[] numbers) {
        for (int number : numbers) {
            System.out.printf("%d ", number);
        }

        System.out.println();
    }

    public static void findKthElement(int[] array, int n, int k) {
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int x = array[k];
            int i = left;
            int j = right;

            while (true) {
                while (x > array[i]) {
                    i++;
                }

                while (x < array[j]) {
                    j--;
                }

                if (i > j) {
                    break;
                }

                int oldValue = array[i];
                array[i] = array[j];
                array[j] = oldValue;

                i++;
                j--;
            }

            if (j < k) {
                left = i;
            }

            if (k < i) {
                right = j;
            }
        }
    }

    public static void main(String[] args) {
        /* Брой елементи в масива */
        int n = 10;

        /* Пореден номер на търсения елемент */
        int k = 4;

        int[] array = new int[n];
        initializeArray(array);
        System.out.println("Масивът преди търсенето: ");
        printArray(array);

        System.out.printf("\nТърсим k-ия елемент: k=%d\n", k);
        findKthElement(array, n, k);
        System.out.println("\nМасивът след търсенето:");
        printArray(array);

        System.out.printf("\nk-ият елемент е: %d\n", array[k]);
    }

    /* Запълва масива със случайни числа */
    private static void initializeArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Rand.nextInt() % ((2 * array.length) + 1);
        }
    }
}
