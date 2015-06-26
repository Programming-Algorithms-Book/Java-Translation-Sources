public class Program {
    private static final int N = 10;
    private static int i = 0;

    public static void main(String[] args) {
        i = N + 1;
        System.out.printf("%d! = %d\n", N, GetFactorial());
    }

    private static long GetFactorial() {
        if (i == 1) {
            return 1;
        }

        return --i * GetFactorial();
    }
}
