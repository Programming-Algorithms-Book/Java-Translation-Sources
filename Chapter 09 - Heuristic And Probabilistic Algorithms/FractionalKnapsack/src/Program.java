public class Program {
    public static void main(String[] args) {
        /* ограничително тегло на раницата */
        final double M = 16;

        /* себестойност на предметите */
        double[] value = { 25.0, 12.0, 16.0 };

        /* количества от предметите */
        double[] quant = { 10.0, 8.0, 8.0 };

        /* отношения стойност/количество */
        double[] ratio = getRatio(value, quant);
        sort(ratio, value, quant);
        solve(M, value, quant);
    }

    private static double[] getRatio(double[] value, double[] quant) {
        double[] ratio = new double[value.length];
        for (int i = 0; i < ratio.length; i++) {
            ratio[i] = value[i] / quant[i];
        }

        return ratio;
    }

    /* сортира предметите по себестойност */
    private static void sort(double[] ratio, double[] value, double[] quant) {
        for (int i = 0; i < ratio.length - 1; i++) {
            for (int j = i + 1; j < ratio.length; j++) {
                if (ratio[j] > ratio[i]) {
                    double temp;

                    temp = value[i];
                    value[i] = value[j];
                    value[j] = temp;

                    temp = quant[i];
                    quant[i] = quant[j];
                    quant[j] = temp;

                    temp = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = temp;
                }
            }
        }
    }

    /* намира решението */
    private static void solve(double m, double[] value, double[] quant) {
        int i = 0;
        double t = 0;
        double v = 0;

        while (t + quant[i] <= m) {
            /* взима цели предмети, докато може */
            System.out.printf(
                    "Избира 100%% от предмет със стойност %.2f и тегло %.2f\n",
                    value[i], quant[i]);
            t += quant[i];
            v += value[i];
            i++;
        }

        System.out.printf(
                "Избира се %.2f%% от предмет със стойност %.2f и тегло %.2f\n",
                ((m - t) / quant[i]) * 100, value[i], quant[i]);

        v += (m - t) * (value[i] / quant[i]);
        System.out.printf("Обща получена цена: %.2f\n", v);
    }
}
