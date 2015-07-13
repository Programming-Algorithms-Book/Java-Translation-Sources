public class Program {
    private static final int VERTICES_COUNT = 14;

    private static byte[][] Graph = {
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1 },
            { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 } 
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static boolean isCyclicGraph = false;

    public static void main(String[] args) {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i]) {
                dfs(i, -1);
            }

            if (isCyclicGraph) {
                return;
            }
        }

        System.out.println("Графът е дърво (не съдържа цикли)!");
    }

    // Модифициран Depth-First-Search
    private static void dfs(int vertex, int parent) {
        Used[vertex] = true;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (isCyclicGraph) {
                return;
            }

            if (Graph[vertex][i] == 1) {
                if (Used[i] && i != parent) {
                    System.out.println("Графът е цикличен!");
                    isCyclicGraph = true;
                    return;
                } else if (i != parent) {
                    dfs(i, vertex);
                }
            }
        }
    }
}
