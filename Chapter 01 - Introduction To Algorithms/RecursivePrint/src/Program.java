public class Program {
    private static final int N = 7892;

    public static void main(String[] args) {
        printN(N);
        System.out.println();
    }

    private static void printN(int n) {
        if (n >= 10) {
            printN(n / 10);
        }

        System.out.print(n % 10);
    }
}
