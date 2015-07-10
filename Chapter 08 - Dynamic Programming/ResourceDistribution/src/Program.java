public class Program {
//    /* Максимално количество стока за град */
//    private static final int MaxPercity = 100;

    /* Максимален брой градове за търговия */
    private static final int MaxCities = 50;

    /* Максим. количество стока за разпределяне */
    private static final int MaxCargo = 200;

    /* Брой градове за разпределяне на стоката */
    private static final int K = 3;

    /* Количеството стока за разпределяне */
    private static final int N = 5;

    /* Максим.количество за продаване в град */
    private static final int M = 5;

    /* Целева функция */
    private static int[][] F = new int[MaxCities][MaxCargo];

    /* Оптимално количество стока */
    private static int[][] Amount = new int[MaxCities][MaxCargo];
    private static int[][] V = {
    /* Таблица на цените на стоките */
    { 0, 10, 15, 25, 40, 60 }, { 0, 15, 20, 30, 45, 60 },
            { 0, 20, 30, 40, 50, 60 } };

    private static int inc = 0;

    public static void main(String[] args) {
        scheduleCargo();
        printResults();
    }

    private static int maxIncome(int city, int ccargo) {
        int max = 0;
        if (0 == ccargo) {
            /* Ако няма стока, няма и печалба ;) */
            return 0;
        } else if (0 == city) {
            /* Ако колич.стока ccargo трябва да се разпредели само */
            /* за 1 град, се избира максим.печалба за този град от тази стока */
            for (int i = 0; i <= Math.min(ccargo, M); i++) {
                if (max < V[city][i]) {
                    max = V[city][i];
                    Amount[city][ccargo] = i;
                }
            }

            F[city][ccargo] = max;
            return max;
        } else if (F[city][ccargo] != Integer.MAX_VALUE) {
            /* Ако функц. е вече изчислена, */
            return F[city][ccargo]; /* се взема стойността й от таблицата */
        } else {
            /* Взема се макс.цена, получена от колич. i стока в този град плюс */
            /* количеството останала стока ccargo-i в останалите градове */
            for (int i = 0; i <= Math.min(ccargo, M); i++) {
                inc = V[city][i] + maxIncome(city - 1, ccargo - i);
                if (max < inc) {
                    max = inc;
                    Amount[city][ccargo] = i;
                }
            }

            F[city][ccargo] = max;
            return max;
        }
    }

    private static void scheduleCargo() {
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                F[i][j] = Integer.MAX_VALUE;
            }
        }

        F[K - 1][N] = maxIncome(K - 1, N);
    }

    private static void printResults() {
        int k = Program.K;
        int n = Program.N;
        k -= 1;
        System.out.printf("Максимален доход: %d\n", F[k][n]);
        for (;;) {
            if (n == 0) {
                System.out.printf("В град %d продайте количество 0.\n", k + 1);
            } else {
                System.out.printf("В град %d продайте количество %d.\n", k + 1,
                        Amount[k][n]);
                n -= Amount[k][n];
            }

            if (k-- == 0) {
                break;
            }
        }

        if (n > 0) {
            System.out.printf("Остава стока: %d\n", n);
        }
    }
}
