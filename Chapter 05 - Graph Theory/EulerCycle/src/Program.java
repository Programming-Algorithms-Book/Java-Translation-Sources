import java.util.Stack;

public class Program {
    private static final int VERTICES_COUNT = 8;

    private static byte[][] Graph = { 
            { 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 1, 0, 0, 0 }, 
            { 0, 0, 0, 1, 1, 0, 1, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0 }, 
            { 0, 0, 1, 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0 }
    };

    public static void main(String[] args) {
        if (isEulerGraph()) {
            findEulerCycle(0);
        } else {
            System.out.println("Графът не е Ойлеров!");
        }
    }

    private static void findEulerCycle(int vertex) {
        Stack<Integer> currentCycle = new Stack<Integer>();
        Stack<Integer> mergedCycles = new Stack<Integer>();
        currentCycle.push(vertex);
        while (currentCycle.size() > 0) {
            vertex = currentCycle.peek();
            int i = 0;
            for (i = 0; i < VERTICES_COUNT; i++) {
                if (Graph[vertex][i] == 1) {
                    Graph[vertex][i] = 0;
                    vertex = i;
                    break;
                }
            }

            if (i < VERTICES_COUNT) {
                currentCycle.push(i);
            } else {
                mergedCycles.push(currentCycle.pop());
            }
        }

        System.out.println("Ойлеровият цикъл е: ");
        while (mergedCycles.size() > 0) {
            System.out.printf("%d ", mergedCycles.pop() + 1);
        }

        System.out.println();
    }

    private static boolean isEulerGraph() {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            int edgesIn = 0;
            int edgesOut = 0;
            for (int j = 0; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] == 1) {
                    edgesIn++;
                }

                if (Graph[j][i] == 1) {
                    edgesOut++;
                }
            }

            if (edgesIn != edgesOut) {
                return false;
            }
        }

        return true;
    }
}
