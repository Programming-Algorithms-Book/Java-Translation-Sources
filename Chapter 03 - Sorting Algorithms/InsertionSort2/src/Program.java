import java.util.Random;

public class Program {
    private static final int MAX = 100;
    private static final int TEST_LOOP_COUNT = 100;

    private static Random Rand = new Random();

    public static void main(String[] args) {
        Element[] array = new Element[MAX + 1];
        Element[] arrayCopy = new Element[MAX + 1];

        for (int loopInd = 1; loopInd <= TEST_LOOP_COUNT; loopInd++) {
            System.out.printf("<<<<< Тест %d >>>>>\n", loopInd);
            init(array);
            for (int p = 0; p < array.length; p++) {
                arrayCopy[p] = array[p];
            }

            System.out.println("Масивът преди сортирането:");
            print(array);
            straightInsertion(array, MAX);
            System.out.println("Масивът след сортирането:");
            print(array);

            boolean testPassed = check(array, arrayCopy, MAX);
            if (!testPassed) {
                System.out.println("Масивът не е сортиран.");
                return;
            }
        }
    }

    /* Запълва масива със случайни цели числа */
    private static void init(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(Rand.nextInt(MAX) % array.length);
        }
    }

    private static void straightInsertion(Element[] array, int elementsCount) {
        Element x;
        int j;
        for (int i = 1; i <= elementsCount; i++) {
            x = array[i];
            array[0].setKey(x.getKey());
            for (j = i - 1; x.getKey() < array[j].getKey(); j--) {
                array[j + 1] = array[j];
            }

            array[j + 1] = x;
        }
    }

    private static void print(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i].getKey());
        }

        System.out.println();
    }

    private static boolean check(Element[] array, Element[] arrayCopy,
            int elementsCount) {
        boolean[] found = new boolean[elementsCount + 1];

        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 1; i < elementsCount; i++) {
            if (array[i].getKey() > array[i + 1].getKey()) {
                return false;
            }
        }

        /* 2. Проверка за пермутация на изходните елементи */
        for (int i = 0; i < elementsCount; i++) {
            int j;
            for (j = 0; j < elementsCount; j++) {
                if (!found[j] && array[i].getKey() == arrayCopy[j].getKey()) {
                    found[j] = true;
                    break;
                }
            }

            if (j > elementsCount) {
                return false; /* Пропада, ако не е намерен съответен */
            }
        }

        return true;
    }
}
