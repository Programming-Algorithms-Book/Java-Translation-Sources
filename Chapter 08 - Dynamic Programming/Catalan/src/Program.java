
public class Program {
    private static final int MAX = 80;
    private static final int INFINITY = Integer.MAX_VALUE;
    private static final int N = 5;

    private static double[][] Distances = new double[MAX][MAX];
    
    /* Таблица - целева функция  */
    private static double[][] M = new double[MAX][MAX];

    private static Position[] Coords = new Position[]
    {
        new Position(1, 1),
        new Position(5,-2),
        new Position(10, 1),
        new Position(7, 7),
        new Position(1, 7)
    };

    public static void main(String[] args)
    {
        calculateDistances();
        solve();
        printResult();
        System.out.println("Диагонали от минималния разрез: ");
        writeCut(1, N - 1);
    }

    private static void calculateDistances()
    {
        for (int i = 0; i < N - 1; i++)
        {
            for (int j = i + 1; j < N; j++)
            {
                Distances[i][j] = Math.sqrt(((Coords[i].getPointX() - Coords[j].getPointX()) *
                                             (Coords[i].getPointX() - Coords[j].getPointX())) +
                                            ((Coords[i].getPointY() - Coords[j].getPointY()) *
                                             (Coords[i].getPointY() - Coords[j].getPointY())));
            }
        }
    }

    private static void solve()
    {
        for (int j = 1; j < N; j++)
        {
            for (int i = 1; i < N - j; i++)
            {
                M[i][i + j] = INFINITY;
                for (int k = i; k < i + j; k++)
                {
                    double t = M[i][k] + M[k + 1][i + j] + Distances[i - 1][k]
                               + Distances[k][i + j] + Distances[i - 1][i + j];

                    if (t < M[i][i + j])
                    {
                        M[i][i + j] = t;
                        M[i + j][i] = k;
                    }
                }
            }
        }
    }

    private static void printResult()
    {
        double p = Distances[0][N - 1]; /* Пресмятане на периметъра */
        for (int i = 0; i < N; i++)
        {
            p += Distances[i][i + 1];
        }

        System.out.printf("Дължината на мин. разрез е %.2f\n", (M[1][N - 1] - p) / 2);
    }

    /* Извеждане на минималния разрез на екрана */

    private static void writeCut(int left, int right)
    {
        if (left != right)
        {
            writeCut(left, (int)M[right][left]);
            writeCut((int)M[right][left] + 1, right);
            if (left != 1 || right != N - 1)
            {
                System.out.printf("(%d, %d) \n", left, right + 1);
            }
        }
    }
}
