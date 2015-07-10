public class Program {
    /* Максимален брой предмети */
    private static final int MAX_OBJECTS = 100;

    /* Максимална стойност на отделен предмет */
    private static final int MAX_VALUE = 200;
    private static final int NOT_SET = -1;

    /* Общ брой на предметите за поделяне */
    private static final int N = 10;

    /* Кой предмет е бил добавен последен? */
    private static int[] LastAdded = new int[MAX_OBJECTS * MAX_VALUE];
    private static int[] ObjectValues = new int[] { 3, 2, 3, 2, 2, 77, 89, 23,
            90, 11 };

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        int totalSum; /* Обща стойност на предметите за поделяне */
        int currSum = 0;
        int i, j;

        /* Пресмятаме totalSum */
        for (totalSum = i = 0; i < N; totalSum += ObjectValues[i++]) {
        }

        /* Начално инициализиране */
        for (LastAdded[0] = 0, i = 1; i <= totalSum; i++) {
            LastAdded[i] = NOT_SET;
        }

        /* Намиране на всевъзможните суми от стойности на подаръците */
        for (i = 0; i < N; i++) {
            for (j = totalSum; j + 1 > 0; j--) {
                if (NOT_SET != LastAdded[j]
                        && NOT_SET == LastAdded[j + ObjectValues[i]]) {
                    LastAdded[j + ObjectValues[i]] = i;
                }
            }

            currSum += ObjectValues[i];
        }

        /* Търсим на най-близка до totalSum/2 стойност и извеждане на решение */
        for (i = totalSum / 2; i > 1; i--) {
            if (LastAdded[i] != NOT_SET) {
                System.out.printf("Сума за Алан: %d, сума за Боб: %d\n", i,
                        totalSum - i);
                System.out.println("Алан взема:");
                while (i > 0) {
                    System.out.printf("%d ", ObjectValues[LastAdded[i]]);
                    i -= ObjectValues[LastAdded[i]];
                }

                System.out.println("\nБоб взема останалите подаръци.");
                return;
            }
        }
    }
}
