public class Program {
    private static final int VERTICES_COUNT = 6;

    private static byte[][] Graph = { 
            { 0, 1, 1, 0, 0, 0 },
            { 1, 0, 1, 0, 0, 0 }, 
            { 1, 1, 0, 0, 0, 0 }, 
            { 0, 0, 0, 0, 1, 1 },
            { 0, 0, 0, 1, 0, 1 },
            { 0, 0, 0, 1, 1, 0 } 
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];

    public static void main(String[] args) {
        System.out.println("Ето всички компоненти на свързаност:");
        int components = 0;
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (!Used[i]) {
                components++;
                System.out.print("{ ");
                findGraphComponents(i);
                System.out.print("}\n");
            }
        }

        if (components == 1) {
            System.out.println("Графът е свързан!");
        } else {
            System.out.printf("Брой на свързаните компоненти в графа: %d\n",
                    components);
        }
    }

    private static void findGraphComponents(int vertex) {
        Used[vertex] = true;
        System.out.printf("%d ", vertex + 1);
        for (int i = 0; i < VERTICES_COUNT; i++) {
            if (Graph[vertex][i] == 1 && !Used[i]) {
                findGraphComponents(i);
            }
        }
    }
}
