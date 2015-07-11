import java.util.Random;

public class Program {
    // Максимална стойност на елементите от масива.
    private static final int MAX_VALUE = 50;
    private static final int NOT_FOUND = -1;

    // Генератор на произволни числа.
    private static Random Random = new Random();

    // Числата на Фибоначи, ненадвишаващи n
    private static int[] FibunacciNumbers = new int[MAX_VALUE];

    // Брой елементи в масива
    private static int n;

    public static void performSearchTest(Element[] elements) {
        for (int elementToSearch = 0; elementToSearch < 2 * MAX_VALUE; elementToSearch++) {
            System.out.printf("Търсим елемент с ключ %d.\n", elementToSearch);
            int index = doFibunacciSearch(elements, elementToSearch);
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
        // Брой елементи в масива.
        n = MAX_VALUE;

        // Инициализация на масив със записи.
        Element[] elements = new Element[MAX_VALUE];
        for (int index = 0; index < MAX_VALUE; index++) {
            int randomNumber = Random.nextInt(2 * MAX_VALUE);

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

    private static int doFibunacciSearch(Element[] elements, int keyToSearch) {
        int index = findFibNumber(n);
        int first = FibunacciNumbers[index - 3];
        int second = FibunacciNumbers[index - 2];
        int third = FibunacciNumbers[index - 1];

        if (keyToSearch > elements[index].getKey()) {
            third += n - FibunacciNumbers[index] + 1;
        }

        while (third > 0 && third < elements.length) {
            if (keyToSearch == elements[third].getKey()) {
                return third;
            }

            if (keyToSearch < elements[third].getKey()) {
                if (0 == first) {
                    third = 0;
                } else {
                    third -= first;
                    int exchangeVariable = second;
                    second = first;
                    first = exchangeVariable - first;
                }
            } else {
                if (1 == second) {
                    third = 0;
                } else {
                    third += first;
                    second -= first;
                    first -= second;
                }
            }
        }

        return NOT_FOUND;
    }

    private static int findFibNumber(int n) {
        FibunacciNumbers[0] = 0;
        FibunacciNumbers[1] = 1;
        int index = 2;
        while (true) {
            FibunacciNumbers[index] = FibunacciNumbers[index - 2]
                    + FibunacciNumbers[index - 1];
            if (FibunacciNumbers[index] > n) {
                return index - 1;
            }

            index++;
        }
    }

    // Принтира елементите на масива върху конзолата.
    private static void printElements(Element[] elements) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            output.append(elements[i]);
            if (i < elements.length - 1) {
                output.append(",");
            }
        }

        System.out.println(output.toString());
    }
}
