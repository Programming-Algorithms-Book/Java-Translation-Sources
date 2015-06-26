public class Program {
	private static final int N = 7;
	private static final int K = 3;
	private static long[] lastLine = new long[N + 1];

	public static void main(String[] args) {
		lastLine[0] = 1;
		for (int i = 1; i <= N; i++) {
			lastLine[i] = 1;
			for (int j = i - 1; j >= 1; j--) {
				lastLine[j] += lastLine[j - 1];
			}
		}

		System.out.printf("C(%d, %d) = %d", N, K, lastLine[K]);
	}
}
