public class Program {
    private static int N = 12;

    public static void main(String[] args) {
        long sum = getSum(N);
        System.out.printf("Сумата на първите %d естествени числа е %d", N, sum);
    }

    private static long getSum(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        return sum;
    }
}
