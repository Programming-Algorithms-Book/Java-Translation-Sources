public class Program {
	private static final int A = 24;
	private static final int B = 108;

	public static void main(String[] args) {
		System.out.printf("Най-големият общ делител на %d и %d е %d\n", A, B,
				getGreatestCommonDivisor(A, B));
	}

	private static int getGreatestCommonDivisor(int a, int b) {
		int swap = 0;
		while (b > 0) {
			swap = b;
			b = a % b;
			a = swap;
		}

		return a;
	}
}
