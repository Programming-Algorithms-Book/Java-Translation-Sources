public class Program {
    private static final int VERTICES_COUNT = 6;

    private static int[][] Graph = { 
            { 0, 1, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 0, 0 }, 
            { 0, 1, 0, 1, 0, 0 }, 
            { 0, 0, 1, 0, 1, 0 },
            { 0, 0, 0, 1, 0, 1 }, 
            { 1, 0, 0, 0, 1, 0 } 
    };

    /*
     * // Пример за транзитивно неориентируем граф const int VerticesCount = 5;
     * static readonly int[,] Graph = new int[VerticesCount, VerticesCount] { {
     * 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 1 },
     * { 1, 0, 0, 1, 0 } };
     */

    public static void main(String[] args) {
        if (isTransitiveOrientable()) {
            printGraph();
        } else {
            System.out.println("Графът е транзитивно неориентируем!");
        }
    }

    private static boolean isTransitiveOrientable() {
        // Намира броя на ребрата в графа
        int tr = 0;
        for (int i = 0; i < VERTICES_COUNT - 1; i++) {
            for (int j = i + 1; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] != 0) {
                    tr++;
                }
            }
        }

        // Повтаряме, докато всички ребра от графа бъдат ориентирани
        int r = 0;
        do {
            // Стъпка 1 – ориентираме произволно ребро (i,j)
            for (int i = 0; i < VERTICES_COUNT; i++) {
                int j = 0;
                for (; j < VERTICES_COUNT; j++) {
                    if (Graph[i][j] == 1) {
                        Graph[i][j] = 2;
                        Graph[j][i] = -2;
                        break;
                    }
                }

                if (j < VERTICES_COUNT) {
                    break;
                }
            }

            // Прилагаме правило 1) и 2), докато е възможно
            boolean flag;
            do {
                flag = false;
                for (int i = 0; i < VERTICES_COUNT; i++) {
                    for (int j = 0; j < VERTICES_COUNT; j++) {
                        if (Graph[i][j] == 2) {
                            for (int k = 0; k < VERTICES_COUNT; k++) {
                                if (i != k && j != k) {
                                    // случай 2.1)
                                    if (Graph[i][k] == 0 || Graph[i][k] < -2) {
                                        // a) -> графът е транзитивно
                                        // неориентируем
                                        if (Graph[j][k] == 2) {
                                            return false;
                                        }

                                        // b) -> ориентираме реброто (j,k)
                                        if (Graph[j][k] == 1) {
                                            Graph[k][j] = 2;
                                            Graph[j][k] = -2;
                                            flag = true;
                                        }
                                    }

                                    // случай 2.2)
                                    if (Graph[j][k] == 0 || Graph[j][k] < -2) {
                                        // a) -> графът е транзитивно
                                        // неориентируем
                                        if (Graph[k][i] == 2) {
                                            return false;
                                        }

                                        // b) -> ориентираме реброто (j,k)
                                        if (Graph[i][k] == 1) {
                                            Graph[i][k] = 2;
                                            Graph[k][i] = -2;
                                            flag = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } while (flag);

            // Стъпка 3 – изключваме ориентираните ребра от графа
            for (int i = 0; i < VERTICES_COUNT; i++) {
                for (int j = 0; j < VERTICES_COUNT; j++) {
                    if (Graph[i][j] == 2) {
                        Graph[i][j] = -3;
                        Graph[j][i] = -4;
                        r++;
                    }
                }
            }
        } while (r < tr);

        return true;
    }

    private static void printGraph() {
        System.out.println("Транзитивната ориентация е:");
        for (int i = 0; i < VERTICES_COUNT; i++) {
            for (int j = 0; j < VERTICES_COUNT; j++) {
                if (Graph[i][j] == -3) {
                    System.out.print("  1");
                } else {
                    if (Graph[i][j] == -4) {
                        System.out.print(" -1");
                    } else {
                        System.out.print("  0");
                    }
                }
            }

            System.out.println();
        }
    }
}
