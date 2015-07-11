public class Program {
    /* Максимален брой върхове в графа */
    private static final int MaxN = 200;
    /* Брой върхове в графа */
    private static final int N = 6;
    /* Матрица на съседство на графа */
    private static int[][] A = { { 0, 10, 0, 5, 0, 0 }, { 0, 0, 5, 0, 0, 15 },
            { 0, 0, 0, 10, 5, 0 }, { 0, 10, 0, 0, 10, 0 },
            { 0, 5, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };

    private static int[] Vertex = new int[MaxN];
    private static int[] SavePath = new int[MaxN];
    private static int[] Used = new int[MaxN];
    private static int maxLen, tempLen, si, ti;

    public static void main(String[] args) {
        int i;
        maxLen = 0;
        tempLen = 0;
        si = 0;
        ti = 1;
        for (i = 0; i < N; i++) {
            Used[i] = 0;
        }

        for (i = 0; i < N; i++) {
            Used[i] = 1;
            Vertex[0] = i;

            addVertex(i);
            Used[i] = 0;
        }

        System.out.println("Най-дългият път е: ");
        for (i = 0; i < si; i++) {
            System.out.printf("%d ", SavePath[i] + 1);
        }

        System.out.printf("\nс обща дължина %d\n", maxLen);
    }

    private static void addVertex(int i) {
        int j, k;
        if (tempLen > maxLen) { /* намерили сме по-дълъг път => запазваме го */
            maxLen = tempLen;
            for (j = 0; j <= ti; j++) {
                SavePath[j] = Vertex[j];
            }

            si = ti;
        }

        for (k = 0; k < N; k++) {
            if (Used[k] == 0) { /* ако върхът k не участва в пътя до момента */
                /* ако върхът, който добавяме, е съседен на последния от пътя */
                if (A[i][k] > 0) {
                    tempLen += A[i][k];
                    Used[k] = 1; /* маркираме k като участващ в пътя */
                    Vertex[ti++] = k; /* добавяме върха k към пътя */
                    addVertex(k);
                    Used[k] = 0; /* връщане от рекурсията */
                    tempLen -= A[i][k];
                    ti--;
                }
            }
        }
    }
}
