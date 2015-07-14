import java.util.Random;

public class Program {
    private static final int MAX = 100;
    private static final int TEST_LOOPS_COUNT = 100;

    private static Random Rand = new Random();

    public static void main(String[] args) {
        Element[] array = new Element[MAX];
        Element[] arrayCopy = new Element[MAX];

        for (int loopInd = 1; loopInd <= TEST_LOOPS_COUNT; loopInd++) {
            System.out.printf("<<<<< Тест %d >>>>>\n", loopInd);
            Init(array, MAX);
            for (int p = 0; p < array.length; p++) {
                arrayCopy[p] = array[p];
            }

            System.out.println("Масивът преди сортирането:");
            Print(array, MAX);
            StraightInsertion(array, MAX);
            System.out.println("Масивът след сортирането:");
            Print(array, MAX);

            boolean testPassed = Check(array, arrayCopy, MAX);
            if (!testPassed) {
                System.out.println("Масивът не е сортиран.");
                return;
            }
        }
    }

    /* Запълва масива със случайни цели числа */
    private static void Init(Element[] array, int n) {
        for (int i = 0; i < n; i++) {
            array[i] = new Element(Rand.nextInt(MAX) % n);
        }
    }

    private static void StraightInsertion(Element[] array, int elementsCount) {
        int j;
        for (int i = 0; i < elementsCount; i++) {
            Element x = array[i];
            j = i - 1;
            while (j >= 0 && x.getKey() < array[j].getKey()) {
                array[j + 1] = array[j--];
            }

            array[j + 1] = x;
        }
    }

    private static void Print(Element[] array, int elementsCount) {
        for (int i = 0; i < elementsCount; i++) {
            System.out.printf("%d ", array[i].getKey());
        }

        System.out.println();
    }

    private static boolean Check(Element[] array, Element[] arrayCopy,
            int elementsCount) {
        boolean[] found = new boolean[elementsCount + 1];

        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 0; i < elementsCount - 1; i++) {
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

            if (j >= elementsCount) {
                return false; /* Пропада, ако не е намерен съответен */
            }
        }

        return true;
    }
}
