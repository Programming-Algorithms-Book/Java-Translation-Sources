public class Program {
    // Брой компании (върхове в графа)
    private static final int VERTICES_COUNT = 6;

    // Tърсим кои компании контролира компания 1
    private static final int MASTER_COMPANY = 1;

    private static int[][] Graph = { 
        { 0, 8, 40, 20, 55, 20 },
        { 0, 0, 0, 0, 0, 25 }, 
        { 0, 0, 0, 0, 0, 10 },
        { 0, 45, 0, 0, 0, 0 }, 
        { 0, 0, 0, 31, 0, 0 }, 
        { 0, 0, 0, 0, 0, 0 } 
     };

    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static int[] Control = new int[VERTICES_COUNT];

    public static void main(String[] args) {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            Control[i] = Graph[MASTER_COMPANY - 1][i];
        }

        for (int i = 0; i < VERTICES_COUNT; i++) {
            addControls();
        }

        printResult();
    }

    private static void addControls() {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            // Ако компания i е контролирана, прибавяме акциите ѝ към тези на
            // MasterCompany
            if (Control[i] > 50 && !Used[i]) {
                for (int j = 0; j < VERTICES_COUNT; j++) {
                    Control[j] += Graph[i][j];
                }

                Used[i] = true;
            }
        }
    }

    private static void printResult() {
        System.out.printf("Компания %d контролира следните компании:\n",
                MASTER_COMPANY);
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (Control[i] > 50) {
                System.out.printf("%1$d: %2$3d%%\n", i, Control[i]);
            }
        }
    }
}
