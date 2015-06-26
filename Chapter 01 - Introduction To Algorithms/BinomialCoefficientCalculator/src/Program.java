import java.util.ArrayList;

public class Program {
    private static ArrayList<Long> primes = new ArrayList<Long>();
    private static ArrayList<Long> counts = new ArrayList<Long>();

    private static long n = 7;
    private static long k = 3;

    public static void main(String[] args) {
        System.out.printf("C(%d, %d) = ", n, k);
        if (n - k < k) {
            k = n - k;
        }

        solve(n - k + 1, n, 1); // Факторизира числителя (n – k + 1), ..., n
        solve(1, k, -1); // Факторизира знаменателя 1, ..., k
        System.out.println(calculateBinomialCoefficient());
    }

    private static void modify(long x, long how) {
        for (int i = 0; i < primes.size(); i++) {
            if (primes.get(i) == x) {
                counts.set(i, counts.get(i) + how);
                return;
            }
        }

        counts.add(how);
        primes.add(x);
    }

    private static void solve(long start, long end, long inc) {
        for (long i = start; i <= end; i++) {
            long multiplier = i;
            long prime = 2;
            while (multiplier != 1) {
                int how = 0;
                while (multiplier % prime == 0) {
                    multiplier /= prime;
                    how++;
                }

                if (how > 0) {
                    modify(prime, inc * how);
                }

                prime++;
            }
        }
    }

    private static long calculateBinomialCoefficient() {
        long result = 1;
        for (int i = 0; i < primes.size(); i++) {
            for (long j = 0; j < counts.get(i); j++) {
                result *= primes.get(i);
            }
        }

        return result;
    }
}
