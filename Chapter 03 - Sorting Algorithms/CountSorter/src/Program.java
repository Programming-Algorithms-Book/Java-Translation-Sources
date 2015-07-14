import java.util.Random;

public class Program {
    private static final int MAX_VALUE = 100;
    private static Random Rand = new Random();

    public static void main(String[] args) throws Exception {
        int[] array = new int[MAX_VALUE];
        initializeArray(array);
        int[] copiedArray = new int[MAX_VALUE];
        System.arraycopy(array, 0, copiedArray, 0, array.length); /*
                                                                   * Запазва се
                                                                   * копие на
                                                                   * масива
                                                                   */
        System.out.println("Масивът преди сортирането");
        printArray(array);
        countSort(array);
        System.out.println("Масивът след сортирането");
        printArray(array);

        check(array, copiedArray);
    }

    private static void initializeArray(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int randomNumber = Rand.nextInt(MAX_VALUE);
            array[i] = (randomNumber % n);
        }
    }

    private static void countSort(int[] array) {
        int[] counter = new int[MAX_VALUE];
        int n = array.length;

        /* 0. Инициализиране на множеството */
        for (int i = 0; i < MAX_VALUE; i++) {
            counter[i] = 0;
        }

        for (int j = 0; j < n; j++) {
            counter[array[j]]++;
        }

        for (int i = 0, j = 0; i < MAX_VALUE; i++) {
            while (counter[i]-- > 0) {
                array[j++] = i;
            }
        }
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i]);
        }

        System.out.println();
    }

    private static void check(int[] sortedArray, int[] coppiedArray) throws Exception {
        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 0; i < sortedArray.length - 1; i++) {
            if (sortedArray[i] > sortedArray[i + 1]) {
                throw new Exception("Wrong order");
            }
        }

        /* 2. Проверка за пермутация на изходните елементи */
        boolean[] found = new boolean[sortedArray.length];
        for (int i = 0; i < sortedArray.length; i++) {
            int j;
            for (j = 0; j < sortedArray.length; j++) {
                if (!found[j] && sortedArray[i] == coppiedArray[j]) {
                    found[j] = true;
                    break;
                }
            }

            /* Пропада, ако не е намерен съответен */
            if (j >= sortedArray.length) {
                throw new Exception("No element found");
            }
        }
    }
}
