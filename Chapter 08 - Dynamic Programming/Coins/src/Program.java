public class Program {
    private static final int MAX_COINS = 100;
    private static final int MAX_SUM = 1000;
    private static final int SUM = 31; /* Сума, чието представяне минимизираме */
    private static final int N = 14; /* Общ брой налични монети */

    private static Element[] Sums = new Element[MAX_SUM];

    private static int[] Coins = new int[] /* Стойности на монетите */
    { 0, 5, 2, 2, 3, 2, 2, 2, 4, 3, 5, 8, 6, 7, 9 };

    public static void main(String[] args) {
        System.out.printf(
                "Как да получим сума от %d лева с минимален брой монети?\n",
                SUM);

        initElements(Sums);
        findMinSolution(SUM);
        printSolution(SUM);
    }

    /* Дали можем да използваме j-тата монета в i-тата сума? */
    private static boolean canJ(int i, int j) {
        int k = i - Coins[j];
        if (k > 0 && Sums[k].getNumber() < MAX_COINS) {
            while (k > 0) {
                if (Sums[k].getLast() == j) {
                    break; /* монета j участва в сумата */
                }

                k -= Coins[Sums[k].getLast()];
            }
        }

        return k == 0;
    }

    /* Намира представяне на сумата Sum с минимален брой монети */
    private static void findMinSolution(int sum) {
        Sums[0].setNumber(0);
        for (int i = 1; i <= sum; i++) {
            Sums[i].setNumber(MAX_COINS);
            for (int j = 1; j <= N; j++) {
                if (canJ(i, j)) {
                    if ((Sums[i - Coins[j]].getNumber() + 1) < Sums[i]
                            .getNumber()) {
                        Sums[i].setNumber(1 + Sums[i - Coins[j]].getNumber());
                        ;
                        Sums[i].setLast(j);
                    }
                }
            }
        }
    }

    /* Извежда намереното представяне */
    private static void printSolution(int sum) {
        if (Sums[sum].getNumber() == MAX_COINS) {
            System.out
                    .println("Сумата не може да се получи с наличните монети.");
        } else {
            System.out.printf("Минимален брой необходими монети: %d\n",
                    Sums[sum].getNumber());
            System.out.println("Ето и стойностите на самите монети: ");
            while (sum > 0) {
                System.out.printf("%d ", Coins[Sums[sum].getLast()]);
                sum -= Coins[Sums[sum].getLast()];
            }

            System.out.println();
        }
    }

    private static void initElements(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(0, 0);
        }
    }
}
