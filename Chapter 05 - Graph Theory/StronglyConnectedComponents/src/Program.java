public class Program {
    private static final int VERTICES_COUNT = 10;

    private static byte[][] Graph = { 
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 }, 
            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 } 
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static int[] Postnum = new int[VERTICES_COUNT];
    private static int traversedVertices = 0;

    public static void main(String[] args) {
        System.out.println("Силно свързаните компоненти в графа са:");
        findStronglyConnectedComponents();
    }

    private static void findStronglyConnectedComponents() {
        while (traversedVertices < VERTICES_COUNT - 1) {
            for (int i = 0; i < VERTICES_COUNT; i++) {
                if (!Used[i]) {
                    dfs(i);
                }
            }
        }

        for (int i = 0; i < VERTICES_COUNT; i++) {
            Used[i] = false;
        }

        traversedVertices = 0;
        while (traversedVertices < VERTICES_COUNT - 1) {
            int max = -1;
            int maxVertex = -1;
            for (int i = 0; i < VERTICES_COUNT; i++) {
                if (!Used[i] && Postnum[i] > max) {
                    max = Postnum[i];
                    maxVertex = i;
                }
            }

            System.out.print("{ ");
            backDfs(maxVertex);
            System.out.println("}");
        }
    }

    // Обхождане в дълбочина със запазване на номерацията
    private static void dfs(int vertex) {
        Used[vertex] = true;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i] && Graph[vertex][i] == 1) {
                dfs(i);
            }
        }

        Postnum[vertex] = traversedVertices++;
    }

    // Обхождане в дълбочина на графа G'
    private static void backDfs(int vertex) {
        System.out.printf("%d ", vertex + 1);
        traversedVertices++;
        Used[vertex] = true;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i] && Graph[i][vertex] == 1) {
                backDfs(i);
            }
        }
    }
}
