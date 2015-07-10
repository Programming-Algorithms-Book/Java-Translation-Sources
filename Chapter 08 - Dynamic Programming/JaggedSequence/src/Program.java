
public class Program {
    private static final int NoInd = -1;

    private static int[] X = { 0, 8, 3, 5, 7, 0, 8, 9, 10, 20, 20, 20, 12, 19, 11 }; /* 0-ият не се ползва */
    private static int N = X.length - 1; /* Брой */

    /* Целева функция: макс. дължина на редица, завършваща с НАМАЛЯВАНЕ */
    private static int[] Fmin = new int[N];
    
    /* Предишен индекс от редицата на целевата функция Fmin */
    private static int[] FminBack = new int[N];
    
    /* Целева функция: макс. дължина на редица, завършваща с НАРАСТВАНЕ */
    private static int[] Fmax = new int[N];
    
    /* Предишен индекс от редицата на целевата функция Fmax */
    private static int[] FmaxBack = new int[N];

    public static void main(String[] args)
    {
        calculateFMinMax();
        findSolution();
    }

    private static void calculateFMinMax()
    {
        int ind, ind2;
        /* Инициализация */
        Fmin[0] = Fmax[0] = 1;
        FminBack[0] = FmaxBack[0] = NoInd;
        /* Последователно пресмятане на двете целеви функции */
        for (ind = 1; ind < N; ind++)
        {
            FmaxBack[ind] = FminBack[ind] = NoInd;
            Fmin[ind] = Fmax[ind] = 0;
            for (ind2 = 0; ind2 < ind; ind2++)
            {
                /* Опит за разширяване на намаляваща редица с нарастващ елемент */
                if (operation(X[ind2], X[ind]) && Fmin[ind2] > Fmax[ind])
                {
                    Fmax[ind] = Fmin[ind2];
                    FmaxBack[ind] = ind2;
                }

                /* Опит за разширяване на нарастваща редица с намаляващ елемент */
                if (operation(X[ind], X[ind2]) && Fmax[ind2] > Fmin[ind])
                {
                    Fmin[ind] = Fmax[ind2];
                    FminBack[ind] = ind2;
                }
            }

            /* Увеличаване с 1 заради текущия елемент */
            Fmin[ind]++;
            Fmax[ind]++;
        }
    }

    private static boolean operation(int a, int b)
    {
        return a < b;
    }

    private static void findSolution()
    {
        int ind, bestFminInd, bestFmaxInd;
        /* Намиране (края) на най-дългата редица */
        bestFminInd = bestFmaxInd = 0;
        for (ind = 1; ind < N; ind++)
        {
            if (Fmin[bestFminInd] < Fmin[ind])
            {
                bestFminInd = ind;
            }

            if (Fmax[bestFmaxInd] < Fmax[ind])
            {
                bestFmaxInd = ind;
            }
        }

        /* Маркиране на елементите й */
        if (Fmin[bestFminInd] > Fmax[bestFmaxInd])
        {
            markSolutionElements(Fmin, Fmax, FminBack, FmaxBack, bestFminInd);
        }
        else
        {
            markSolutionElements(Fmax, Fmin, FmaxBack, FminBack, bestFmaxInd);
        }

        /* Извеждане на решението на екрана */
        for (ind = 0; ind < N; ind++)
        {
            if (NoInd == Fmin[ind])
            {
                System.out.printf("%d ", X[ind]);
            }
        }

        System.out.println();
    }

    private static void markSolutionElements(
        int[] f1, int[] f2, int[] indF1, int[] indF2, int indF)
    {
        if (indF1[indF] == NoInd)
        {
            return;
        }

        f1[indF] = f2[indF] = NoInd;
        markSolutionElements(f2, f1, indF2, indF1, indF1[indF]);
    }
}
