import java.util.ArrayList;

public class Program {
    private static final int N = 1000;
    private static ArrayList<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) {
        findPrimeNumbersToN(N);
        System.out.println();
    }

    private static void findPrimeNumbersToN(int n) {
        int i = 2;
        while (i < n) {
            if (isPrime(i)) {
                primeNumbers.add(i);
                System.out.printf("%d ", i);
            }

            i++;
        }
    }

    private static boolean isPrime(int number) {
        int i = 0;
        int primeNumbersCount = primeNumbers.size();
        while (i < primeNumbersCount
                && primeNumbers.get(i) * primeNumbers.get(i) <= N) {
            if (number % primeNumbers.get(i) == 0) {
                return false;
            }

            i++;
        }

        return true;
    }
}
