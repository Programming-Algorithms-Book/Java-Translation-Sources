import java.util.Random;

public class Program {
    // Максимална стойност на елементите от масива.
    private static final int MAX_VALUE = 50;

    // Генератор на произволни числа.
    private static Random Random = new Random();
    private static Element head;

    public static void performSearchTest() {
        Element current = null;
        for (int elementToSearch = 0; elementToSearch < 2 * MAX_VALUE; elementToSearch++) {
            System.out.printf("Търсим елемент с ключ %d.\n", elementToSearch);
            current = listSearch(elementToSearch);
            if (current == null) {
                System.out.println("Елемент с такъв ключ не съществува!");
            } else {
                System.out.printf(
                        "Елементът е намерен! Стойност на инф. част: %d.\n",
                        current.getValue());
            }
        }
    }

    public static void main(String[] args) {
        Element previous = null;
        for (int index = 0; index < MAX_VALUE; index++) {
            // Пълнене на списъка с произволни числа.
            int randomNumber = Random.nextInt(2 * MAX_VALUE);
            Element element = new Element(randomNumber, index);
            if (index == 0) {
                head = element;
            } else {
                previous.setNext(element);
            }

            previous = element;
        }

        System.out.println("Списъкът съдържа следните елементи: ");
        printElements();

        System.out.println("\nТестване:");
        performSearchTest();
    }

    private static Element listSearch(int keyToSearch) {
        Element current = null;
        Element previous = head;
        if (previous == null) {
            return null;
        }

        if (previous.getKey() == keyToSearch) {
            return previous;
        }

        for (current = head.getNext(); current != null;) {
            if (current.getKey() != keyToSearch) {
                previous = current;
                current = current.getNext();
            } else {
                previous.setNext(current.getNext());
                current.setNext(head);
                head = current;
                return head;
            }
        }

        return null;
    }

    // Принтира елементите на масива върху конзолата.
    private static void printElements() {
        StringBuilder output = new StringBuilder();
        Element currentElement = head;
        while (currentElement != null) {
            output.append(currentElement.getValue());
            if (currentElement.getNext() != null) {
                output.append(",");
            }

            currentElement = currentElement.getNext();
        }

        System.out.println(output.toString());
    }
}
