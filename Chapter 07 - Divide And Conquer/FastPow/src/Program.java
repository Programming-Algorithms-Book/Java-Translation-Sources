public class Program {
    private static final double BASE_NUMBER = 3.14;
    private static final int POWER = 11;

    public static void main(String[] args) {
        System.out.printf("%f^%d = %f\n", BASE_NUMBER, POWER,
                fastPower(BASE_NUMBER, POWER));
    }

    private static double fastPower(double x, int n) {
        if (n == 0) {
            return 1;
        } else {
            if ((n & 1) == 1) {
                return x * fastPower(x, n - 1);
            } else {
                return fastPower(x * x, n / 2);
            }
        }
    }
}
