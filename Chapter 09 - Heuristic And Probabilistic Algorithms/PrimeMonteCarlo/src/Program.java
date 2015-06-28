import java.util.Random;

public class Program {
    public static void main(String[] args) {
        /* проверява се дали даденото число n е просто */
        final long N = 127;

        /* брой опити на Монте Карло алгоритъма със случайна база a */
        final int K = 10;
        System.out.printf("Числото %d e %s.\n", N, isPrime(N, K) ? "просто"
                : "съставно");
    }

    /* пресмята a^t mod n; */
    private static long bigmod(long a, long t, long n) {
        return (t == 1) ? (a % n) : (bigmod(a, t - 1, n) * (a % n)) % n;
    }

    private static boolean strongPrime(long n, long a) {
        long s = 1, t = n - 1;

        /* частен случай */
        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        /* стъпка 1) */
        while (t % 2 != 1) {
            s++;
            t /= 2;
        }

        /* стъпка 2) x = a^t mod n; */
        long x = bigmod(a, t, n);
        if (x == 1) {
            return true;
        }

        /* стъпка 3 */
        for (int i = 0; i < s; i++) {
            if (x == n - 1) {
                return true;
            }

            x = (x * x) % n;
        }

        return false;
    }

    private static boolean isPrime(long n, int k) {
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + (rand.nextLong() % n) - 3;
            if (!strongPrime(n, a)) {
                return false;
            }
        }

        return true;
    }
}
