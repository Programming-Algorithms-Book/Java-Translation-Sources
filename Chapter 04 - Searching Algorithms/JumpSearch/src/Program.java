import java.util.Random;

public class Program {
    // Максимална стойност на елементите от масива.
    private static final int MAX_VALUE = 50;
    private static final int NOT_FOUND = -1;
    private static final int STEP = 10;

    // Генератор на произволни числа.
    private static Random random = new Random();

    public static void performSearchTest(Element[] elements) {
        for (int elementToSearch = 0; elementToSearch < 2 * MAX_VALUE; elementToSearch++) {
            System.out.printf("Търсим елемент с ключ %d.\n", elementToSearch);
            int index = jumpSearch(elements, elementToSearch, STEP);
            if (index == -1) {
                System.out.println("Елемент с такъв ключ не съществува!");
            } else {
                System.out.printf(
                        "Елементът е намерен! Стойност на инф. част: %d.\n",
                        index);
            }
        }
    }

    public static void main(String[] args) {
        // Инициализация на масив със записи.
        Element[] elements = new Element[MAX_VALUE];
        for (int index = 0; index < MAX_VALUE; index++) {
            int randomNumber = random.nextInt(2 * MAX_VALUE);

            // Пълнене на масива с произволни числа.
            elements[index] = new Element(randomNumber, index);
        }

        sortElementsArray(elements);

        System.out.println("Списъкът съдържа следните елементи: ");
        printElements(elements);

        System.out.println("\nТестване:");
        performSearchTest(elements);
    }

    // Сортира елементите в масива.
    private static void sortElementsArray(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                if (elements[i].getKey() < elements[j].getKey()) {
                    Element swapElement = elements[i];
                    elements[i] = elements[j];
                    elements[j] = swapElement;
                }
            }
        }
    }

    private static int jumpSearch(Element[] elements, int keyToSearch, int step) {
        int i;
        for (i = 0; i < elements.length && elements[i].getKey() < keyToSearch; i += step) {
        }

        int leftIndex = (i + 1 < step) ? 0 : (i + 1 - step);
        int rightIndex = (elements.length < i) ? (int) elements.length : i;
        return sequentSearch(elements, leftIndex, rightIndex, keyToSearch);
    }

    private static int sequentSearch(Element[] elements, int leftIndex,
            int rightIndex, int keyToSearch) {
        while (leftIndex < rightIndex) {
            if (elements[leftIndex].getKey() == keyToSearch) {
                return leftIndex;
            }

            leftIndex++;
        }

        return NOT_FOUND;
    }

    // Принтира елементите на масива върху конзолата.
    private static void printElements(Element[] elements) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            output.append(elements[i].toString());
            if (i < elements.length - 1) {
                output.append(",");
            }
        }

        System.out.println(output.toString());
    }
}
