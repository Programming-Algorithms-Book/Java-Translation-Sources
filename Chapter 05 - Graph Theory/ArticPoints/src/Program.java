import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final int VERTICES_COUNT = 7;

    // Матрица на съседство на графа
    private static byte[][] Graph = { 
        { 0, 1, 0, 0, 0, 0, 0 },
        { 1, 0, 1, 0, 0, 1, 0 }, 
        { 0, 1, 0, 1, 0, 1, 0 },
        { 0, 0, 1, 0, 1, 1, 0 }, 
        { 0, 0, 0, 1, 0, 0, 0 },
        { 0, 1, 1, 1, 0, 0, 1 }, 
        { 0, 0, 0, 0, 0, 1, 0 } 
     };

    private static int[] Prenum = new int[VERTICES_COUNT];
    private static int[] Lowest = new int[VERTICES_COUNT];
    private static int cN = 0;

    public static void main(String[] args) {
        findArticPoints();
    }

    private static void dfs(int vertex) {
        Prenum[vertex] = ++cN;
        for (int j = 0; j < VERTICES_COUNT; j++) {
            if (Graph[vertex][j] != 0 && Prenum[j] == 0) {
                Graph[vertex][j] = 2; // Строим покриващо дърво T
                dfs(j);
            }
        }
    }

    // Обхождане на дървото в postorder
    private static void traversePostOrder(int vertex) {
        for (int j = 0; j < VERTICES_COUNT; j++) {
            if (Graph[vertex][j] == 2) {
                traversePostOrder(j);
            }
        }

        Lowest[vertex] = Prenum[vertex];
        for (int j = 0; j < VERTICES_COUNT; j++) {
            if (Graph[vertex][j] == 1) {
                Lowest[vertex] = Math.min(Lowest[vertex], Prenum[j]);
            }
        }

        for (int j = 0; j < VERTICES_COUNT; j++) {
            if (Graph[vertex][j] == 2) {
                Lowest[vertex] = Math.min(Lowest[vertex], Lowest[j]);
            }
        }
    }

    private static void findArticPoints() {
        dfs(0);
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (Prenum[i] == 0) {
                System.out.println("Графът не е свързан!");
                return;
            }
        }

        traversePostOrder(0);

        List<Integer> articPoints = new ArrayList<>();

        // Проверяваме 3.1)
        int count = 0;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (Graph[0][i] == 2) {
                count++;
            }
        }

        if (count > 1) {
            articPoints.add(0);
        }

        // Прилагаме стъпка 2)
        for (int i = 1; i < VERTICES_COUNT; i++) {
            int j = 0;
            for (; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] == 2 && Lowest[j] >= Prenum[i]) {
                    break;
                }
            }

            if (j < VERTICES_COUNT) {
                articPoints.add(i);
            }
        }

        System.out.print("Разделящите точки в графа са: ");
        for (int i = 0; i < articPoints.size(); i++) {
            System.out.printf("%d ", articPoints.get(i) + 1);
        }

        System.out.println();
    }
}
