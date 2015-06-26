public class Program {
	private static final int NUMBER = 4242;

	public static void main(String[] args) {
		int digits = 0;

		for (int n = NUMBER; n > 0; n /= 10) {
			digits++;
		}

		System.out.printf("Броят на цифрите на %d е %d", NUMBER, digits);
		System.out.println();
	}
}
