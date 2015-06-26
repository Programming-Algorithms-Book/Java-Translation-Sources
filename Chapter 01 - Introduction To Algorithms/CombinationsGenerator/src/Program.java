public class Program {
	private static final int N = 5;
	private static final int K = 3;

	private static int[] Combination = new int[K];

	public static void main(String[] args) {
		System.out.printf("C(%d, %d): \n", N, K);
		generateCombinations(1, 0);
	}

	private static void print() {
		for (int i = 0; i < K; i++) {
			System.out.printf("%d ", Combination[i]);
		}

		System.out.println();
		;
	}

	private static void generateCombinations(int i, int after) {
		if (i > K) {
			return;
		}

		for (int j = after + 1; j <= N; j++) {
			Combination[i - 1] = j;
			if (i == K) {
				print();
			}

			generateCombinations(i + 1, j);
		}
	}
}
