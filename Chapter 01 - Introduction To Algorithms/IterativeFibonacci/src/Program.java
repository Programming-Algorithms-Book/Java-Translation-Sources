public class Program {
    private static final int N = 22;

    public static void main(String[] args) {
        long result = getFibonacciNumber2(N);
        System.out.printf("Fibonacci(%d) = %d\n", N, result);
    }

    // Пресмята n-тото число на Фибоначи, използвайки три променливи
    private static long getFibonacciNumber1(int n) {
        long fn = 1, fn1 = 0, fn2 = 0;
        while (n-- > 0) {
            fn2 = fn1;
            fn1 = fn;
            fn = fn1 + fn2;
        }

        return fn1;
    }

    // Пресмята n-тото число на Фибоначи, използвайки две променливи
    private static long getFibonacciNumber2(int n) {
        long f1 = 0, f2 = 1;
        while (n-- > 0) {
            f2 = f1 + f2;
            f1 = f2 - f1;
        }

        return f1;
    }
}
