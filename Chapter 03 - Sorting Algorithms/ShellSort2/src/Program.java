import java.util.Random;


public class Program {
    private static final int MAX = 100;
    private static final int TESTS_COUNT = 100;

    public static void main(String[] args)
    {
        Element[] m = new Element[MAX + 1];
        Element[] saveM = new Element[MAX + 1];
        System.out.println("start -- ");
        for (int loopInd = 1; loopInd <= TESTS_COUNT; loopInd++)
        {
            init(m, MAX);
            System.arraycopy(m, 0, saveM, 0, m.length);
            System.out.println("Масивът преди сортирането:");
            print(m, MAX);
            shellSort(m, 1, MAX);
            System.out.println("Масивът след сортирането:");
            print(m, MAX);
            check(m, saveM, MAX);
        }
    }

    /* Запълва масива със случайни цели числа */
    private static void init(Element[] m, int n)
    {
        int i;
        Random rand = new Random();
        for (i = 1; i <= n; i++)
        {
            m[i] = new Element(rand.nextInt() % n);
        }
    }

    private static void shellSort(Element[] m, int l, int r)
    {
        int[] incs =
        {
            1391376, 463792, 198768, 86961, 33936,
            13776, 4592, 1968, 861, 336, 112, 48,
            21, 7, 3, 1
        };

        for (int k = 0; k < 16; k++)
        {
            for (int h = incs[k], i = l + h; i <= r; i++)
            {
                Element v = m[i];
                int j = i;
                while (j > h && m[j - h].getKey() > v.getKey())
                {
                    m[j] = m[j - h];
                    j -= h;
                }

                m[j] = v;
            }
        }
    }

    /* Извежда ключовете на масива на екрана */
    private static void print(Element[] m, int n)
    {
        for (int i = 1; i <= n; i++)
        {
            System.out.printf("%d, ", m[i].getKey());
        }

        System.out.println();
    }

    private static void check(Element[] m, Element[] saveM, int n)
    {
        int j;
        
        /* третира се като масив от булев тип */
        boolean[] found = new boolean[n + 1];
        /* 1. Проверка за наредба във възходящ ред */
        for (int i = 1; i < n; i++)
        {
            if (m[i].getKey() > m[i + 1].getKey())
            {
                System.exit(0);
            }
        }

        /* 2. Проверка за пермутация на изходните елементи */
        for (int i = 1; i <= n; i++)
        {
            for (j = 1; j <= n; j++)
            {
                if (found[j] == false && m[i].getKey() == saveM[j].getKey())
                {
                    found[j] = true;
                    break;
                }
            }

            if (j > n)
            {
                System.exit(0);
            }
        }
    }
}
