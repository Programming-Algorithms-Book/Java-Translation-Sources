
public class Program {
    
    /* Стойности на числата (без първото) */
    private static long[] X = new long[] { 0, 9, -3, 8, 7, -8, 0, 7 };
    
    /* Знаци между тях */
    private static char[] Sign = new char[] { ' ', '+', '*', '*', '-', '+', '*', '-' };
    
    /* Брой числа */
    private static int N = X.length - 1;
    
    /* Целеви функции Fmin() и Fmax() */
    private static Goal[][] F = new Goal[N + 1][N + 1];

    public static void main(String[] args)
    {
        initGoalMatrix(F);
        solve();
        print();
    }

    /* Намира максимума и минимума, както и как се получават */
    private static void solve()
    {
        /* Инициализация */
        Sign[0] = Sign[N];
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                F[i][j].setMin(Integer.MAX_VALUE);
            }
        }

        /* Пресмятане на стойностите на целевата функция */
        for (int i = 1; i <= N; i++)
        {
            calculate(i, N);
        }
    }

    /* Пресмята стойностите на целевата функция */
    private static void calculate(int beg, int len)
    {
        if (beg > N)
        {
            beg -= N;
        }

        /* Стойността вече е била сметната */
        if (F[beg][len].getMin() != Integer.MAX_VALUE)
        {
            return;
        }

        if (len == 1)
        {
            F[beg][len].setMin(X[beg]);
            F[beg][len].setMax(X[beg]);
            F[beg][len].setLenMax(0);
            F[beg][len].setLenMin(0);
            return;
        }

        /* Стойността трябва да се пресметне */
        F[beg][len].setMin(Integer.MAX_VALUE);
        F[beg][len].setMax(Integer.MIN_VALUE);
        for (int i = 1; i < len; i++)
        {
            /* Пресмятане на всички стойности f[beg, i] и f[beg+i, len-i] */
            calculate(beg, i);
            int beg2;
            if (beg + i > N)
            {
                beg2 = beg + i - N;
            }
            else
            {
                beg2 = beg + i;
            }

            calculate(beg2, len - i);
            long val1 = operation(F[beg][i].getMin(), Sign[beg2 - 1], F[beg2][len - i].getMin());
            long val2 = operation(F[beg][i].getMin(), Sign[beg2 - 1], F[beg2][len - i].getMax());
            long val3 = operation(F[beg][i].getMax(), Sign[beg2 - 1], F[beg2][len - i].getMin());
            long val4 = operation(F[beg][i].getMax(), Sign[beg2 - 1], F[beg2][len - i].getMax());
            /* Актуализиране на минималната стойност на f[beg, len] */
            long minValue = Math.min(val1, Math.min(val2, Math.min(val3, val4)));
            if (minValue < F[beg][len].getMin())
            {
                F[beg][len].setMin(minValue);
                F[beg][len].setLenMin(i);
            }

            /* Актуализиране на максималната стойност на f[beg, len] */
            long maxValue = Math.max(val1, Math.max(val2, Math.max(val3, val4)));
            if (maxValue > F[beg][len].getMax())
            {
                F[beg][len].setMax(maxValue);
                F[beg][len].setLenMax(i);
            }
        }
    }

    /* Извършва операцията */
    private static long operation(long v1, char sign, long v2)
    {
        switch (sign)
        {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            case '*':
                return v1 * v2;
        }

        return 0;
    }

    /* Търси и извежда максимума и минимума */
    private static void print()
    {
        int i, minIndex, maxIndex;
        for (minIndex = 1, i = 2; i <= N; i++)
        {
            if (F[i][N].getMin() < F[minIndex][N].getMin())
            {
                minIndex = i;
            }
        }

        for (maxIndex = 1, i = 2; i <= N; i++)
        {
            if (F[i][N].getMax() > F[maxIndex][N].getMax())
            {
                maxIndex = i;
            }
        }

        System.out.printf("Минимална стойност: %d\n", F[minIndex][N].getMin());
        printMinMax(minIndex, N, true);
        System.out.println();
        System.out.println();
        System.out.printf("Максимална стойност: %d\n", F[maxIndex][N].getMax());
        printMinMax(maxIndex, N, false);
    }

    /* Извежда израз, за който се получава min/max */
    private static void printMinMax(int beg, int len, boolean printMin)
    {
        int i, beg2;
        if (beg > N)
        {
            beg -= N;
        }

        if (1 == len)
        {
            System.out.print(X[beg]);
        }
        else
        {
            if (len < N)
            {
                System.out.print("(");
            }

            i = printMin ? F[beg][len].getLenMin() : F[beg][len].getLenMax();
            if ((beg2 = beg + i) > N)
            {
                beg2 -= N;
            }

            /* Рекурсия за лявата част на израза */
            printMinMax(beg, i, printMin);
            
            /* Извеждане на операцията */
            System.out.print(Sign[beg2 - 1]);
            
            /* Рекурсия за дясната част на израза */
            printMinMax(beg2, len - i, printMin);
            if (len < N)
            {
                System.out.print(")");
            }
        }
    }
    
    private static void initGoalMatrix(Goal[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                input[i][j] = new Goal(0, 0, 0, 0);
            }
        }
    }
}
