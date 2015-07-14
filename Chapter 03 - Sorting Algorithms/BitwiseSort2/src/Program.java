import java.util.Random;

public class Program {
    private static final int MAX_VALUE = 100;
    private static final int TESTS_COUNT = 100;
    private static Random Rand = new Random();

    public static void main(String[] args) throws Exception {
        Element[] elements = new Element[MAX_VALUE];
        for (int i = 0; i < TESTS_COUNT; i++) {
            System.out.println("----------Тест " + i + "----------");
            initialize(elements);
            System.out.println("Масив преди сортиране : ");
            printElements(elements);
            long bitMask = (long) Integer.MAX_VALUE + 1;
            bitwiseSort(elements, 0, MAX_VALUE - 1, bitMask);
            System.out.println("Масив след сортиране : ");
            printElements(elements);
            check(elements);
        }
    }

    private static void initialize(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(Rand.nextInt(MAX_VALUE * 2));
        }
    }

    private static void bitwiseSort(Element[] elements, int leftIndex,
            int rightIndex, long bitMask) {
        if (rightIndex > leftIndex && bitMask > 0) {
            int i = leftIndex;
            int j = rightIndex;
            while (j != i) {
                while ((elements[i].getKey() & bitMask) != bitMask && i < j) {
                    i++;
                }

                while ((elements[j].getKey() & bitMask) == bitMask && i < j) {
                    j--;
                }

                Element oldValue = elements[i];
                elements[i] = elements[j];
                elements[j] = oldValue;
            }

            if ((elements[rightIndex].getKey() & bitMask) != bitMask) {
                j++;
            }

            bitwiseSort(elements, leftIndex, j - 1, bitMask >> 1);
            bitwiseSort(elements, j, rightIndex, bitMask >> 1);
        }
    }

    private static void printElements(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i].getKey() + " ");
        }

        System.out.println();
    }

    private static void check(Element[] elements) throws Exception {
        boolean isSorted = true;
        for (int i = 0; i < elements.length - 1; i++) {
            if (elements[i].getKey() > elements[i + 1].getKey()) {
                isSorted = false;
                break;
            }
        }

        if (!isSorted) {
            throw new Exception("Масивът не е сортиран правилно.");
        }
    }
}
