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
            bubbleSort(elements);
            System.out.println("Масив след сортиране : ");
            printElements(elements);
            check(elements);
        }
    }

    private static void initialize(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element();
            elements[i].setKey(Rand.nextInt(MAX_VALUE * 2));
        }
    }

    private static void bubbleSort(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            for (int j = elements.length - 1; j > i; j--) {
                if (elements[j - 1].getKey() > elements[j].getKey()) {
                    Element oldValue = elements[j - 1];
                    elements[j - 1] = elements[j];
                    elements[j] = oldValue;
                }
            }
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
            throw new Exception("Масива не е сортиран правилно.");
        }
    }
}
