import java.util.Random;

public class Program {
    /* Брой елементи за сортиране */
    private static final int N = 100;

    public static void main(String[] args) {
        /* Основен масив - за сортиране */
        int[] a = new int[N];

        /* Помощен масив */
        int[] b = new int[N];
        generate(a);
        System.out.println("Преди сортирането:");
        printArray(a);
        System.out.println();
        mergeSort(a, b, 0, N - 1);
        System.out.println("След сортирането:");
        printArray(a);
    }

    /* Генерира примерно множество */
    private static void generate(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt() % ((2 * N) + 1);
        }
    }

    /* Извежда списъка на екрана */
    private static void printArray(int[] array) {
        for (int i = 0; i < N; ++i) {
            System.out.printf("%1$5d", array[i]);
        }
    }

    /* Сортиране */

    private static void mergeSort(int[] a, int[] b, int left, int right) {
        if (right <= left) {
            /* Проверка дали има какво да се сортира */
            return;
        }

        int mid = (right + left) / 2;

        /* Сортиране на левия дял */
        mergeSort(a, b, left, mid);

        /* Сортиране на десния дял */
        mergeSort(a, b, mid + 1, right);

        /* Копиране на елементите на a[] в помощния масив b[] */
        int i, j;
        for (i = mid + 1; i > left; i--) {
            /* Права посока */
            b[i - 1] = a[i - 1];
        }

        for (j = mid; j < right; j++) {
            /* Обратна посока */
            b[right + mid - j] = a[j + 1];
        }

        /* Сливане на двата масива в a[] */
        for (int k = left; k <= right; k++) {
            a[k] = (b[i] < b[j]) ? b[i++] : b[j--];
        }
    }
}
