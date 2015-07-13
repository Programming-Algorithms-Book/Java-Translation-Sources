public class Program {
    private static final int VERTICES_COUNT = 10;
    private static final int MAX_VALUE = 1000000;

    // Матрица на теглата на графа
    private static int[][] Graph = { 
            { 0, 23, 0, 0, 0, 0, 0, 8, 0, 0 },
            { 23, 0, 0, 3, 0, 0, 34, 0, 0, 0 },
            { 0, 0, 0, 6, 0, 0, 0, 25, 0, 7 },
            { 0, 3, 6, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 10, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 10, 0, 0, 0, 0, 0 },
            { 0, 34, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 8, 0, 25, 0, 0, 0, 0, 0, 0, 30 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 0, 7, 0, 0, 0, 0, 30, 0, 0 } 
    };

    public static void main(String[] args) {
        floyd();
        printMinimalPaths();
    }

    // Намира дължината на минималния път между всяка двойка върхове
    private static void floyd() {
        initializeGraph();

        // Алгоритъм на Флойд
        for (int k = 0; k < VERTICES_COUNT; k++) {
            for (int i = 0; i < VERTICES_COUNT; i++) {
                for (int j = 0; j < VERTICES_COUNT; j++) {
                    if (Graph[i][j] > Graph[i][k] + Graph[k][j]) {
                        Graph[i][j] = Graph[i][k] + Graph[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < VERTICES_COUNT; i++) {
            Graph[i][i] = 0;
        }
    }

    private static void initializeGraph() {
        // Стойностите 0 се променят на MaxValue
        for (int i = 0; i < VERTICES_COUNT; i++) {
            for (int j = 0; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] == 0) {
                    Graph[i][j] = MAX_VALUE;
                }
            }
        }
    }

    private static void printMinimalPaths() {
        System.out
                .println("Матрица на теглата след изпълнение на алгоритъма на Флойд:");
        for (int i = 0; i < VERTICES_COUNT; i++) {
            for (int j = 0; j < VERTICES_COUNT; j++) {
                System.out.printf("%1$3d ", Graph[i][j] == MAX_VALUE ? 0
                        : Graph[i][j]);
            }

            System.out.println();
        }
    }
}
