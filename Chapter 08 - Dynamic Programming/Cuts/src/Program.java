
public class Program {
    private static final int MaxSize = 100;
    private static final int NotCalculated = -1;

    private static Element[][] F = new Element[MaxSize][MaxSize];
    private static int sizeX; /* Размерност на парчето */
    private static int sizeY; /* Размерност на парчето */

    public static void main(String[] args)
    {
        initElementMatrix(F);
        init();
        System.out.printf("Максимална цена %d\n", solve(sizeX, sizeY));
        System.out.println("Размери (X,Y)-->Цена");
        printSolution(sizeX, sizeY);
        System.out.println();
    }

    private static void init()
    {
        int x, y;
        /* Входни данни */
        sizeX = 13;
        sizeY = 9;

        /* Инициализация */
        for (x = 1; x <= sizeX; x++)
        {
            for (y = 1; y <= sizeY; y++)
            {
                F[x][y].setValue(NotCalculated);
                F[x][y].setAction(0);
            }
        }

        /* Входни данни */
        F[11][1].setValue(28);
        F[5][3].setValue(31);
        F[1][2].setValue(4);
        F[2][1].setValue(2);
        F[3][1].setValue(7);
        F[10][1].setValue(23);
        F[3][2].setValue(14);
        F[3][3].setValue(17);
        F[5][4].setValue(41);
        F[5][7].setValue(96);
    }

    private static int solve(int x, int y)
    {
        int bestAction = 0;
        int i, x2 = x / 2, y2 = y / 2;
        if (F[x][y].getValue() != NotCalculated)
        {
            return F[x][y].getValue(); /* Вече е пресмятана */
        }

        int bestSolution = 0;
        if (x > 1)
        {
            /* Срязваме го хоризонтално и викаме рекурсия за двете части */
            for (i = 1; i <= x2; i++)
            {
                if (solve(i, y) + solve(x - i, y) > bestSolution)
                {
                    bestSolution = solve(i, y) + solve(x - i, y);
                    bestAction = i;
                }
            }
        }

        if (y > 1)
        {
            /* Срязваме го вертикално и викаме рекурсия за двете части */
            for (i = 1; i <= y2; i++)
            {
                if (solve(x, i) + solve(x, y - i) > bestSolution)
                {
                    bestSolution = solve(x, i) + solve(x, y - i);
                    bestAction = -i;
                }
            }
        }

        F[x][y].setValue(bestSolution);
        F[x][y].setAction(bestAction);
        return bestSolution;
    }

    private static void printSolution(int x, int y)
    {
        if (x > 0 && y > 0 && F[x][y].getValue() > 0)
        {
            if (F[x][y].getAction() > 0)
            {
                printSolution(F[x][y].getAction(), y);
                printSolution(x - F[x][y].getAction(), y);
            }
            else if (F[x][y].getAction() < 0)
            {
                printSolution(x, -F[x][y].getAction());
                printSolution(x, y - (-F[x][y].getAction()));
            }
            else
            {
                System.out.printf("(%d,%d) --> %d  \n", x, y, F[x][y].getValue());
            }
        }
    }
    
    private static void initElementMatrix(Element[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Element(0, 0);
            }
        }
    }
}
