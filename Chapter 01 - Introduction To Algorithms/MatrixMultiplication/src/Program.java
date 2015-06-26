public class Program {
	public static void main(String[] args) {
		int[][] a = { { 3, 52, 1, 2 }, { -3, 2, 11, 6 }, { 7, 8, 2, 9 } };
		int[][] b = { { -5, 2, 7, 6 }, { 3, 5, 7, 2 }, { 7, 3, 11, 2 },
				{ 2, 55, -6, 83 } };

		int[][] product = getProduct(a, b);
		printMatrix(product);
	}

	private static int[][] getProduct(int[][] a, int[][] b) {
		int[][] product = new int[a.length][b[0].length];
		for (int row = 0; row < product.length; row++) {
			for (int col = 0; col < product[0].length; col++) {
				for (int i = 0; i < a[0].length; i++) {
					product[row][col] += a[row][i] * b[i][col];
				}
			}
		}

		return product;
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
