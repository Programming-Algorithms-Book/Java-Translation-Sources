import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final int VerticesCount = 14;
    private static final int StartVertex = 1;
    private static final int EndVertex = 10;

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

    private static boolean[] Used = new boolean[VerticesCount];
    private static List<Integer> Path = new ArrayList<>();

    public static void main(String[] args) {
        System.out.printf("Прости пътища между %d и %d:\n", StartVertex,
                EndVertex);
        allDfs(StartVertex - 1, EndVertex - 1);
    }

    // Намира всички прости пътища между върховете i и j
    private static void allDfs(int i, int j) {
        if (i == j) {
            Path.add(j);
            printPath();
            Path.remove(Path.size() - 1);
            return;
        }

        Used[i] = true; // Маркиране на посетения връх
        Path.add(i);

        // Рекурсия за всички съседи на i
        for (int k = 0; k < VerticesCount; k++) {
            if (Graph[i][k] == 1 && !Used[k]) {
                allDfs(k, j);
            }
        }

        // Връщане: размаркиране на посетения връх
        Used[i] = false;
        Path.remove(Path.size() - 1);
    }

    private static void printPath() {
        for (int i = 0; i < Path.size(); i++) {
            System.out.printf("%d ", Path.get(i) + 1);
        }

        System.out.println();
    }
}
