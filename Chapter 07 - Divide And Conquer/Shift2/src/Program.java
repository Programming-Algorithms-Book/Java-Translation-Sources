public class Program {
    /* Брой елементи в масива */
    private static final int N = 10;

    /* Брой позицции на отместване */
    private static final int K = 2;

    public static void main(String[] args) {
        Element[] elements = new Element[N];
        initializeArray(elements);
        shiftLeft(elements);
        printArray(elements);
    }

    private static void initializeArray(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(i);
        }
    }

    /* Разменя местата на подмасивите m[a..a+l-1] и m[b..b+l-1] */
    private static void swap(Element[] array, int a, int b, int l) {
        for (int i = 0; i < l; i++) {
            Element tempElement = array[a + i];
            array[a + i] = array[b + i];
            array[b + i] = tempElement;
        }
    }

    /*
     * Измества масива m[] на k позиции наляво. Рекурсивен процес, реализиран
     * итеративно
     */
    private static void shiftLeft(Element[] array) {
        int i = K;
        int p = K;
        int j = N - K;
        while (i != j) {
            if (i > j) {
                swap(array, p - i, p, j);
                i -= j;
            } else {
                swap(array, p - i, p + j - i, i);
                j -= i;
            }
        }

        swap(array, p - i, p, i);
    }

    private static void printArray(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i].getData());
        }

        System.out.println();
    }
}
