import java.util.Random;

public class Program {
    private static final int MAX_VALUE = 100;
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        Element[] array = new Element[MAX_VALUE];
        Element[] saveArray = new Element[MAX_VALUE];
        initialize(array);
        System.arraycopy(array, 0, saveArray, 0, array.length);
        System.out.println("Масивът преди сортирането");
        print(array);
        countSort(array);
        System.out.println("Масивът след сортирането");
        print(array);

        check(array, saveArray);
    }

    private static void initialize(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(Math.abs(rand.nextInt()) % MAX_VALUE);
        }
    }

    private static void print(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i].getKey());
        }

        System.out.println();
    }

    private static void countSort(Element[] array) {
        List[] list = new List[MAX_VALUE];

        /* 1. Разпределяне на елементите по списъци */
        for (int i = 0; i < MAX_VALUE; i++) {
            list[i] = null;
        }

        /* 1.2. Добавяне на елемента в началото на списъка */
        List p;
        for (int i = 0; i < array.length; i++) {
            p = new List(array[i], list[array[i].getKey()]);
            list[array[i].getKey()] = p;
        }

        /* 2. Извеждане на ключовете на сортираната последователност */
        for (int i = 0, j = 0; i < MAX_VALUE; i++) {
            while ((p = list[i]) != null) {
                array[j++] = list[i].getData();
                list[i] = list[i].getNext();
            }
        }
    }

    private static void check(Element[] array, Element[] coppiedArray)
            throws Exception {
        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getKey() > array[i + 1].getKey()) {
                throw new Exception("Wrong order");
            }
        }

        /* 2. Проверка за пермутация на изходните елементи */
        boolean[] found = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            int j;
            for (j = 0; j < array.length; j++) {
                if (!found[j] && array[i] == coppiedArray[j]) {
                    found[j] = true;
                    break;
                }
            }

            /* Пропада, ако не е намерен съответен */
            if (j >= array.length) {
                throw new Exception("No element found");
            }
        }
    }
}
