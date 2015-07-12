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

    /* Обръща подмасива m[a..b] */
    private static void reverse(Element[] array, int a, int b) {
        int count = (b - a) / 2;
        int k = a;
        int j = b;
        for (int i = 0; i <= count; i++, j--, k++) {
            Element temp = array[k];
            array[k] = array[j];
            array[j] = temp;
        }
    }

    private static void shiftLeft(Element[] array) {
        /* Измества масива m на k позиции наляво, на три стъпки */
        reverse(array, 0, K - 1);
        reverse(array, K, N - 1);
        reverse(array, 0, N - 1);
    }

    private static void printArray(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i].getData());
        }

        System.out.println();
    }
}
