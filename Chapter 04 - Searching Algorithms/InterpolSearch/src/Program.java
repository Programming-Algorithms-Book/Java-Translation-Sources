import java.util.Random;

public class Program {
 // Максимална стойност на елементите от масива.
    private static final int MAX_VALUE = 50;
    private static final int NOT_FOUND = -1;

    // Генератор на произволни числа.
    private static Random Random = new Random();

    public static void performSearchTest(Element[] elements)
    {
        int index;
        for (int elementToSearch = 0; elementToSearch < 2 * MAX_VALUE; elementToSearch++)
        {
            System.out.printf("Търсим елемент с ключ %d.\n", elementToSearch);
            index = interpolSearch(elements, elementToSearch);
            if (index == -1)
            {
                System.out.println("Елемент с такъв ключ не съществува!");
            }
            else
            {
                System.out.printf("Елементът е намерен! Стойност на инф. част: %d.\n", index);
            }
        }
    }

    public static void main(String[] args)
    {
        // Инициализация на масив със записи.
        Element[] elements = new Element[MAX_VALUE];
        for (int index = 0; index < MAX_VALUE; index++)
        {
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
    private static void sortElementsArray(Element[] elements)
    {
        for (int i = 0; i < elements.length; i++)
        {
            for (int j = 0; j < elements.length; j++)
            {
                if (elements[i].getKey() < elements[j].getKey())
                {
                    Element swapElement = elements[i];
                    elements[i] = elements[j];
                    elements[j] = swapElement;
                }
            }
        }
    }

    private static int interpolSearch(Element[] elements, int keyToSearch)
    {
        int leftIndex = 0;
        int rightIndex = elements.length - 1;
        while (leftIndex <= rightIndex)
        {
            if (elements[rightIndex].getKey() == elements[leftIndex].getKey())
            {
                if (elements[leftIndex].getKey() == keyToSearch)
                {
                    return leftIndex;
                }
                else
                {
                    return NOT_FOUND;
                }
            }

            float interpolCoefficent = (float)(keyToSearch - elements[leftIndex].getKey())
                                       / (elements[rightIndex].getKey() - elements[leftIndex].getKey());
            if (interpolCoefficent < 0 || interpolCoefficent > 1)
            {
                return NOT_FOUND;
            }

            int midIndex = (int)(leftIndex + (interpolCoefficent * (rightIndex - leftIndex)) + 0.5);
            if (keyToSearch < elements[midIndex].getKey())
            {
                rightIndex = midIndex - 1;
            }
            else if (keyToSearch > elements[midIndex].getKey())
            {
                leftIndex = midIndex + 1;
            }
            else
            {
                return midIndex;
            }
        }

        return NOT_FOUND;
    }

    // Принтира елементите на масива върху конзолата.
    private static void printElements(Element[] elements)
    {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < elements.length; i++)
        {
            output.append(elements[i].toString());
            if (i < elements.length - 1)
            {
                output.append(",");
            }
        }

        System.out.println(output.toString());
    }
}
