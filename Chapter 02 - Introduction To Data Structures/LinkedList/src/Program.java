import sun.plugin.dom.exception.InvalidStateException;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedListNode<Integer> first = list.addFirst(42);

        Random random = new Random();

        for (int i = 1; i < 6; i++) {
            int value = random.nextInt() % 10;
            System.out.println("Вмъкване преди: " + first.getValue() + " - стойност " + value);
            first = list.addBefore(first, value);
        }

        LinkedListNode<Integer> current = first;

        for (int i = 6; i < 10; i++) {
            int value = random.nextInt() % 10;
            System.out.println("Вмъкване след: " + current.getValue() + " - стойност " + value);
            current = list.addAfter(current, value);
        }

        System.out.println(list);

        // Изтриването на несъществуващ елемент ще доведе до грешка
        for (int i = 0; i < 5; i++) {
            try {
                int value = random.nextInt() % 10;
                System.out.println("Изтриване на елемент със стойност " + value);
                list.remove(value);
            } catch (InvalidStateException ise) {
                System.out.println(ise.getMessage());
            }

            System.out.println(list);
        }
    }
}
