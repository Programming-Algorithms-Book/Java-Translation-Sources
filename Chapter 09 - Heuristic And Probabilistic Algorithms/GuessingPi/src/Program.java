import java.util.Random;

public class Program {
    public static void main(String[] args) {
        long t = 1000000; /* брой тестове */
        int d = 10000; /* диаметър на окръжността */
        double r = d / 2;
        long k = 0;
        Random rand = new Random(0);

        for (int i = 0; i < t; i++) {
            int a = (int) (rand.nextInt(d) - r + 1);
            int b = (int) (rand.nextInt(d) - r + 1);
            if (Math.sqrt((a * a) + (b * b)) <= r) {
                k++;
            }
        }

        System.out.printf("Приближение на pi = %.2f\n", (4.0 * k) / t);
    }
}
