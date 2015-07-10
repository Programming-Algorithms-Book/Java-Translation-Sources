public class Program {
    /* Вероятност A да спечели отделен мач */
    private static final double PROBABILITY = 0.5;
    private static final int N = 5;

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%.6f ", getProbability(i, j));
            }

            System.out.println();
        }
    }

    /* Неефективен рекурсивен вариант */
    private static double getProbability(int i, int j) {
        if (0 == j) {
            return 0.0;
        } else if (0 == i) {
            return 1.0;
        } else {
            return (PROBABILITY * getProbability(i - 1, j))
                    + ((1 - PROBABILITY) * getProbability(i, j - 1));
        }
    }
}
