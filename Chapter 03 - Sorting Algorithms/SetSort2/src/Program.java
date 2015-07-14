import java.util.Random;

public class Program {
    private static final int Max = 100;
    private static final int Factor = 5;
    private static final int MaxValue = Max * Factor;
    private static final int NoIndex = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Element[] m = new Element[Max];
        init(m);
        System.out.println("Масивът преди сортирането:");
        print(m);
        System.out.println("Масивът след сортирането:");
        setSort(m);
        System.out.println();
    }

    /* Запълва масива със случайни цели числа */
    private static void init(Element[] array) {
        /* 1. Запълване със случайни стойности в нарастващ ред */
        Random rand = new Random();
        array[0] = new Element(rand.nextInt(Max) % Factor);
        for (int i = 1; i < array.length; i++) {
            array[i] = new Element(1 + array[i - 1].getKey()
                    + (rand.nextInt(Max) % Factor));
        }

        /* 2. Разменяме елементи */
        for (int i = 1; i < array.length; i++) {
            /* 2.1. Избиране на два случайни индекса */
            int index1 = rand.nextInt(Max) % array.length;
            int index2 = rand.nextInt(Max) % array.length;

            /* 2.2. Разменяме ги */
            Element tmp = array[index1];
            array[index1] = array[index2];
            array[index2] = tmp;
        }
    }

    /* Извежда ключовете на масива на екрана */
    private static void print(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%1$4d", array[i].getKey());
        }

        System.out.println();
    }

    private static void do4Elem(Element e) {
        System.out.printf("%1$4d", e.getKey());
    }

    /* Сортира масив с използване на множество */
    private static void setSort(Element[] array) {
        int[] indexSet = new int[MaxValue]; /* Индексно множество */

        /* 0. Инициализиране на множеството */
        for (int i = 0; i < MaxValue; i++) {
            indexSet[i] = NoIndex;
        }

        /* 1. Формиране на множеството */
        for (int j = 0; j < array.length; j++) {
            if (!(array[j].getKey() >= 0 && array[j].getKey() < MaxValue)
                    || !(NoIndex == indexSet[array[j].getKey()])) {
                System.exit(0);
            }

            indexSet[array[j].getKey()] = j;
        }

        /* 2. Генериране на сортирана последователност */
        for (int i = 0; i < MaxValue; i++) {
            if (NoIndex != indexSet[i]) {
                do4Elem(array[indexSet[i]]);
            }
        }
    }
}
