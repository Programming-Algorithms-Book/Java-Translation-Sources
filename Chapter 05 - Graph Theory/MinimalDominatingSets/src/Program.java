public class Program {
    private static final int VERTICES_COUNT = 6;

    private static byte[][] Graph = { 
            { 0, 1, 1, 0, 1, 0 },
            { 0, 0, 0, 1, 0, 0 }, 
            { 0, 0, 0, 1, 0, 0 }, 
            { 0, 0, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 0 }, 
            { 1, 0, 0, 0, 0, 0 } 
    };

    private static int[] Cover = new int[VERTICES_COUNT];
    private static boolean[] T = new boolean[VERTICES_COUNT];

    public static void main(String[] args) {
        System.out.println("Минималните доминиращи множества в графа са:");
        findMinDominatingSets(0);
    }

    private static void printSet() {
        System.out.print("{ ");
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (T[i]) {
                System.out.printf("%d ", i + 1);
            }
        }

        System.out.println("}");
    }

    private static boolean ok() {
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (T[i]) {
                // Проверка дали покритието се запазва при премахване на върха i
                if (Cover[i] == 0) {
                    continue;
                }

                int j = 0;
                for (; j < VERTICES_COUNT; j++) {
                    if (Cover[j] != 0 && Cover[j] - Graph[i][j] == 0 && !T[j]) {
                        break; // Остава непокрит връх
                    }
                }

                if (j == VERTICES_COUNT) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void findMinDominatingSets(int last) {
        // Проверява се дали е намерено решение
        int i = 0;
        for (; i < VERTICES_COUNT; i++) {
            if (Cover[i] == 0 && !T[i]) {
                break;
            }
        }

        if (i == VERTICES_COUNT) {
            printSet();
            return;
        }

        // Не - продължаваме да конструираме доминиращото множество
        for (i = last; i < VERTICES_COUNT; i++) {
            T[i] = true;
            for (int j = 0; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] == 1) {
                    Cover[j]++;
                }
            }

            if (ok()) {
                findMinDominatingSets(i + 1);
            }

            for (int j = 0; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] == 1) {
                    Cover[j]--;
                }
            }

            T[i] = false;
        }
    }
}
