public class Program {
    private static final int NUMBER = 7;
    private static int[] mp = new int[NUMBER + 1];

    public static void main(String[] args) {
        mp[0] = NUMBER + 1;
        devNum(NUMBER, 1);
    }

    private static void print(int length) {
        for (int i = 1; i < length; i++) {
            System.out.printf("%d + ", mp[i]);
        }

        System.out.printf("%d", mp[length]);
        System.out.println();
    }

    private static void devNum(int n, int pos) {
        if (n == 0) {
            print(pos - 1);
        } else {
            for (int k = n; k >= 1; k--) {
                mp[pos] = k;
                if (mp[pos] <= mp[pos - 1]) {
                    devNum(n - k, pos + 1);
                }
            }
        }
    }
}
