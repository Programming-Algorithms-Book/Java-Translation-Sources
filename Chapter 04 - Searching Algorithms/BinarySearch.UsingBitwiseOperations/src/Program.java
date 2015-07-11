import java.util.Random;

public class Program {
    // Максимална стойност на елементите от масива.
    private static final int MAX_VALUE = 50;
    private static final int NOT_FOUND = -1;

    // Генератор на произволни числа.
    private static Random Random = new Random();

    public static void main(String[] args) {
        // Брой елементи в масива.
        int n = MAX_VALUE;

        // Инициализация на масив със записи.
        Element[] elements = new Element[n];
        for (int index = 0; index < n; index++) {
            int randomNumber = Random.nextInt(2 * MAX_VALUE);

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

    private static int getMaxPower(int number) {
        int power = 1;
        while (power <= number) {
            power <<= 1;
        }

        power >>= 1;
        return power;
    }

    // Извърша двоично търсене.
    private static int binarySearch(Element[] elements, int elementToSearch) {
        int power = getMaxPower(elements.length);
        int leftIndex;
        if (elements[power].getKey() >= elementToSearch) {
            leftIndex = 0;
        } else {
            leftIndex = elements.length - power + 1;
        }

        int index;
        int result = NOT_FOUND;
        while (power > 0) {
            power >>= 1;
            index = leftIndex + power;
            if (index >= elements.length) {
                break;
            }

            if (elements[index].getKey() == elementToSearch) {
                result = index;
                break;
            } else if (elements[index].getKey() < elementToSearch) {
                leftIndex = index;
            }
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
