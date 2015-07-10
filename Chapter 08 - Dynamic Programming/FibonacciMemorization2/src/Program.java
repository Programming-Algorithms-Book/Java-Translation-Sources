public class Program {
    private static final int MAX = 250;
    private static final int N = 10;

    private static int[] Memo = new int[MAX + 1];

    public static void main(String[] args) {
        System.out.printf("%d-тото число на Фибоначи е: %d\n", N,
                fibonacciMemorization(N - 1));
    }

    /* Бърз рекурсивен логаритмичен вариант, запаметяващ вече изчисленото */
    private static int fibonacciMemorization(int n) {
        if (n < 2) {
            Memo[n] = 1;
        } else if (Memo[n] == 0) {
            if (n % 2 == 1) {
                Memo[n] = fibonacciMemorization(n - 1)
                        + fibonacciMemorization(n - 2);
            } else {
                Memo[n] = getSquare(fibonacciMemorization(n / 2))
                        + getSquare(fibonacciMemorization((n / 2) - 1));
            }
        }

        return Memo[n];
    }

    private static int getSquare(int num) {
        int square = (int) Math.pow(num, 2);
        return square;
    }
}
