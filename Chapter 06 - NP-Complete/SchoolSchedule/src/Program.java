public class Program {
    /* Максимален брой учители */
    private static final int MAX_TEACHERS = 100;
    /* Максимален брой класове */
    private static final int MAX_CLASSES = 100;
    /* Брой учители */
    private static final int TEACHERS_COUNT = 3;

    private static final int MAX_VALUE = 20000;

    /* Брой класове */
    private static final int C = 4;

    private static int[][] Cl = { { 5, 5, 5 },
    /* информация за клас 1 */{ 5, 5, 5 }, { 5, 0, 0 }, { 0, 0, 5 },
    /* информация за клас C */
    };

    private static byte[][] UsedC = new byte[100][MAX_CLASSES];
    private static int[] teach = new int[MAX_TEACHERS];
    private static int minimal;

    public static void main(String[] args) {
        int i, j;
        for (i = 0; i < 100; i++) {
            for (j = 0; j < C; j++) {
                UsedC[i][j] = 0;
            }
        }

        minimal = MAX_VALUE;
        generate(TEACHERS_COUNT, 0, 0, 0);
        System.out.printf(
                "Минималното разписание е с продължителност %d часа.\n",
                minimal);
    }

    private static void generate(int teacher, int level, int mX, int totalHours) {
        int i, j;
        if (totalHours >= minimal) {
            return;
        }

        if (teacher == TEACHERS_COUNT) {
            int min = MAX_VALUE;
            for (i = 0; i < C; i++) {
                for (j = 0; j < TEACHERS_COUNT; j++) {
                    if (Cl[i][j] < min && 0 != Cl[i][j]) {
                        min = Cl[i][j];
                    }
                }
            }

            if (min == MAX_VALUE) {
                if (totalHours < minimal) {
                    minimal = totalHours;
                }
            } else {
                generate(0, level + 1, min, totalHours + min);
            }

            return;
        }

        /* определя клас за учителя teacher, с който той да проведе min часа */
        for (i = 0; i < C; i++) {
            if (Cl[i][teacher] > 0 && UsedC[level][i] == 0) {
                Cl[i][teacher] -= mX;
                UsedC[level][i] = 1;
                generate(teacher + 1, level, mX, totalHours);
                UsedC[level][i] = 0; /* връщане */
                Cl[i][teacher] += mX;
            }
        }

        /*
         * Ако не е намерено присвояване за учителя, това означава, че не са му
         * останали часове за преподаване
         */
        if (i == C) {
            generate(teacher + 1, level, mX, totalHours);
        }
    }
}
