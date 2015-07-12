public class Program {
    private static final int MAX_MATRIX_SIZE = 100;

    public static void main(String[] args) {
        int numberOfTeams = 4;
        int[][] matrix = new int[MAX_MATRIX_SIZE][MAX_MATRIX_SIZE];
        findSolution(matrix, numberOfTeams);
        print(matrix, numberOfTeams);
    }

    /* Построява таблицата */
    private static void findSolution(int[][] matrix, int n) {
        /* Ако n е четно, задачата се свежда към n-1 */
        if (n % 2 == 0) {
            n--;
        }

        /* Попълване на таблицата за n - тук е гарантирано нечетно. */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = i + j < n ? i + j + 1 : i + j + 1 - n;
            }
        }

        /* Възстановяване на стойността на n */
        if (n % 2 == 1) {
            n++;
        }

        for (int i = 0; i < n; i++) {
            /* Запълване на последния стълб и ред при четно n */
            if (n % 2 == 0) {
                matrix[i][n - 1] = matrix[n - 1][i] = matrix[i][i];
            }

            matrix[i][i] = 0; /* Запълване с 0 на главния диагонал */
        }
    }

    /* Извежда резултата */
    private static void print(int[][] m, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", m[i][j]);
            }

            System.out.println();
        }
    }
}
