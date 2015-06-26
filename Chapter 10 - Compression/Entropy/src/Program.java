public class Program {
    public static void main(String[] args) {
        double[] probs = { 0.2, 0.2, 0.15, 0.15, 0.10, 0.10, 0.05, 0.05 };

        System.out.println("Източник, зададен с честоти на срещане: ");
        for (double prob : probs) {
            System.out.printf("%.2f ", prob);
        }

        System.out.println();

        double entr = CalcEntropy(probs);
        System.out.printf("Ентропия на източника: %.5f\n", entr);
        System.out.printf("Теоретична цена на кода: %.5f\n", entr + 1);

        int[] lengths = CalcLengths(probs);
        System.out.print("Дължини на кодовете: ");
        for (int i = 0; i < lengths.length; i++) {
            System.out.printf("%d ", lengths[i]);
        }

        System.out.println();
        System.out.printf("Цена на кода при горните дължини: %.2f",
                CalcValue(probs, lengths));
    }

    private static double log(double x, int base) {
        return (Math.log(x) / Math.log(base) + 1e-10);
    }

    /* Пресмята ентропията на източника */
    private static double CalcEntropy(double[] probs) {
        // също sum = -probs.Select(p*Math.Log(p, 2)).Sum()j
        double sum = 0;
        for (int i = 0; i < probs.length; i++) {
            sum -= probs[i] * log(probs[i], 2);
        }

        return sum;
    }

    /* Пресмята дължините на кодовете на отделните букви */
    private static int[] CalcLengths(double[] probs) {
        // също probs.Select(p => Math.Ceiling(Math.Log(1.0 / p, 2))).ToArray()
        int[] lengths = new int[probs.length];
        for (int i = 0; i < probs.length; i++) {
            lengths[i] = (int) Math.ceil(log(1.0 / probs[i], 2));
        }

        return lengths;
    }

    /* Пресмята цената на кода */
    private static double CalcValue(double[] probs, int[] lengths) {
        // също sum = probs.Zip(lengths, (p, l) => p*l).Sum()
        double sum = 0;
        for (int i = 0; i < probs.length; i++) {
            sum += probs[i] * lengths[i];
        }

        return sum;
    }
}
