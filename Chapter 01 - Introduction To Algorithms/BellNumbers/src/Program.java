public class Program {
	private static final int MAX_N = 100;
	private static final long N = 10;

	private static long[] m = new long[MAX_N + 1];

	public static void main(String[] args) {
		stirling(N);
		System.out.printf("Bell(%d) = {%d}", N, bell(N));
		System.out.println();
	}

	private static void stirling(long n) {
		if (n == 0) {
			m[0] = 1;
		} else {
			m[0] = 0;
		}

		for (int i = 1; i <= n; i++) {
			m[i] = 1;

			for (int j = i - 1; j >= 1; j--) {
				m[j] = (j * m[j]) + m[j - 1];
			}
		}
	}

	private static long bell(long n) {
		long result = 0;
		for (int i = 0; i <= n; i++) {
			result += m[i];
		}

		return result;
	}
}