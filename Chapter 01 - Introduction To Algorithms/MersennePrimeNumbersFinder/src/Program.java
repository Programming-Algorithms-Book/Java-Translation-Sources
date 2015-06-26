public class Program {
	// //private static final int MersennePrimeNumbersCount = 10;
	private static int[] primeNumbers = { 2, 3, 5, 7, 13, 17, 19, 31, 61, 89 };
	private static int[] number = new int[200];
	private static int k = 0;

	public static void main(String[] args) {
		for (int i = 1; i <= primeNumbers.length; i++) {
			calculatePerfectNumber(i, primeNumbers[i - 1]);
		}
	}

	private static void calculatePerfectNumber(int s, int m) {
		k = 1;
		number[0] = 1;
		for (int i = 0; i < m; i++) {
			doubleN(); // Това са делители от вида 2^i
		}

		number[0]--; // Последната цифра със сигурност е измежду { 2, 4, 6, 8 }

		for (int i = 0; i < m - 1; i++) {
			doubleN();
		}

		System.out.printf("%d-то съвършено число е = ", s);
		printPerfectNumber(); // Отпечатва поредното съвършено число
	}

	private static void doubleN() {
		int carry = 0, temp = 0;
		for (int i = 0; i < k; i++) {
			temp = (number[i] * 2) + carry;
			number[i] = temp % 10;
			carry = temp / 10;
		}

		if (carry > 0) {
			number[k++] = carry;
		}
	}

	private static void printPerfectNumber() {
		for (int i = k; i > 0; i--) {
			System.out.print(number[i - 1]);
		}

		System.out.println();
	}
}
