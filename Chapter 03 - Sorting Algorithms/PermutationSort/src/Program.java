import java.util.Random;

public class Program {
    private static final int MAX = 100;
    private static final int TEST_LOOP_COUNT = 100;

    private static Random Rand = new Random();

    public static void main(String[] args) {
        Element[] array = new Element[MAX];
        Element[] arrayCopy = new Element[MAX];

        for (int loopInd = 1; loopInd <= TEST_LOOP_COUNT; loopInd++) {
            System.out.printf("<<<<< Тест %d >>>>>\n", loopInd);
            init(array);
            for (int p = 0; p < array.length; p++) {
                arrayCopy[p] = array[p];
            }

            System.out.println("Масивът преди сортирането:");
            print(array);
            permSort(array);
            System.out.println("Масивът след сортирането:");
            print(array);

            boolean testPassed = check(array, arrayCopy);
            if (!testPassed) {
                System.out.println("Масивът не е сортиран.");
                return;
            }
        }
    }

    /* Запълва масива със случайни цели числа */
    private static void init(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(i);
        }

        for (int i = 0; i < array.length; i++) {
            int index = Rand.nextInt(MAX) % array.length;
            Element oldValue = array[i];
            array[i] = array[index];
            array[index] = oldValue;
        }
    }

    private static void permSort(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            while (array[i].getKey() != i) {
                Element oldValue = array[i];
                array[i] = array[array[i].getKey()];
                array[array[i].getKey()] = oldValue;
            }
        }
    }

    private static void print(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i].getKey());
        }

        System.out.println();
    }

    private static boolean check(Element[] array, Element[] arrayCopy) {
        boolean[] found = new boolean[array.length + 1];

        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getKey() > array[i + 1].getKey()) {
                return false;
            }
        }

        /* 2. Проверка за пермутация на изходните елементи */
        for (int i = 0; i < array.length; i++) {
            int j;
            for (j = 0; j < array.length; j++) {
                if (!found[j] && array[i].getKey() == arrayCopy[j].getKey()) {
                    found[j] = true;
                    break;
                }
            }

            if (j >= array.length) {
                return false; /* Пропада, ако не е намерен съответен */
            }
        }

        return true;
    }
}
