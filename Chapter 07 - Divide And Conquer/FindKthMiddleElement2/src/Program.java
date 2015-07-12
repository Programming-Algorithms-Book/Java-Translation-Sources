import java.util.Random;

public class Program {
    private static Random Rand = new Random();
    private static int[] array;

    /* Извежда масива на екрана */
    public static void printArray(int[] numbers) {
        for (int number : numbers) {
            System.out.printf("%d ", number);
        }

        System.out.println();
    }

    /* Търсене по Хоор */
    public static int findKthElement(int left, int right, int k) {
        if (left == right) {
            return left;
        }

        int middle = partition(left, right);
        int p = middle - left + 1;
        return k > p ? findKthElement(left, middle, k) : findKthElement(
                middle + 1, right, k - p);
    }

    public static void main(String[] args) {
        int n = 10;
        int k = 4;
        array = new int[n];
        initializeArray(array);
        printArray(array);
        findKthElement(0, n - 1, k);
        printArray(array);
    }

    /* Раделяне по Ломуто */
    private static int partition(int left, int right) {
        int i = left - 1;
        int x = array[right];
        for (int j = left; j <= right; j++) {
            if (array[j] <= x) {
                i++;

                int oldValue = array[i];
                array[i] = array[j];
                array[j] = oldValue;
            }
        }

        /* Всички са <= x. Стесняване на областта с 1. */
        if (i == right) {
            i--;
        }

        return i;
    }

    /* Запълва масива със случайни числа */
    private static void initializeArray(int[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = Rand.nextInt() % ((2 * input.length) + 1);
        }
    }
}
