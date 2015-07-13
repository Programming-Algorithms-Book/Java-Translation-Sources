import java.util.LinkedList;
import java.util.Queue;

public class Program {
    private static final int VERTICES_COUNT = 14;
    private static final int START_VETRIX = 5;

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

    public static void main(String[] args) {
        System.out.printf("Обхождане в ширина от връх %d:\n", START_VETRIX);
        bfs(START_VETRIX - 1);
    }

    private static void bfs(int startVertex) {
        Queue<Integer> verticesQueue = new LinkedList<Integer>();
        verticesQueue.add(startVertex);
        Used[startVertex] = true;
        int levelVertex = 1;
        while (verticesQueue.size() > 0) {
            for (int i = 0; i < levelVertex; i++) {
                int currentVertex = verticesQueue.poll();
                System.out.printf("%d ", currentVertex + 1);

                for (int j = 0; j < VERTICES_COUNT; j++) {
                    if (Graph[currentVertex][j] == 1 && !Used[j]) {
                        verticesQueue.add(j);
                        Used[j] = true;
                    }
                }
            }

            System.out.println();
            levelVertex = verticesQueue.size();
        }
    }
}
