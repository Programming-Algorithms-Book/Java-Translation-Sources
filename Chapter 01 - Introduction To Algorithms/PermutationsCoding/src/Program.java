public class Program {
	private static final int N = 6;
	private static final int CODE = 551;

	private static int[] Permutation = { 5, 3, 6, 4, 2, 1 };

	public static void main(String[] args) {
		System.out.printf("Дадената пермутация се кодира като %d \n",
				codePermutation(N, Permutation));
		System.out.printf("Декодираме пермутацията отговаряща на числото %d: ",
				CODE);

		decodePermutation(CODE, N, Permutation);
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", Permutation[i]);
		}

		System.out.println();
	}

	private static long codePermutation(int n, int[] permutation) {
		int[] p = new int[n];
		int r = 0;
		long result = 0;
		for (int i = 0; i < n; i++) {
			p[i] = i + 1;
		}

		for (int pos = 0; pos < n; pos++) {
			r = 0;
			while (permutation[pos] != p[r]) {
				r++;
			}

			result = (result * (n - pos)) + r;
			for (int i = r + 1; i < n; i++) {
				p[i - 1] = p[i];
			}
		}

		return result;
	}

	private static void decodePermutation(int codeNumber, int n,
			int[] permutation) {
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i + 1;
		}

		int k = n;
		int m = 0;
		do {
			m = n - k + 1;
			permutation[k - 1] = codeNumber % m;
			if (k > 1) {
				codeNumber /= m;
			}
		} while (--k > 0);

		k = 0;
		do {
			m = permutation[k];
			permutation[k] = p[m];
			if (k < n) {
				for (int i = m + 1; i < n; i++) {
					p[i - 1] = p[i];
				}
			}
		} while (++k < n);
	}
}
