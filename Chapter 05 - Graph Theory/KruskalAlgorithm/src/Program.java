import java.util.Arrays;

public class Program {
    private static final int NO_PARENT = -1;
    private static final int VERTICES_COUNT = 9;
    private static final int EDGES_COUNT = 13;

    // Списък от ребрата на графа и техните тегла
    private static Arc[] Graph = { new Arc(1, 2, 1), new Arc(1, 4, 2),
            new Arc(2, 3, 3), new Arc(2, 5, 13), new Arc(3, 4, 4),
            new Arc(3, 6, 3), new Arc(4, 6, 16), new Arc(4, 7, 14),
            new Arc(5, 6, 12), new Arc(5, 8, 1), new Arc(5, 9, 13),
            new Arc(6, 7, 1), new Arc(6, 9, 1) };

    private static int[] Previous = new int[VERTICES_COUNT + 1];

    public static void main(String[] args) {
        for (int i = 0; i < VERTICES_COUNT + 1; i++) {
            Previous[i] = NO_PARENT;
        }

        FindMinSpanningTree();
    }

    private static void FindMinSpanningTree() {
        Arrays.sort(Graph);

        System.out.println("Ето ребрата, които участват в минималното покриващо дърво:");
        int minSpanningTree = 0;
        for (int i = 0; i < EDGES_COUNT; i++) {
            int vertex1 = GetRoot(Graph[i].getVertex1());
            int vertex2 = GetRoot(Graph[i].getVertex2());
            if (vertex1 != vertex2) {
                System.out.printf("(%d, %d) ", Graph[i].getVertex1(),
                        Graph[i].getVertex2());
                minSpanningTree += Graph[i].getWeight();
                Previous[vertex2] = vertex1;
            }
        }

        System.out.printf("\nЦената на минималното покриващо дърво е %d.\n",
                minSpanningTree);
    }

    private static int GetRoot(int vertex) {
        // Намиране на корена на дървото
        int root = vertex;
        while (Previous[root] != NO_PARENT) {
            root = Previous[root];
        }

        // Свиване на пътя
        int saveVertex = 0;
        while (vertex != root) {
            saveVertex = vertex;
            vertex = Previous[vertex];
            Previous[saveVertex] = root;
        }

        return root;
    }
}
