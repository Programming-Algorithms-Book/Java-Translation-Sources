public class Program {
	private static final int N = 3;

	private static int[] Permutation = new int[N];

	public static void main(String[] args) {
		for (int i = 0; i < N; i++) {
			Permutation[i] = i;
		}

		generatePermutations(N);
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", Permutation[i] + 1);
		}

		System.out.println();
	}

	private static void generatePermutations(int k) {
		if (k == 0) {
			print();
			return;
		}

		generatePermutations(k - 1);
		for (int i = 0; i < k - 1; i++) {
			int swap = Permutation[i];
			Permutation[i] = Permutation[k - 1];
			Permutation[k - 1] = swap;

			generatePermutations(k - 1);

			swap = Permutation[i];
			Permutation[i] = Permutation[k - 1];
			Permutation[k - 1] = swap;
		}
	}
}
