public class Program {
    private static final int MAX_MATRIX_SIZE = 100;

    public static void main(String[] args) {
        final int NumberOfTeams = 4;
        int[][] matrix = new int[MAX_MATRIX_SIZE][MAX_MATRIX_SIZE];
        findSolution(matrix, NumberOfTeams);
        print(matrix, NumberOfTeams);
    }

    /* Построява таблицата */
    private static void findSolution(int[][] matrix, int n) {
        int i;
        /* Ако n е четно, задачата се свеждаме към n-1 */
        if (n % 2 == 0) {
            n--;
        }

        /* Попълва се таблицата за n - тук е гарантирано нечетно. */
        for (i = 0; i < n * (n + 1); i++) {
            matrix[i % (n + 1)][i / (n + 1)] = (i % n) + 1;
        }

        /* Възстановява се стойността на n */
        if (n % 2 == 1) {
            n++;
        }

        for (i = 0; i < n; i++) {
            /* Запълват се последният стълб и ред при четно n */
            if (n % 2 == 0) {
                matrix[i][n - 1] = matrix[n - 1][i] = matrix[i][i];
            }

            /* Запълва се с 0 главният диагонал */
            matrix[i][i] = 0;
        }
    }

    /* Извежда резултата */
    private static void print(int[][] m, int n)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", m[i][j]);
            }

            System.out.println();
        }
    }
}
