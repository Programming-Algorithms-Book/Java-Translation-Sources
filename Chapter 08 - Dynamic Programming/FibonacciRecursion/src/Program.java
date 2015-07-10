public class Program {
    private static final int N = 10;

    public static void main(String[] args) {
        System.out.printf("Fib(%d) = %d\n", N, getFibonaciiNumber(N));
    }

    private static int getFibonaciiNumber(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return getFibonaciiNumber(n - 1) + getFibonaciiNumber(n - 2);
        }
    }
}
