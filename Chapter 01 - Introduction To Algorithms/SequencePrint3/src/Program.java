public class Program {
	private static final int N = 5;

	private static int k = 0;
	private static long result = 1;

	public static void main(String[] args) {
		printSequence();
		System.out.println();
	}

	private static void printSequence() {
		k++;
		result *= 10;

		System.out.printf("%d ", result);
		if (k < N) {
			printSequence();
		}

		System.out.printf("%d ", result);
		result /= 10;
	}
}
