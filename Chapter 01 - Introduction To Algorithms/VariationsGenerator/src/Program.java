public class Program {
	private static final int N = 4;
	private static final int K = 2;

	private static int[] Variation = new int[K];

	public static void main(String[] args) {
		generateVariations(0);
	}

	private static void print(int i) {
		System.out.print("( ");
		for (int k = 0; k < i; k++) {
			System.out.printf("%d ", Variation[k] + 1);
		}

		System.out.println(")");
	}

	// Вариации на n елемента от клас k
	private static void generateVariations(int i) {
		/*
		 * Без if (i >= k) и return; тук (а само Print(i); ако искаме всички
		 * генерирания с дължина 1, 2, …, k, а не само вариациите с дължина k
		 */
		if (i >= K) {
			print(i);
			return;
		}

		for (int j = 0; j < N; j++) {
			// if (allowed(k)) {
			Variation[i] = j;
			generateVariations(i + 1);
		}
	}
}
