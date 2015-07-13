public class Program {
    private static final int VERTICES_COUNT = 6;
    private static final int START_VERTEX = 1; // Връх-източник
    private static final int END_VERTEX = 6; // Връх-консуматор

    private static int[][] Graph = { 
            { 0, 5, 5, 10, 0, 0 },
            { 0, 0, 4, 0, 0, 5 }, 
            { 0, 0, 0, 0, 7, 0 }, 
            { 0, 0, 0, 0, 0, 7 },
            { 0, 0, 0, 0, 0, 8 }, 
            { 0, 0, 0, 0, 0, 0 } 
    };

    private static int[][] FlowGraph = new int[VERTICES_COUNT][VERTICES_COUNT];
    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static int[] Path = new int[VERTICES_COUNT];
    private static boolean found = false;

    public static void main(String[] args) {
        // Намира увеличаващ път, докато е възможно
        do {
            for (int i = 0; i < VERTICES_COUNT; i++) {
                Used[i] = false;
            }

            found = false;
            Used[START_VERTEX - 1] = true;
            Path[0] = START_VERTEX - 1;
            findAugmentingPath(START_VERTEX - 1, 1);
        } while (found);

        System.out.println("Максимален поток през графа:");
        for (int i = 0; i < VERTICES_COUNT; i++) {
            for (int j = 0; j < VERTICES_COUNT; j++) {
                System.out.printf("%1$4d", FlowGraph[i][j]);
            }

            System.out.println();
        }

        int flow = 0;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            flow += FlowGraph[i][END_VERTEX - 1];
        }

        System.out.printf("С големина: %d\n", flow);
    }

    private static void findAugmentingPath(int vertex, int level) {
        if (found) {
            return;
        }

        if (vertex == END_VERTEX - 1) {
            found = true;
            updateFlow(level - 1);
        } else {
            for (int i = 0; i < VERTICES_COUNT; i++) {
                if (!Used[i] && Graph[vertex][i] > 0) {
                    Used[i] = true;
                    Path[level] = i;
                    findAugmentingPath(i, level + 1);
                    if (found) {
                        return;
                    }
                }
            }
        }
    }

    private static void updateFlow(int level) {
        int increasingFlow = Integer.MAX_VALUE;
        System.out.println("Намерен увеличаващ път: ");
        for (int i = 0; i < level; i++) {
            int p1 = Path[i];
            int p2 = Path[i + 1];
            System.out.printf("%d, ", p1 + 1);
            if (increasingFlow > Graph[p1][p2]) {
                increasingFlow = Graph[p1][p2];
            }
        }

        System.out.printf("%d \n", Path[level] + 1);

        for (int i = 0; i < level; i++) {
            int p1 = Path[i];
            int p2 = Path[i + 1];
            FlowGraph[p1][p2] += increasingFlow;
            FlowGraph[p2][p1] -= increasingFlow;
            Graph[p1][p2] -= increasingFlow;
            Graph[p2][p1] += increasingFlow;
        }
    }
}
