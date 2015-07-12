import java.util.Arrays;
import java.util.Random;

public class Program {
    private static Random Rand = new Random();
    private static int[] Array = new int[10];

    public static void main(String[] args) {
        initializeArray();
        System.out.printf("Масивът: %s\n", Arrays.toString(Array));

        int k = 5;
        /* Пореден номер на търсения елемент */
        heapFindKthElement(k);
        System.out.printf("K-тия елемент е: %d\n", Array[0]);
    }

    /* Запълва масива със случайни числа */
    private static void initializeArray() {
        for (int i = 0; i < Array.length; i++) {
            Array[i] = Rand.nextInt(Integer.MAX_VALUE)
                    % ((2 * Array.length) + 1);
        }
    }

    /* Търсене на k-ия елемент с пирамида */
    private static void heapFindKthElement(int k) {
        /* Брой елементи в масива */
        int n = Array.length;
        int left, right;
        boolean useMax = k > n / 2;
        if (useMax) {
            k = n - k - 1;
        }

        left = n / 2;
        right = n - 1;

        /* Построяване на пирамидата */
        while (left > 0) {
            left--;
            if (useMax) {
                siftMax(left, right);
            } else {
                siftMin(left, right);
            }
        }

        /* (k-1)-кратно премахване на минималния елемент */
        for (right = n - 1; right >= n - k; right--) {
            Array[0] = Array[right];
            if (useMax) {
                siftMax(0, right);
            } else {
                siftMin(0, right);
            }
        }
    }

    /* Отсява елем. от върха на пирамидата */
    private static void siftMax(int left, int right) {
        int i = left;
        int j = i + i + 1;
        int x = Array[i];
        while (j <= right) {
            if (j < right) {
                if (Array[j] < Array[j + 1]) {
                    j++;
                }
            }

            if (x >= Array[j]) {
                break;
            }

            Array[i] = Array[j];
            i = j;
            j = (j * 2) + 1;
        }

        Array[i] = x;
    }

    /* Отсява елем. от върха на пирамидата */
    private static void siftMin(int left, int right) {
        int i = left;
        int j = i + i + 1;
        int x = Array[i];
        while (j <= right) {
            if (j < right) {
                if (Array[j] > Array[j + 1]) {
                    j++;
                }
            }

            if (x <= Array[j]) {
                break;
            }

            Array[i] = Array[j];
            i = j;
            j = (j * 2) + 1;
        }

        Array[i] = x;
    }
}
