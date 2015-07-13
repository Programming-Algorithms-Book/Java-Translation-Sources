import java.util.HashSet;

public class Program {
    private static final int VERTICES_COUNT = 10;
    private static final int START_VERTEX = 1;
    private static final int NO_PARENT = -1;
    private static final int MAX_VALUE = 1000000;

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

    private static HashSet<Integer> T = new HashSet<Integer>();
    private static int[] DijkstraDistances = new int[VERTICES_COUNT];
    private static int[] Predecessors = new int[VERTICES_COUNT];

    public static void main(String[] args) {
        dijkstra(START_VERTEX - 1);
        printResult(START_VERTEX - 1);
    }

    // Алгоритъм на Дейкстра - минимален път от s до останалите върхове
    private static void dijkstra(int startVertex) {
        // Инициализиране: DijkstraDistances[i] = A[StartVertex, i], i∈V, i !=
        // StartVertex
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (Graph[startVertex][i] == 0) {
                DijkstraDistances[i] = MAX_VALUE;
                Predecessors[i] = NO_PARENT;
            } else {
                DijkstraDistances[i] = Graph[startVertex][i];
                Predecessors[i] = startVertex;
            }
        }

        for (int i = 0; i < VERTICES_COUNT; i++) {
            T.add(i); // T съдържа всички върхове
        }

        T.remove(startVertex); // от графа, с изключение на startVertex
        Predecessors[startVertex] = NO_PARENT;

        while (T.size() > 0) {
            // Избиране на върха j от T, за който DijkstraDistances[j] е
            // минимално
            int j = NO_PARENT;
            int distanceToI = MAX_VALUE;
            for (int i = 0; i < VERTICES_COUNT; i++) {
                if (T.contains(i) && DijkstraDistances[i] < distanceToI) {
                    distanceToI = DijkstraDistances[i];
                    j = i;
                }
            }

            if (j == NO_PARENT) {
                // DijkstraDistances[i] = MaxValue, за всички i: изход
                break;
            }

            T.remove(j);

            // За всяко i от T изпълняваме DijkstraDistances[i] =
            // min(DijkstraDistances[i], DijkstraDistances[j] + Graph[j, i])
            for (int i = 0; i < VERTICES_COUNT; i++) {
                if (T.contains(i) && Graph[j][i] != 0) {
                    if (DijkstraDistances[i] > DijkstraDistances[j]
                            + Graph[j][i]) {
                        DijkstraDistances[i] = DijkstraDistances[j]
                                + Graph[j][i];
                        Predecessors[i] = j;
                    }
                }
            }
        }
    }

    private static void printPath(int startVertex, int vertex) {
        if (Predecessors[vertex] != startVertex) {
            printPath(startVertex, Predecessors[vertex]);
        }

        System.out.printf("%d ", vertex + 1);
    }

    private static void printResult(int startVertex) {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (i != startVertex) {
                if (DijkstraDistances[i] == MAX_VALUE) {
                    System.out.printf("Няма път между върховете %d и %d\n",
                            startVertex + 1, i + 1);
                } else {
                    System.out.printf("Минимален път от връх %1$d до %2$d: %1$d ",
                            startVertex + 1, i + 1);
                    printPath(startVertex, i);
                    System.out.printf(", дължина на пътя: %d\n",
                            DijkstraDistances[i]);
                }
            }
        }
    }
}
