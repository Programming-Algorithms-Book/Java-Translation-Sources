public class Program {
	public static void main(String[] args) {
		int[][] a = { { 3, 52, 1, 2 }, { -3, 2, 11, 6 }, { 7, 8, 2, 9 } };
		int[][] b = { { -5, 2, 7, 6 }, { 3, 5, 71, 2 }, { 7, 3, 11, 2 } };

		int[][] sum = getSum(a, b);
		printMatrix(sum);
	}

	private static int[][] getSum(int[][] a, int[][] b) {
		int[][] sum = new int[a.length][a[0].length];

		for (int row = 0; row < sum.length; row++) {
			for (int col = 0; col < sum[row].length; col++) {
				sum[row][col] = a[row][col] + b[row][col];
			}
		}

		return sum;
	}

	private static void printMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				System.out.printf("%d ", matrix[row][col]);
			}

			System.out.println();
		}
	}
}
