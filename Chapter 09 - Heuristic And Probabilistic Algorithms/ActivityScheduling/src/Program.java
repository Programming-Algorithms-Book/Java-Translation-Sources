public class Program {
    public static void main(String[] args)
    {
        int[] start = { 3, 5, 7, 9, 13, 15, 17 };
        int[] end = { 8, 10, 12, 14, 15, 19, 20 };
        
        Solve(start, end);
    }

    private static void Solve(int[] start, int[] end)
    {
        int i = 1, j = 1;
        System.out.printf("Избрани лекции: %d \n", 1);

        while (j++ < start.length)
        {
            if (start[j - 1] > end[i - 1])
            {
                System.out.printf("%d ", j);
                i = j;
            }
        }

        System.out.println();
    }
}
