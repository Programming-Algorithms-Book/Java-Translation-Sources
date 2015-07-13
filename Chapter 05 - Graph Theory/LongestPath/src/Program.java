public class Program {
    private static final int VERTICES_COUNT = 6;

    // Матрица на теглата на графа
    private static int[][] Graph = { 
            { 0, 12, 0, 0, 0, 0 },
            { 0, 0, 40, 0, 17, 0 }, 
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 30, 0 }, 
            { 0, 0, 0, 0, 0, 20 }, 
            { 0, 0, 20, 0, 0, 0 } 
    };

    private static int[] MaximalDistances = new int[VERTICES_COUNT];
    private static int[] SavePath = new int[VERTICES_COUNT];

    public static void main(String[] args) {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            SavePath[i] = -1;
        }

        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (MaximalDistances[i] == 0) {
                dfs(i);
            }
        }

        int maxI = 0;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (MaximalDistances[i] > MaximalDistances[maxI]) {
                maxI = i;
            }
        }

        System.out.printf("Дължината на критичния път е %d\nПътят е: ",
                MaximalDistances[maxI]);
        while (SavePath[maxI] >= 0) {
            System.out.printf("%d ", maxI + 1);
            maxI = SavePath[maxI];
        }

        System.out.printf("%d \n", maxI + 1);
    }

    private static void dfs(int vertex) {
        if (MaximalDistances[vertex] > 0) {
            return;
        }

        int max = MaximalDistances[vertex];
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (Graph[vertex][i] != 0) {
                dfs(i);
                int distance = MaximalDistances[i] + Graph[vertex][i];
                if (distance > max) {
                    max = distance;
                    SavePath[vertex] = i;
                }
            }
        }

        MaximalDistances[vertex] = max;
    }
}
