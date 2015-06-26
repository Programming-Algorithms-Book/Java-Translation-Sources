import java.util.Random;

public class Program {
    public static void main(String[] args) {
        int[][] a = new int[5][4];
        fillMatrixByRows(a);
        printMatrix(a);

        System.out.println("-----------------------------");

        int[][] b = new int[5][4];
        fillMatrixByColumns(b);
        printMatrix(b);
    }

    private static void fillMatrixByRows(int[][] matrix) {
        Random generator = new Random();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = generator.nextInt(16);
            }
        }
    }

    private static void fillMatrixByColumns(int[][] matrix) {
        Random generator = new Random();

        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][col] = generator.nextInt(16);
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }

            System.out.println();
            ;
        }
    }
}
