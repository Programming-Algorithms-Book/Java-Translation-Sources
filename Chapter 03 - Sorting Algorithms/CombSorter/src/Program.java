import java.util.Random;

public class Program {
    private static final int MAX_VALUE = 100;
    private static Random Rand = new Random();

    public static void main(String[] args) throws Exception {
        Element[] array = new Element[MAX_VALUE];
        Element[] saveArray = new Element[MAX_VALUE];
        initialize(array);

        /* Запазва се копие на масива */
        System.arraycopy(array, 0, saveArray, 0, array.length);
        System.out.println("Масивът преди сортирането");
        print(array);
        combSort(array);
        System.out.println("Масивът след сортирането");
        print(array);

        check(array, saveArray);
    }

    private static void initialize(Element[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            array[i] = new Element(Rand.nextInt() % n);
        }
    }

    private static void print(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i].getKey());
        }

        System.out.println();
    }

    private static void check(Element[] array, Element[] coppiedArray)
            throws Exception {
        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getKey() > array[i + 1].getKey()) {
                throw new Exception("Wrong order");
            }
        }

        /* 2. Проверка за пермутация на изходните елементи */
        boolean[] found = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            int j;
            for (j = 0; j < array.length; j++) {
                if (!found[j] && array[i].equals(coppiedArray[j])) {
                    found[j] = true;
                    break;
                }
            }

            /* Пропада, ако не е намерен съответен */
            if (j >= array.length) {
                throw new Exception("No element found");
            }
        }
    }

    private static void combSort(Element[] array) {
        int n = array.length;
        int gap = array.length, s;
        do {
            s = 0;
            gap = (int) (gap / 1.3);
            if (gap < 1) {
                gap = 1;
            }

            for (int i = 0; i < n - gap; i++) {
                int j = i + gap;
                if (array[i].getKey() > array[j].getKey()) {
                    Element oldValue = array[i];
                    array[i] = array[j];
                    array[j] = oldValue;
                    s++;
                }
            }
        } while (s != 0 || gap == 0);
    }
}
