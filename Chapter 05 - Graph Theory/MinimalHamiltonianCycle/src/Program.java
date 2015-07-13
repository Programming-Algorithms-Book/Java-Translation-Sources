public class Program {
    private static final int VerticesCount = 6;

    private static int[][] Graph = { 
            { 0, 5, 0, 0, 7, 7 },
            { 5, 0, 5, 0, 0, 0 }, 
            { 0, 5, 0, 6, 5, 0 }, 
            { 0, 0, 6, 0, 3, 3 },
            { 7, 0, 5, 3, 0, 5 }, 
            { 7, 0, 0, 3, 5, 0 } 
    };

    private static boolean[] Used = new boolean[VerticesCount];
    private static int[] CurrentCycle = new int[VerticesCount];
    private static int[] MinimalCycle = new int[VerticesCount];
    private static int currentSum = 0, minSum = Integer.MAX_VALUE;

    public static void main(String[] args) {
        CurrentCycle[0] = 1;
        findMinHamiltonianCycle(0, 0);
        printCycle();
    }

    private static void findMinHamiltonianCycle(int vertex, int level) {
        if (vertex == 0 && level > 0) {
            if (level == VerticesCount) {
                minSum = currentSum;
                System.arraycopy(CurrentCycle, 0, MinimalCycle, 0,
                        CurrentCycle.length);
            }

            return;
        }

        if (Used[vertex]) {
            return;
        }

        Used[vertex] = true;
        for (int i = 0; i < VerticesCount; i++) {
            if (Graph[vertex][i] != 0 && i != vertex) {
                CurrentCycle[level] = i;
                currentSum += Graph[vertex][i];

                // Прекъсване на генерирането
                if (currentSum < minSum) {
                    findMinHamiltonianCycle(i, level + 1);
                }

                currentSum -= Graph[vertex][i];
            }
        }

        Used[vertex] = false;
    }

    private static void printCycle() {
        System.out.print("Минимален Хамилтонов цикъл: 1");
        for (int i = 0; i < VerticesCount; i++) {
            System.out.printf(" %d", MinimalCycle[i] + 1);
        }

        System.out.printf(" с дължина %d.\n", minSum);
    }
}
