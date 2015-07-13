public class Program {
    private static final int VERTICES_COUNT = 9;

    private static int[][] Graph = { 
            { 0, 1, 0, 2, 0, 0, 0, 0, 0 },
            { 1, 0, 3, 0, 13, 0, 0, 0, 0 }, 
            { 0, 3, 0, 4, 0, 3, 0, 0, 0 },
            { 2, 0, 4, 0, 0, 16, 14, 0, 0 }, 
            { 0, 13, 0, 0, 0, 12, 0, 1, 13 },
            { 0, 0, 3, 16, 12, 0, 1, 0, 1 }, 
            { 0, 0, 0, 14, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0 }, 
            { 0, 0, 0, 0, 13, 1, 0, 0, 0 } 
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static int[] Previous = new int[VERTICES_COUNT];
    private static int[] T = new int[VERTICES_COUNT];

    public static void main(String[] args) {
        FindMinSpanningTree();
    }

    private static void FindMinSpanningTree() {
        Used[0] = true; // Избираме произволен начален връх
        for (int i = 0; i < VERTICES_COUNT; i++) {
            T[i] = (Graph[0][i] != 0) ? Graph[0][i] : Integer.MAX_VALUE;
        }

        int minSpanningTree = 0;
        for (int k = 0; k < VERTICES_COUNT - 1; k++) {
            // Tърсене на следващо минимално ребро
            int minDistance = Integer.MAX_VALUE;
            int j = -1;
            for (int i = 0; i < VERTICES_COUNT; i++) {
                if (!Used[i] && T[i] < minDistance) {
                    minDistance = T[i];
                    j = i;
                }
            }

            Used[j] = true;
            System.out.printf("(%d, %d) ", Previous[j] + 1, j + 1);
            minSpanningTree += minDistance;
            for (int i = 0; i < VERTICES_COUNT; i++) {
                if (!Used[i] && Graph[j][i] != 0 && T[i] > Graph[j][i]) {
                    T[i] = Graph[j][i];

                    // Запазване на предшественика, за евентуално отпечатване на
                    // следващо минимално ребро
                    Previous[i] = j;
                }
            }
        }

        System.out.printf("\nЦената на минималното покриващо дърво е %d.\n",
                minSpanningTree);
    }
}
