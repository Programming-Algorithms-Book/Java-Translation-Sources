public class Program {
	private static final int N = 6;

	public static void main(String[] args) {
		System.out.printf("%d! = %d", N, getFactorial(N));
	}

	private static long getFactorial(int i) {
		if (i < 2) {
			return i;
		}

		return i * getFactorial(i - 1);
	}
}
