public class Program {
    /* Максимален брой предмети */
    private static final int MAX_OBJECTS = 100;

    /* Максимална стойност на отделен предмет */
    private static final int MAX_VALUE = 200;

    /* Общ брой на предметите за поделяне */
    private static final int N = 10;

    private static int[] ObjectValues = new int[] { 3, 2, 3, 2, 2, 77, 89, 23,
            90, 11 };

    /* Може ли да се получи сумата? */
    private static boolean[] Possible = new boolean[MAX_OBJECTS * MAX_VALUE];

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        int totalSum; /* Обща стойност на предметите за поделяне */
        int i, j;

        /* Пресмятаме totalSum */
        for (totalSum = i = 0; i < N; totalSum += ObjectValues[i++]) {
        }

        Possible[0] = true;

        /* Намиране на всевъзможните суми от стойности на подаръците */
        for (i = 0; i < N; i++) {
            for (j = totalSum; j + 1 > 0; j--) {
                if (Possible[j]) {
                    Possible[j + ObjectValues[i]] = true;
                }
            }
        }

        /* Намиране на най-близката до p/2 стойност */
        for (i = totalSum / 2; i > 1; i--) {
            if (Possible[i]) {
                System.out.printf("сума за Алан: %d, сума за Боб: %d\n", i,
                        totalSum - i);
                return;
            }
        }
    }
}
