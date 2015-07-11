import java.util.Random;

public class Program {
    // Максимална стойност на елементите от масива.
    private static final int MAX_VALUE = 100;
    private static final int NOT_FOUND = -1;

    // Генератор на произволни числа.
    private static Random random = new Random();

    public static void main(String[] args) {
        // Брой елементи в масива.
        int n = MAX_VALUE;

        // Инициализация на масив със записи.
        Element[] elements = new Element[n];
        for (int index = 0; index < n; index++) {
            int randomNumber = random.nextInt(2 * MAX_VALUE);

            // Пълнене на масива с произволни числа.
            elements[index] = new Element(randomNumber, index);
        }

        sortElementsArray(elements);

        System.out.println("Списъкът съдържа следните елементи: ");
        printElements(elements);

        System.out.println("\nТестване:");
        performSearchTest(elements, n);
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

    // Извърша двоично търсене.
    private static int binarySearch(Element[] elements, int elementToSearch,
            int leftIndex, int rightIndex) {
        int midIndex;
        int result;
        if (leftIndex > rightIndex) {
            result = NOT_FOUND;
            return result;
        }

        midIndex = (leftIndex + rightIndex) / 2;
        if (elementToSearch < elements[midIndex].getKey()) {
            result = binarySearch(elements, elementToSearch, leftIndex,
                    midIndex - 1);
        } else if (elementToSearch > elements[midIndex].getKey()) {
            result = binarySearch(elements, elementToSearch, midIndex + 1,
                    rightIndex);
        } else {
            result = midIndex;
        }

        return result;
    }

    // Принтира елементите на масива върху конзолата.
    private static void printElements(Element[] elements) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            output.append(elements[i].getValue());
            if (i < elements.length - 1) {
                output.append(",");
            }
        }

        System.out.println(output.toString());
    }

    private static void performSearchTest(Element[] elements, int n) {
        int index;
        for (int elementToSearch = 0; elementToSearch < 2 * MAX_VALUE; elementToSearch++) {
            System.out.printf("Търсим елемент с ключ %d.\n", elementToSearch);
            index = binarySearch(elements, elementToSearch, 0, n - 1);
            if (index == NOT_FOUND) {
                System.out.println("Елемент с такъв ключ не съществува!");
            } else {
                System.out.printf(
                        "Елементът е намерен! Стойност на инф. част: %d.\n",
                        index);
            }
        }
    }
}
