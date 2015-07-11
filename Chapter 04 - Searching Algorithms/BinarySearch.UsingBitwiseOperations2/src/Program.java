import java.util.Random;

;

public class Program {
    // Максимална стойност на елементите от масива.
    private static final int MAX_VALUE = 50;
    private static final int NOT_FOUND = -1;

    // Генератор на произволни числа.
    private static Random Random = new Random();

    // Сортира елементите в масива.
    public static void sortElementsArray(Element[] elements) {
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

    public static void main(String[] args) {
        // Брой елементи в масива.
        int n = MAX_VALUE;

        // Инициализация на масив със записи.
        Element[] elements = new Element[n];
        for (int index = 0; index < n; index++) {
            int randomNumber = Random.nextInt(2 * MAX_VALUE);
            if (randomNumber == 99) {
                System.out.println("99");
            }

            // Пълнене на масива с произволни числа.
            elements[index] = new Element(randomNumber, index);
        }

        sortElementsArray(elements);

        System.out.println("Списъкът съдържа следните елементи: ");
        printElements(elements);

        System.out.println("\nТестване:");
        performSearchTest(elements, n);
    }

    private static int getMaxPower(int number) {
        int power = 1;
        while (power <= number) {
            power <<= 1;
        }

        power >>= 1;
        return power;
    }

    // Извърша двоично търсене.
    private static int binarySearch(Element[] elements, int keyToSearch) {
        int index = getMaxPower(elements.length);
        int leftIndex;
        if (elements[index].getKey() >= keyToSearch) {
            leftIndex = 0;
        } else {
            leftIndex = elements.length - index + 1;
        }

        while (index > 1) {
            index >>= 1;
            if (index + leftIndex < elements.length
                    && elements[leftIndex + index].getKey() < keyToSearch) {
                leftIndex += index;
            }
        }

        if (leftIndex + 1 < MAX_VALUE
                && elements[leftIndex + 1].getKey() == keyToSearch) {
            return leftIndex + 1;
        }

        return NOT_FOUND;
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
            index = binarySearch(elements, elementToSearch);
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
