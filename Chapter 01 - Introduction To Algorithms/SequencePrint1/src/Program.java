public class Program {
	private static final int N = 5;

	public static void main(String[] args) {
		printSequence(1, 10);
		System.out.println();
	}

	private static void printSequence(int k, long result) {
		System.out.printf("%d ", result);
		if (k < N) {
			printSequence(k + 1, result * 10);
		}

		System.out.printf("%d ", result);
	}
}
