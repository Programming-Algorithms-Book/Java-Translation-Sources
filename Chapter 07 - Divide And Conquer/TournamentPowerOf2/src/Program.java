public class Program {
    private static final int MAX_MATIX_SIZE = 100;

    public static void main(String[] args) {
        int numberOfPlayers = 8;
        int[][] matrix = new int[MAX_MATIX_SIZE][MAX_MATIX_SIZE];
        findSolution(matrix, numberOfPlayers);
        print(matrix, numberOfPlayers);
    }

    private static void copyMatrix(int[][] matrix, int stX, int stY, int cnt,
            int add) {
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                matrix[i + stX][j + stY] = matrix[i + 1][j + 1] + add;
            }
        }
    }

    /* Построява таблицата */
    private static void findSolution(int[][] matrix, int n) {
        matrix[1][1] = 0;
        for (int i = 1; i <= n; i <<= 1) {
            copyMatrix(matrix, i + 1, 1, i, i);
            copyMatrix(matrix, i + 1, i + 1, i, 0);
            copyMatrix(matrix, 1, i + 1, i, i);
        }
    }

    /* Извежда резултата */
    private static void print(int[][] matrix, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }

            System.out.println();
        }
    }
}
