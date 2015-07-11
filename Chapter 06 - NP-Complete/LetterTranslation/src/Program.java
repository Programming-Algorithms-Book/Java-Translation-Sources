public class Program {
    /* Максимален брой съответствия между букви */
    private static final int MAX_N = 40;
    /* Максимална дължина на дума за превод */
    private static final int MAX_TRANSLATIONS = 200;
    /* Брой съответствия */
    private static final int N = 38;

    private static TranslationType[] Transf = new TranslationType[MAX_N];

    private static int[] Translation = new int[MAX_TRANSLATIONS];

    /* Дума за превод */
    private static String str1 = "101001010";
    private static int pN;
    private static int total = 0;

    public static void main(String[] args)
    {
        System.out.println("Списък от всички възможни преводи: ");
        InitLanguage();
        pN = 0;
        nextLetter(0);
        System.out.printf("Общ брой различни преводи: %d \n", total);
    }

    /* В примера се използва Морзовата азбука: 0 е точка, а 1-та е тире */
    private static void InitLanguage()
    {
        Transf[0] = new TranslationType("А", "01");
        Transf[1] = new TranslationType("Б", "1000");
        Transf[2] = new TranslationType("В", "011");
        Transf[3] = new TranslationType("Г", "110");
        Transf[4] = new TranslationType("Д", "100");
        Transf[5] = new TranslationType("Е", "0");
        Transf[6] = new TranslationType("Ж", "0001");
        Transf[7] = new TranslationType("З", "1100");
        Transf[8] = new TranslationType("И", "00");
        Transf[9] = new TranslationType("Й", "0111");
        Transf[10] = new TranslationType("K", "101");
        Transf[11] = new TranslationType("Л", "0100");
        Transf[12] = new TranslationType("М", "11");
        Transf[13] = new TranslationType("Н", "10");
        Transf[14] = new TranslationType("О", "111");
        Transf[15] = new TranslationType("П", "0110");
        Transf[16] = new TranslationType("Р", "010");
        Transf[17] = new TranslationType("С", "000");
        Transf[18] = new TranslationType("Т", "1");
        Transf[19] = new TranslationType("У", "001");
        Transf[20] = new TranslationType("Ф", "0010");
        Transf[21] = new TranslationType("Х", "0000");
        Transf[22] = new TranslationType("Ц", "1010");
        Transf[23] = new TranslationType("Ч", "1110");
        Transf[24] = new TranslationType("Ш", "1111");
        Transf[25] = new TranslationType("Щ", "1101");
        Transf[26] = new TranslationType("Ю", "0011");
        Transf[27] = new TranslationType("Я", "0101");
        Transf[28] = new TranslationType("1", "01111");
        Transf[29] = new TranslationType("2", "00111");
        Transf[30] = new TranslationType("3", "00011");
        Transf[31] = new TranslationType("4", "00001");
        Transf[32] = new TranslationType("5", "00000");
        Transf[33] = new TranslationType("6", "10000");
        Transf[34] = new TranslationType("7", "11000");
        Transf[35] = new TranslationType("8", "11100");
        Transf[36] = new TranslationType("9", "11110");
        Transf[37] = new TranslationType("0", "11111");
    }

    /* Отпечатва превод */
    private static void printTranslation()
    {
        total++;
        for (int i = 0; i < pN; i++)
        {
            System.out.printf("%s", Transf[Translation[i]].getFirstString());
        }

        System.out.println();
    }

    /* Намира следваща буква */
    private static void nextLetter(int count)
    {
        if (count == str1.length())
        {
            printTranslation();
            return;
        }

        for (int k = 0; k < N; k++)
        {
            int len = (int)Transf[k].getSecondString().length();
            int i;
            for (i = 0; i < len; i++)
            {
                if ((int)i >= Transf[k].getSecondString().length()
                    || (int)(i + count) >= str1.length()
                    || str1.charAt((int)(i + count)) != Transf[k].getSecondString().charAt((int)i))
                {
                    break;
                }
            }

            if (i == len)
            {
                Translation[pN++] = k;
                nextLetter(count + Transf[k].getSecondString().length());
                pN--;
            }
        }
    }
}
