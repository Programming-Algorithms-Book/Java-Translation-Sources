public class Program {
    private static final int VERTICES_COUNT = 8;

    private static int[][] Graph = { 
            { 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 1, 0 }, 
            { 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 1, 0, 0, 0 }, 
            { 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 } 
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static int[] TopologicalSort = new int[VERTICES_COUNT];
    private static int totalSorts = 0;

    public static void main(String[] args) {
        fullTopologicalSort(0);
    }

    private static void fullTopologicalSort(int count) {
        int[] saved = new int[VERTICES_COUNT];
        if (count == VERTICES_COUNT) {
            printSort();
            return;
        }

        // Намира всички върхове без предшественик
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i]) {
                int j = 0;
                for (; j < VERTICES_COUNT; j++) {
                    if (Graph[j][i] != 0) {
                        break;
                    }
                }

                if (j == VERTICES_COUNT) {
                    for (int k = 0; k < VERTICES_COUNT; k++) {
                        saved[k] = Graph[i][k];
                        Graph[i][k] = 0;
                    }

                    Used[i] = true;
                    TopologicalSort[count] = i;
                    fullTopologicalSort(count + 1); // Рекурсия
                    Used[i] = false;
                    for (int k = 0; k < VERTICES_COUNT; k++) {
                        Graph[i][k] = saved[k];
                    }
                }
            }
        }
    }

    private static void printSort() {
        System.out.printf("Топологично сортиране номер %d: ", ++totalSorts);
        for (int i = 0; i < VERTICES_COUNT; i++) {
            System.out.printf("%d ", TopologicalSort[i] + 1);
        }

        System.out.println();
    }
}
