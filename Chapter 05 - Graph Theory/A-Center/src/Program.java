public class Program {
    private static final int VERTICES_COUNT = 6;
    private static final int MAX_VALUE = 1000000;

    private static int[][] Graph = { 
        { 0, 1, 0, 0, 0, 0 },
        { 0, 0, 1, 0, 1, 0 }, 
        { 0, 0, 0, 1, 0, 0 }, 
        { 0, 0, 0, 0, 1, 0 },
        { 0, 1, 0, 0, 0, 1 }, 
        { 1, 0, 0, 0, 0, 0 } 
    };

    public static void main(String[] args) {
        floyd();
        findCenter();
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

    private static void findCenter() {
        int center = 0;
        int min = MAX_VALUE;
        int max = Integer.MAX_VALUE;

        /*
         * Sot(Xi) = max { Vj [d(Xi, Xj) + d[Xj, Xi])] }, центърът е върхът X*,
         * за който Sot(X*) е минимално
         */
        for (int i = 0; i < VERTICES_COUNT; i++) {
            max = Graph[i][0] + Graph[0][i];
            for (int j = 0; j < VERTICES_COUNT; j++) {
                if ((i != j) && (Graph[i][j] + Graph[j][i]) > max) {
                    max = Graph[i][j] + Graph[j][i];
                }
            }

            if (max < min) {
                min = max;
                center = i;
            }
        }

        System.out.printf("Центърът на графа е връх %d\n", center + 1);
        System.out.printf("Радиусът на графа е %d\n", min);
    }
}
