import java.util.LinkedList;
import java.util.Queue;

public class Program {
    private static final int VERTICES_COUNT = 14;
    private static final int START_VERTEX = 1;
    private static final int END_VERTEX = 10;

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
    private static int[] Predecessors = new int[VERTICES_COUNT];

    public static void main(String[] args) {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            Predecessors[i] = -1;
        }

        bfs(START_VERTEX - 1);
        if (Predecessors[END_VERTEX - 1] > -1) {
            System.out.println("Намереният път е:");
            System.out.printf("\nДължината на пътя е %d.\n",
                    printPath(END_VERTEX - 1));
        } else {
            System.out.println("Не съществува път между двата върха!");
        }
    }

    // Отпечатва върховете от минималния път и връща дължината му
    private static int printPath(int vertex) {
        int count = 1;
        if (Predecessors[vertex] > -1) {
            count += printPath(Predecessors[vertex]);
        }

        System.out.printf("%d ", vertex + 1);
        return count;
    }

    // Oбхождане в ширина от даден връх със запазване на предшественика
    private static void bfs(int startVertex) {
        Queue<Integer> verticesQueue = new LinkedList<Integer>();
        verticesQueue.add(startVertex);
        Used[startVertex] = true;
        int levelVertex = 1;
        while (verticesQueue.size() > 0) {
            for (int i = 0; i < levelVertex; i++) {
                int currentVertex = verticesQueue.poll();
                for (int j = 0; j < VERTICES_COUNT; j++) {
                    if (Graph[currentVertex][j] == 1 && !Used[j]) {
                        verticesQueue.add(j);
                        Used[j] = true;
                        Predecessors[j] = currentVertex;
                        if (j == END_VERTEX) {
                            return;
                        }
                    }
                }
            }

            levelVertex = verticesQueue.size();
        }
    }
}
