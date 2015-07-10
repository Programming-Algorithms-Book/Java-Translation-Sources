public class Program {
    public static void main(String[] args) {
        System.out.println(calculateBinom(7, 3));
    }

    private static int calculateBinom(int n, int k) {
        if (k > n) {
            return 0;
        } else if (k == 0 || k == n) {
            return 1;
        } else {
            return calculateBinom(n - 1, k - 1) + calculateBinom(n - 1, k);
        }
    }
}
