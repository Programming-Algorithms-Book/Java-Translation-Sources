public class Program {

    // Число, което ще се разлага
    private static int n = 435;

    public static void main(String[] args) {
        System.out.printf("%d = ", n);
        int i = 1;
        while (n != 1) {
            i++;
            int how = 0;
            while (n % i == 0) {
                how++;
                n /= i;
            }

            for (int j = 0; j < how; j++) {
                System.out.printf("%d ", i);
            }
        }

        System.out.println();
    }
}
