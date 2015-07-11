import java.util.ArrayList;
import java.util.Random;

public class Program {
    // Максимална стойност на елементите от листа.
    private static final int MAX_VALUE = 50;
    private static final int NOT_FOUND = -1;

    // Генератор на произволни числа.
    private static Random Random = new Random();

    public static void performSearchTest(ArrayList<Element<Integer>> elements,
            int n) {
        for (int elementToSearch = 0; elementToSearch < 2 * MAX_VALUE; elementToSearch++) {
            System.out.printf("Търсим елемент с ключ %d.\n", elementToSearch);
            int index = binarySearch(elements, elementToSearch);
            if (index == NOT_FOUND) {
                System.out.println("Елемент с такъв ключ не съществува!");
            } else {
                System.out.printf(
                        "Елементът е намерен! Стойност на инф. част: %d.\n",
                        index);
            }
        }
    }

    public static void main(String[] args) {
        // Брой елементи в листа.
        int n = MAX_VALUE;

        // Инициализация на лист със записи.
        ArrayList<Element<Integer>> elements = new ArrayList<Element<Integer>>(
                n);
        for (int index = 0; index < n; index++) {
            int randomNumber = Random.nextInt(2 * MAX_VALUE);

            // Пълнене на лист с произволни числа.
            elements.add(new Element<Integer>(randomNumber, index));
        }

        sortElementsArray(elements);

        System.out.println("Списъкът съдържа следните елементи: ");
        printElements(elements);

        System.out.println("\nТестване:");
        performSearchTest(elements, n);
    }

    // Сортира елементите в листа.
    private static void sortElementsArray(ArrayList<Element<Integer>> elements) {
        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < elements.size(); j++) {
                if (elements.get(i).getKey() < elements.get(j).getKey()) {
                    Element<Integer> swapElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, swapElement);
                }
            }
        }
    }

    // Извърша двоично търсене.
    private static int binarySearch(ArrayList<Element<Integer>> elements,
            int elementToSearch) {
        int leftIndex = 0;
        int rightIndex = elements.size() - 1;
        int midIndex;
        int result = NOT_FOUND;
        while (leftIndex <= rightIndex) {
            midIndex = (leftIndex + rightIndex) / 2;
            if (elementToSearch < elements.get(midIndex).getKey()) {
                rightIndex = midIndex - 1;
            } else if (elementToSearch > elements.get(midIndex).getKey()) {
                leftIndex = midIndex + 1;
            } else {
                result = midIndex;
                break;
            }
        }

        return result;
    }

    // Принтира елементите на листа върху конзолата.
    private static void printElements(ArrayList<Element<Integer>> elements) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            output.append(elements.get(i).getValue());
            if (i < elements.size() - 1) {
                output.append(",");
            }
        }

        System.out.println(output.toString());
    }
}
