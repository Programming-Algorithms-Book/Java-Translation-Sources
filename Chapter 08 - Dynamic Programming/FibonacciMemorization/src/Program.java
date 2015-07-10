public class Program {
    private static final int MAX = 256;
    private static final int N = 10;

    private static int[] Memo = new int[MAX + 1];

    public static void main(String[] args) {
        System.out.printf("%d-тото число на Фибоначи е: %d\n", N,
                fibonacciMemorization(N));
    }

    private static int fibonacciMemorization(int n) {
        if (n < 2) {
            Memo[n] = n;
        } else if (0 == Memo[n]) {
            Memo[n] = fibonacciMemorization(n - 1)
                    + fibonacciMemorization(n - 2);
        }

        return Memo[n];
    }
}
