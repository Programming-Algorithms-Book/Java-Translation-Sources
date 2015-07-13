import java.util.HashSet;


public class Program {
    private static final int VERTICES_COUNT = 9;

    private static byte[][] Graph =
    {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 0, 1, 0, 0, 0, 0, 0, 0 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 0, 1, 0, 0, 0 },
        { 0, 0, 0, 1, 1, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 0, 0, 0, 0, 1, 0, 0, 0 },
        { 0, 1, 0, 0, 0, 0, 0, 0, 0 }
    };

    private static boolean[] Used = new boolean[VERTICES_COUNT];
    private static HashSet<Integer> NonVerticesBase = new HashSet<Integer>();

    public static void main(String[] args)
    {
        for (int i = 0; i < VERTICES_COUNT; i++)
        {
            if (!NonVerticesBase.contains(i))
            {
                for (int j = 0; j < VERTICES_COUNT; j++)
                {
                    Used[j] = false;
                }

                getVerticesBase(i);
            }
        }

        System.out.print("Върховете, образуващи база в графа са: ");
        for (int i = 0; i < VERTICES_COUNT; i++)
        {
            if (!NonVerticesBase.contains(i))
            {
                System.out.printf("%d ", i + 1);
            }
        }

        System.out.printf("\nБрой на върховете в базата: %d\n", VERTICES_COUNT - NonVerticesBase.size());
    }

    private static void getVerticesBase(int vertex)
    {
        Used[vertex] = true;
        for (int i = 0; i < VERTICES_COUNT; i++)
        {
            if (Graph[vertex][i] == 1 && !Used[i])
            {
                NonVerticesBase.add(i);
                getVerticesBase(i);
            }
        }
    }
}
