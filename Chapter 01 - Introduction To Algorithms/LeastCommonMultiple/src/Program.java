public class Program {
    private static final int N = 4;
    private static int[] A = { 10, 8, 5, 9 };

    public static void main(String[] args) {
        System.out.println(getLeastCommonMultiple(A, N));
    }

    private static int getGreatestCommonDivisor(int a, int b) {
        return (b == 0) ? a : getGreatestCommonDivisor(b, a % b);
    }

    private static int getLeastCommonMultiple(int[] a, int n) {
        if (n == 2) {
            return (a[0] * a[1]) / getGreatestCommonDivisor(a[0], a[1]);
        } else {
            int b = getLeastCommonMultiple(a, n - 1);
            return (a[n - 1] * b) / getGreatestCommonDivisor(a[n - 1], b);
        }
    }
}
