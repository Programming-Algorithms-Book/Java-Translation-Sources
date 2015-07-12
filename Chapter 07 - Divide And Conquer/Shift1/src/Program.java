public class Program {
    /* Брой елементи в масива */
    private static final int N = 10;

    /* Брой позиции на отместване */
    private static final int K = 2;

    public static void main(String[] args) {
        Element[] elements = new Element[N];
        initializeArray(elements);
        printArray(elements);
        shiftLeft(elements);
        printArray(elements);
    }

    private static void initializeArray(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(i);
        }
    }

    private static int greatestCommonDivisor(int x, int y) {
        while (y > 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }

        return x;
    }

    private static void shiftLeft(Element[] array) {
        int greatestCommonDivisor = greatestCommonDivisor(N, K);
        for (int i = 0; i < greatestCommonDivisor; i++) {
            int currentIndex = i;
            Element tempElement = array[i];
            int nextIndex = currentIndex + K;
            if (nextIndex >= N) {
                nextIndex -= N;
            }

            while (nextIndex != i) {
                array[currentIndex] = array[nextIndex];
                currentIndex = nextIndex;
                nextIndex += K;
                if (nextIndex >= N) {
                    nextIndex -= N;
                }
            }

            array[currentIndex] = tempElement;
        }
    }

    private static void printArray(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i].getData());
        }

        System.out.println();
    }
}
