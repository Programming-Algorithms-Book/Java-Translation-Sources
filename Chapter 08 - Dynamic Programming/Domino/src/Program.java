public class Program {
    private static final int MAX = 100;

    /* Основа на бройната система */
    private static final int NUMERAL_SYSTEM_BASE = 10;
    private static final int SEQUENCE_ELEMENTS_COUNT = 17;

    /* Наследници за всеки връх */
    private static int[] Successors = new int[MAX];

    /* F[i]: текуща макс. дължина на подредица за i */
    private static int[] MaxLengths = new int[NUMERAL_SYSTEM_BASE];

    /* ind[i]: индекс на началото на редицата за i */
    private static int[] StartIndices = new int[NUMERAL_SYSTEM_BASE];

    private static int[] Sequence = new int[] { 0, 72, 121, 1445, 178, 123,
            3462, 762, 33434, 444, 472, 4, 272, 4657, 7243, 7326, 3432, 3465 }; /* Редица */

    public static void main(String[] args) {
        solve();
        print();
    }

    /* Намира максимална домино-редица */
    private static void solve() {
        int l, r;

        for (int i = 0; i < NUMERAL_SYSTEM_BASE; i++) {
            MaxLengths[i] = StartIndices[i] = 0;
        }

        /* Намиране дължините на редиците, започващи с цифрите от 0 до 9 */
        for (int i = SEQUENCE_ELEMENTS_COUNT; i > 0; i--) {
            /* Определяне на най-старшата и най-младшата цифри на числото */
            r = Sequence[i] % NUMERAL_SYSTEM_BASE;
            l = Sequence[i];

            while (l > NUMERAL_SYSTEM_BASE) {
                l /= NUMERAL_SYSTEM_BASE;
            }

            /* Актуализиране на редицата, започваща със старшата цифра */
            if (MaxLengths[r] >= MaxLengths[l]) {
                MaxLengths[l] = MaxLengths[r] + 1;
                Successors[i] = StartIndices[r];
                StartIndices[l] = i;
            }
        }
    }

    private static void print() {
        int i, bestIndex = 0;
        /* Определяне на най-дългата редица */
        /* Никое число не започва с 0 */
        for (i = 1; i < NUMERAL_SYSTEM_BASE; i++) {
            if (MaxLengths[i] > MaxLengths[bestIndex]) {
                bestIndex = i;
            }
        }

        /* Извеждане на редицата на екрана */
        System.out.printf("Дължина на максималната домино-подредица: %d\n",
                MaxLengths[bestIndex]);
        i = StartIndices[bestIndex];
        do {
            System.out.printf("%d ", Sequence[i]);
            i = Successors[i];
        } while (i > 0);

        System.out.println();
    }
}
