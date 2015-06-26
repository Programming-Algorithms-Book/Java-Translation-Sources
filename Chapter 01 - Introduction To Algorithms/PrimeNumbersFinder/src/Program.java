public class Program {
	private static final int N = 23;

	public static void main(String[] args) {
		if (isPrime(N)) {
			System.out.printf("Числото %d е просто.\n", N);
		} else {
			System.out.printf("Числото %d е съставно.\n", N);
		}
	}

	private static boolean isPrime(int number) {
		if (number == 2) {
			return true;
		}

		int divider = 2;
		while (divider <= Math.sqrt(number)) {
			if (number % divider == 0) {
				return false;
			}

			divider++;
		}

		return true;
	}
}
