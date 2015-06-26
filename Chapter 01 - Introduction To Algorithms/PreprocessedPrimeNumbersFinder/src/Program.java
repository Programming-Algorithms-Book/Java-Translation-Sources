public class Program {
    private static final int N = 21;

    private static int[] primeNumbers = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
            31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };

    public static void main(String[] args) {
        if (isPrime(N)) {
            System.out.printf("Числото %d е просто.\n", N);
        } else {
            System.out.printf("Числото %d е съставно.\n", N);
        }
    }

    private static boolean isPrime(int number) {
        int i = 0;
        while (i < primeNumbers.length
                && primeNumbers[i] * primeNumbers[i] <= N) {
            if (N % primeNumbers[i] == 0) {
                return false;
            }

            i++;
        }

        return true;
    }
}
