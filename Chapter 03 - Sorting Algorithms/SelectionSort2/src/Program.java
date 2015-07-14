import java.util.Random;

public class Program {
    private static final int MAX = 100;
    private static final int TEST_LOOP_COUNT = 100;

    private static Random Random = new Random();

    public static void main(String[] args) throws Exception {
        Element[] elements = new Element[MAX];

        for (int i = 1; i <= TEST_LOOP_COUNT; i++) {
            System.out.printf("\n<<<<< Тест %d >>>>>\n", i);
            init(elements);

            System.out.println("Масивът преди сортирането:");
            print(elements);
            straightSelection(elements);

            System.out.println("Масивът след сортирането:");
            print(elements);

            check(elements);
        }
    }

    // Запълва масива със случайни цели числа
    private static void init(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(Random.nextInt(MAX) % elements.length);
        }
    }

    private static void straightSelection(Element[] elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = i + 1; j < elements.length; j++) {
                if (elements[i].getKey() > elements[j].getKey()) {
                    Element old = elements[i];
                    elements[i] = elements[j];
                    elements[j] = old;
                }
            }
        }
    }

    // Извежда ключовете на масива на екрана
    private static void print(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            System.out.printf("%d ", elements[i].getKey());
        }

        System.out.println();
    }

    // TODO: Transfer to unit tests
    private static void check(Element[] elements) throws Exception {
        // 1. Проверка за наредба във възходящ ре
        for (int i = 0; i < elements.length - 1; i++) {
            if (elements[i].getKey() > elements[i + 1].getKey()) {
                throw new Exception("Wrong order");
            }
        }

        // 2. Проверка за пермутация на изходните елементи
        boolean[] found = new boolean[elements.length];

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                if (!found[j] && elements[i].getKey() == elements[j].getKey()) {
                    found[j] = true;
                    break;
                }

                // Пропада, ако не е намерен съответен
                if (j >= elements.length) {
                    throw new Exception("No element found");
                }
            }
        }
    }
}
