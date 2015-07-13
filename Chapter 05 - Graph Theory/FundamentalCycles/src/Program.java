import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final int VERTICES_COUNT = 6;

    /*
     * Графът, представен с матрица на съседство: 0 - няма ребро; 1 – има;
     * По-късно с 2 ще маркираме ребрата на покриващото дърво на графа.
     */
    private static byte[][] Graph = { 
            { 0, 1, 1, 0, 0, 0 },
            { 1, 0, 1, 1, 0, 0 }, 
            { 1, 1, 0, 0, 0, 1 }, 
            { 0, 1, 0, 0, 1, 1 },
            { 0, 0, 0, 1, 0, 1 }, 
            { 0, 0, 1, 1, 1, 0 } 
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static List<Integer> Cycle = new ArrayList<Integer>();

    public static void main(String[] args) {
        findSpanningTree(0);
        System.out.println("Простите цикли в графа са:");
        for (int i = 0; i < VERTICES_COUNT - 1; i++) {
            for (int j = i + 1; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] == 1) {
                    for (int k = 0; k < VERTICES_COUNT; k++) {
                        Used[k] = false;
                    }

                    Cycle.clear();
                    Cycle.add(i);
                    findCycle(i, j);
                }
            }
        }
    }

    // Намира произволно покриващо дърво
    private static void findSpanningTree(int vertex) {
        Used[vertex] = true;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i] && Graph[vertex][i] == 1) {
                Graph[vertex][i] = 2;
                Graph[i][vertex] = 2;
                findSpanningTree(i);
            }
        }
    }

    // Намиране на един цикъл спрямо намереното покриващо дърво
    private static void findCycle(int v, int u) {
        if (v == u) {
            printCycle();
            return;
        }

        Used[v] = true;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i] && Graph[v][i] == 2) {
                Cycle.add(i);
                findCycle(i, u);
                Cycle.remove(Cycle.size() - 1);
            }
        }
    }

    private static void printCycle() {
        for (int i = 0; i < Cycle.size(); i++) {
            System.out.printf("{0} ", Cycle.get(i) + 1);
        }

        System.out.println();
    }
}
