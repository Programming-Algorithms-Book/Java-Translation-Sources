public class Program {

	// Брой числа в редицата
	private static final int N = 8;

	// Търсена сума
	private static final int Sum = 0;

	// Редицата
	private static int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static void main(String[] args) {
		variate(0);
	}

	private static void checkSolution() {
		int tempSum = 0;
		for (int i = 0; i < N; i++) {
			tempSum += numbers[i];
		}

		// Намерено е решение => отпечатваме го
		if (tempSum == Sum) {
			for (int i = 0; i < N; i++) {
				if (numbers[i] > 0) {
					System.out.printf("+%d ", numbers[i]);
				} else {
					System.out.printf("%d ", numbers[i]);
				}
			}

			System.out.printf("= {0}\n", tempSum);
		}
	}

	private static void variate(int i) {
		if (i >= N) {
			checkSolution();
			return;
		}

		numbers[i] = Math.abs(numbers[i]);
		variate(i + 1);

		numbers[i] = -Math.abs(numbers[i]);
		variate(i + 1);
	}
}
