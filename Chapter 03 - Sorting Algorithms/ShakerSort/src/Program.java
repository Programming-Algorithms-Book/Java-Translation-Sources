import java.util.Random;

public class Program {
    private static final int MAX = 100;
    private static final int TEST_LOOP_COUNT = 100;

    public static void main(String[] args) {
        Element[] array = new Element[MAX];
        Element[] saveM = new Element[MAX];
        System.out.println("Start -- ");
        for (int loopInd = 1; loopInd <= TEST_LOOP_COUNT; loopInd++) {
            init(array);
            System.arraycopy(array, 0, saveM, 0, array.length);
            System.out.println("Масивът преди сортирането:");
            print(array);
            shakerSort(array);
            System.out.println("Масивът след сортирането:");
            print(array);
            check(array, saveM);
        }
    }

    /* Запълва масива със случайни цели числа */
    private static void init(Element[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(rand.nextInt(MAX) % array.length);
        }
    }

    private static void shakerSort(Element[] array) {
        int k = array.length;
        int r = array.length - 1;
        int l = 1;
        do {
            for (int j = r; j >= l; j--) {
                if (array[j - 1].getKey() > array[j].getKey()) {
                    Element oldValue = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = oldValue;
                    k = j;
                }
            }

            l = k + 1;
            for (int j = l; j <= r; j++) {
                if (array[j - 1].getKey() > array[j].getKey()) {
                    Element oldValue = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = oldValue;
                    k = j;
                }
            }

            r = k - 1;
        } while (l <= r);
    }

    /* Извежда ключовете на масива на екрана */
    private static void print(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%1$4d", array[i].getKey());
        }

        System.out.println();
    }

    private static void check(Element[] array, Element[] saveArray) {
        /* третира се като масив от булев тип */
        boolean[] found = new boolean[array.length + 1];

        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getKey() > array[i + 1].getKey()) {
                System.exit(0);
            }
        }

        /* 2. Проверка за пермутация на изходните елементи */
        int j;

        for (int i = 0; i < array.length; i++) {
            for (j = 0; j < array.length; j++) {
                if (!found[j] && array[i].getKey() == saveArray[j].getKey()) {
                    found[j] = true;
                    break;
                }
            }

            /* Пропада, ако не е намерен съответен */
            if (j >= array.length) {
                System.exit(0);
            }
        }
    }
}
