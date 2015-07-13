public class Program {
    private static final int VERTICES_COUNT = 8;

    private static byte[][] Graph = { 
            { 0, 1, 0, 0, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 1, 0, 0 }, 
            { 0, 1, 0, 1, 1, 0, 0, 0 },
            { 0, 0, 1, 0, 1, 0, 1, 0 }, 
            { 0, 0, 1, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 1 }, 
            { 0, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 0, 0, 0, 0, 1, 0, 0 } 
    };

    private static int[] S = new int[VERTICES_COUNT];
    private static int[] T = new int[VERTICES_COUNT];
    private static int sN = 0;
    private static int tN = 0;

    public static void main(String[] args) {
        System.out.println("Всички максимални независими множества в графа са:");
        FindMaxIndependentSets(0);
    }

    private static void printSet() {
        System.out.print("{ ");
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (T[i] == 1) {
                System.out.printf("%d ", i + 1);
            }
        }

        System.out.println("}");
    }

    private static void FindMaxIndependentSets(int last) {
        if (sN + tN == VERTICES_COUNT) {
            printSet();
            return;
        }

        for (int i = last; i < VERTICES_COUNT; i++) {
            if (S[i] == 0 && T[i] == 0) {
                for (int j = 0; j < VERTICES_COUNT; j++) {
                    if (Graph[i][j] == 1 && S[j] == 0) {
                        S[j] = last + 1;
                        sN++;
                    }
                }

                T[i] = 1;
                tN++;
                FindMaxIndependentSets(i + 1); // Рекурсия
                T[i] = 0;
                tN--;
                for (int j = 0; j < VERTICES_COUNT; j++) {
                    if (S[j] == last + 1) {
                        S[j] = 0;
                        sN--;
                    }
                }
            }
        }
    }
}
