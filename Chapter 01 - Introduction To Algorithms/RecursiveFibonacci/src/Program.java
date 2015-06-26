public class Program {
	private static final int N = 22;

	public static void main(String[] args) {
		System.out.printf("Fibonacci(%d) = %d", N, getFibonacciNumber(N));
	}

	private static long getFibonacciNumber(int number) {
		if (number < 2) {
			return number;
		}

		return getFibonacciNumber(number - 1) + getFibonacciNumber(number - 2);
	}
}
