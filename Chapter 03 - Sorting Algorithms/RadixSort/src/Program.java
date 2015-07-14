import java.util.Random;

public class Program {
    private static final int MAX = 100;

    // Броя битове - дължината на нашата група (Степен на двойката)
    private static final int POW2 = 4;

    // Броя битове на int в C#
    private static final int BITS_COUNT = 32;

    private static Random Random = new Random();

    public static void main(String[] args) throws Exception {
        Element[] elements = init(MAX);
        System.out.println("Масивът преди сортирането:");
        print(elements);

        elements = sortRadix(elements);
        System.out.println("Масивът след сортирането:");
        print(elements);

        check(elements);
    }

    private static Element[] init(int n) {
        Element[] elements = new Element[n];

        for (int i = 0; i < n; i++) {
            elements[i] = new Element(Random.nextInt(MAX));
        }

        return elements;
    }

    private static Element[] sortRadix(Element[] elements) {
        // Допълнителен помощен масив
        Element[] helper = new Element[elements.length];

        // Масив брояч и масив с префикси
        int[] count = new int[1 << POW2];
        int[] prefixes = new int[1 << POW2];

        // Брой групи
        int groups = (int) Math.ceil((double) BITS_COUNT / (double) POW2);

        // Битова маска за идентифициране на групи
        int mask = (1 << POW2) - 1;

        // Алгоритъмът
        for (int c = 0, shiftRight = 0; c < groups; c++, shiftRight += POW2) {
            // Нулиране на масива брояч
            for (int j = 0; j < count.length; j++) {
                count[j] = 0;
            }

            // Изброяване на елементите в C-тата група
            for (int i = 0; i < elements.length; i++) {
                count[(elements[i].getKey() >> shiftRight) & mask]++;
            }

            // Изчисляване на префиксите
            prefixes[0] = 0;
            for (int i = 1; i < count.length; i++) {
                prefixes[i] = prefixes[i - 1] + count[i - 1];
            }

            // Прехвърляне на елементите от основния в
            // спомагателния масив подредени по C-тата група
            for (int i = 0; i < elements.length; i++) {
                helper[prefixes[(elements[i].getKey() >> shiftRight) & mask]++] = elements[i];
            }

            // Копиране на спомагателния масив в основния
            // и започване отначало до последната група
            System.arraycopy(helper, 0, elements, 0, helper.length);
        }

        // Масивът е сортиран
        return elements;
    }

    private static void print(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            System.out.printf("%d ", elements[i].getKey());
        }

        System.out.println();
    }

    // TODO: Transfer to unit tests
    private static void check(Element[] elements) throws Exception {
        // 1. Проверка за наредба във възходящ ред
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
