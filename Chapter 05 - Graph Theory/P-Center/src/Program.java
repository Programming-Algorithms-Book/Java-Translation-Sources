public class Program {
    private static final int MAX_VALUE = 1000000;
    private static final int VERTICES_COUNT = 7;
    private static final int P = 3; // P-център

    // Матрица на теглата на графа
    private static int[][] Graph = { 
            { 0, 1, 0, 0, 2, 0, 0 },
            { 1, 0, 4, 0, 0, 0, 0 }, 
            { 0, 4, 0, 3, 0, 5, 0 },
            { 0, 0, 3, 0, 4, 0, 8 }, 
            { 2, 0, 0, 4, 0, 0, 0 },
            { 0, 0, 5, 0, 0, 0, 2 }, 
            { 0, 0, 0, 8, 0, 2, 0 } 
    };

    private static int[] Center = new int[VERTICES_COUNT];
    private static int[] CenterP = new int[P];
    private static int radiusP = 0;

    public static void main(String[] args) {
        floyd();
        radiusP = MAX_VALUE;
        generateCombinations(0, 0);
        printPCenter();
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

    // Изчисляваме S за текущо генерираното подмножество
    private static void findPCenter() {
        int radiusC = 0;
        for (int j = 0; j < VERTICES_COUNT; j++) {
            int bT = MAX_VALUE;
            for (int i = 0; i < P; i++) {
                if (Graph[Center[i]][j] < bT) {
                    bT = Graph[Center[i]][j];
                }
            }

            if (radiusC < bT) {
                radiusC = bT;
            }
        }

        if (radiusC < radiusP) {
            radiusP = radiusC;
            for (int i = 0; i < P; i++) {
                CenterP[i] = Center[i];
            }
        }
    }

    // Kомбинации C(n,p) – генериране на всички p-елементни подмножества на G
    private static void generateCombinations(int k, int last) {
        for (int i = last; i < VERTICES_COUNT - P + k; i++) {
            Center[k] = i;
            if (k == P) {
                findPCenter();
            } else {
                generateCombinations(k + 1, i + 1);
            }
        }
    }

    // Печатаме p-центъра и p-радиуса
    private static void printPCenter() {
        System.out.printf(
                "%d-центърът в графа е следното множество от върхове: ( ", P);
        for (int i = 0; i < P; i++) {
            System.out.printf("%d ", CenterP[i] + 1);
        }

        System.out.println(")");
        System.out.printf("%d-радиусът в графа е %d\n", P, radiusP);
    }
}
