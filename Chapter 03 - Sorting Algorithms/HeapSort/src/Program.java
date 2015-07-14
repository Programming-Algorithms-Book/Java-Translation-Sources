import java.util.Random;

public class Program {
    private static final int MAX_VALUE = 100;
    private static Random Rand = new Random();

    public static void main(String[] args) throws Exception {
        Element[] array = new Element[MAX_VALUE];
        Element[] saveArray = new Element[MAX_VALUE];
        System.arraycopy(array, 0, saveArray, 0, array.length);

        initialize(array);
        System.out.println("Масивът преди сортирането");
        print(array);
        heapSortArray(array);
        System.out.println("Масивът след сортирането");
        print(array);

        check(array, saveArray);
    }

    private static void initialize(Element[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            array[i] = new Element(Math.abs(Rand.nextInt()) % n);
        }
    }

    private static void Sift(Element[] array, int left, int right) {
        int i = left;
        int j = (i * 2) + 1;
        Element temp = array[i];
        while (j <= right) {
            if (j < right) {
                if (array[j].getKey() < array[j + 1].getKey()) {
                    j++;
                }
            }

            if (temp.getKey() < array[j].getKey()) {
                array[i] = array[j];
                i = j;
                j = (2 * i) + 1;
            } else {
                j = right + 1;
            }
        }

        array[i] = temp;
    }

    private static void heapSortArray(Element[] array) {
        int n = array.length;
        int k;
        /* 1. Построяване на пирамидата */
        for (k = (n - 1) / 2; k >= 0; k--) {
            Sift(array, k, n - 1);
        }

        /* 2. Построяване на сортирана последователност */
        for (k = n - 1; k >= 1; k--) {
            Element oldValue = array[0];
            array[0] = array[k];
            array[k] = oldValue;
            Sift(array, 0, k - 1);
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
                if (!found[j] && array[i] == coppiedArray[j]) {
                    found[j] = true;
                    break;
                }
            }

            if (j > array.length) {
                throw new Exception("No element found");
            }
        }
    }
}
