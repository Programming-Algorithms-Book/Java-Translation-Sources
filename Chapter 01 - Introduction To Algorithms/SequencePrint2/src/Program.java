public class Program {
	private static final int N = 5;

	private static int k = 0;

	public static void main(String[] args) {
		printSequence(10);
		System.out.println();
	}

	private static void printSequence(long result) {
		k++;
		System.out.printf("%d ", result);
		if (k < N) {
			printSequence(result * 10);
		}

		System.out.printf("%d ", result);
	}
}
