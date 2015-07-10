public class Program {
    private static final int N = 10;

    private static int[][] MatrE = { { 1, 1 }, { 1, 0 } }; /* Изходна матрица */
    private static int[][] Matrix = new int[2][2]; /* Резултатна матрица */
    private static int[][] HelpMatrix = new int[2][2]; /* Помощна матрица */

    private static int sq12; /* Помощна променлива */

    public static void main(String[] args) {
        fibonacciMatrix(N - 1, Matrix);
        System.out.printf("%d-тото число на Фибоначи е: %d\n", N, Matrix[0][0]);
    }

    private static void fibonacciMatrix(int n, int[][] matr) {
        if (n < 2) {
            for (int row = 0; row < MatrE.length; row++) {
                for (int col = 0; col < MatrE[0].length; col++) {
                    matr[row][col] = MatrE[row][col];
                }
            }
        } else if (0 == n % 2) {
            fibonacciMatrix(n / 2, HelpMatrix);
            sq12 = (int) Math.pow(HelpMatrix[0][1], 2);
            matr[0][0] = (int) Math.pow(HelpMatrix[0][0], 2) + sq12;
            matr[1][1] = (int) Math.pow(HelpMatrix[1][1], 2) + sq12;
            matr[0][1] = matr[0][0] - matr[1][1];
            matr[1][0] = matr[0][1];
        } else {
            fibonacciMatrix(n - 1, HelpMatrix);
            matr[1][1] = HelpMatrix[0][1];
            matr[0][1] = HelpMatrix[0][0];
            matr[0][0] = HelpMatrix[0][0] + HelpMatrix[1][0];
            matr[1][0] = HelpMatrix[0][1];
        }
    }
}
