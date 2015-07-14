import java.util.Random;

public class Program {
    private static final int Max = 100;
    private static final int TestLoopCount = 100;
    private static final int Factor = 5;
    private static final int MaxValue = Max * Factor;

    private static Random Random = new Random();

    public static void main(String[] args) throws Exception
    {
        int[] elements = new int[Max];

        for (int i = 1; i <= TestLoopCount; i++)
        {
            System.out.printf("\n<<<<< Тест %d >>>>>\n", i);
            Init(elements);

            System.out.println("Масивът преди сортирането:");
            Print(elements);

            SortWithSet(elements);
            System.out.println("Масивът след сортирането:");
            Print(elements);

            Check(elements);
        }
    }

    private static void Init(int[] elements)
    {
        // 1. Запълване със случайни стойности в нарастващ ред
        elements[0] = Random.nextInt(Max) % Factor;
        for (int i = 1; i < elements.length; i++)
        {
            elements[i] = 1 + elements[i - 1] + (Random.nextInt(Max) % Factor);
        }

        // 2. Разменяме многократно произволни двойки елементи
        for (int i = 1; i < elements.length; i++)
        {
            // 2.1 Избиране на ва случайни индекса
            int index1 = Random.nextInt(Max) % elements.length;
            int index2 = Random.nextInt(Max) % elements.length;

            // 2.2 Разменяме ги
            int old = elements[index1];
            elements[index1] = elements[index2];
            elements[index2] = old;
        }
    }

    // Сортира масив с използване на множество
    private static void SortWithSet(int[] elements) throws Exception
    {
        // 0. Инициализиране на множество;
        boolean[] set = new boolean[MaxValue];

        // 1. Формиране на множеството
        for (int i = 0; i < elements.length; i++)
        {
            if (!(elements[i] >= 0 && elements[i] < MaxValue)) {
                throw new Exception("Element out of range");
            }
            
            if (set[elements[i]]) {
                throw new Exception("Invalid element");
            }
            set[elements[i]] = true;
        }

        // 2. Генериране на сортирана последователност
        int j = 0;
        for (int i = 0; i < MaxValue; i++)
        {
            if (set[i])
            {
                elements[j] = i;
                j++;
            }
        }

        if (j > elements.length) {
            throw new Exception("Out of range");
        }
    }

    private static void Print(int[] elements)
    {
        for (int i = 0; i < elements.length; i++)
        {
            System.out.printf("%d ", elements[i]);
        }

        System.out.println();
    }

    // TODO: Transfer to unit tests
    private static void Check(int[] elements) throws Exception
    {
        // 1. Проверка за наредба във възходящ ре
        for (int i = 0; i < elements.length - 1; i++)
        {
            if (elements[i] > elements[i + 1]) {
                throw new Exception("Wrong order");
            }
        }

        // 2. Проверка за пермутация на изходните елементи
        boolean[] found = new boolean[elements.length];

        for (int i = 0; i < elements.length; i++)
        {
            for (int j = 0; j < elements.length; j++)
            {
                if (!found[j] && elements[i] == elements[j])
                {
                    found[j] = true;
                    break;
                }

                // Пропада, ако не е намерен съответен
                if (j >= elements.length) {
                    throw new Exception("Element not found");
                }
            }
        }
    }
}
