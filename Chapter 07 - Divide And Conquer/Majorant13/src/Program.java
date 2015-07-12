
public class Program {
    private static final char DEFAULT_CHAR_VALUE = '\u0000';
    
    public static void main(String[] args)
    {
        char[] array = { 'A', 'D', 'A', 'B', 'A', 'B', 'A', 'A', 'B', 'A', 'B', 'A', 'C', };
        Character majority = findMajority(array);
        if (majority != null)
        {
            System.out.printf("Мажорант: %s\n", majority);
        }
        else
        {
            System.out.println("Няма мажорант.");
        }
    }

    private static Character findMajority(char[] array)
    {
        char majority = DEFAULT_CHAR_VALUE;
        int size = array.length;
        int counter = 0;
        for (int i = 0; i < size; i++)
        {
            if (counter == 0)
            {
                majority = array[i];
                counter = 1;
            }
            else if (array[i] == majority)
            {
                counter++;
            }
            else
            {
                counter--;
            }
        }

        if (counter > 0)
        {
            counter = 0;
            for (int i = 0; i < size; i++)
            {
                if (array[i] == majority)
                {
                    counter++;
                }
            }

            boolean isThereMajority = counter > size / 2;
            return isThereMajority ? majority : null;
        }

        return null;
    }
}
