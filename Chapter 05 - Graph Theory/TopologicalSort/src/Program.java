public class Program {
    private static final int VERTICES_COUNT = 5;

    private static byte[][] Graph = { 
            { 0, 1, 0, 0, 0 }, 
            { 0, 0, 1, 0, 1 },
            { 0, 0, 0, 1, 0 },
            { 0, 0, 0, 0, 0 }, 
            { 0, 0, 1, 0, 0 }
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];

    public static void main(String[] args) {
        System.out.println("Топологично сортиране (в обратен ред): ");
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i]) {
                topologicalSort(i);
            }
        }

        System.out.println();
    }

    private static void topologicalSort(int vertex) {
        Used[vertex] = true;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (Graph[vertex][i] == 1 && !Used[i]) {
                topologicalSort(i);
            }
        }

        System.out.printf("%d ", vertex + 1);
    }
}
