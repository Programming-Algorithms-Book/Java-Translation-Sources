public class Program {
	private static final int MAX_LENGTH = 20; // Множители: най-много log2n (минималният е 2)
	private static final int NUMBER = 50; // Число, което ще разбиваме

	private static int[] mp = new int[MAX_LENGTH];

	public static void main(String[] args) {
		mp[0] = NUMBER + 1;
		devNum(NUMBER, 1);
	}

	private static void print(int length) {
		for (int i = 1; i < length; i++) {
			System.out.printf("%d * ", mp[i]);
		}

		System.out.printf("%d", mp[length]);
	}

	private static void devNum(int n, int pos) {
		if (n == 1) {
			print(pos - 1);
		} else {
			for (int k = n; k > 1; k--) {
				mp[pos] = k;
				if ((mp[pos] <= mp[pos - 1]) && (n % k == 0)) {
					devNum(n / k, pos + 1);
				}
			}
		}
	}
}
