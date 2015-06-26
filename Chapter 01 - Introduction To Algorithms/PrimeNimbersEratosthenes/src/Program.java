public class Program {
	private static final int N = 200;
	private static boolean[] sieve = new boolean[N + 1];

	public static void main(String[] args) {
		findPrimeNumbersToN(N);
		System.out.println();
	}

	private static void findPrimeNumbersToN(int n) {
		int i = 2;
		while (i <= n) {
			if (!sieve[i]) {
				System.out.printf("%d ", i);
				int j = i * i;
				while (j <= n) {
					sieve[j] = true;
					j += i;
				}
			}

			i++;
		}
	}
}
