public class Program {
    private static final int N = 3;

    public static void main(String[] args) {
        long factorial = getFactorial(N);
        System.out.printf("%d! = %d\n", N, factorial);
    }

    private static long getFactorial(int n) {
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }
}
