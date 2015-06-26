public class Program {
	private static final int MAX_ADDS = 100;
	private static final int N = 15; // Сума, която ще разбиваме
	private static final int GIVEN_COUNT = 3; // Брой различни стойности на монетите

	private static int[] given = { 2, 3, 5 }; // Стойности на монетите
	private static int[] mp = new int[MAX_ADDS];

	public static void main(String[] args) {
		mp[0] = N + 1;
		devNum(N, 1);
	}

	private static void print(int length) {
		for (int i = 1; i < length; i++) {
			System.out.printf("%d + ", mp[i]);
		}

		System.out.printf("%d", mp[length]);
		System.out.println();
	}

	private static void devNum(int n, int pos) {
		int k;
		for (int p = GIVEN_COUNT; p > 0; p--) {
			k = given[p - 1];
			if (n > k) {
				mp[pos] = k;

				if (mp[pos] <= mp[pos - 1]) {
					devNum(n - k, pos + 1);
				}
			} else if (n == k) {
				mp[pos] = k;

				if (mp[pos] <= mp[pos - 1]) {
					print(pos);
				}
			}
		}
	}
}
