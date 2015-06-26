public class Program {
	private static final int N = 123;

	public static void main(String[] args) {
		double digitsCount = 0;
		for (int i = 1; i <= N; i++) {
			digitsCount += Math.log10(i);
		}

		System.out.printf("Броят на цифрите на %d! е %d\n", N,
				(long) digitsCount + 1);
	}
}
