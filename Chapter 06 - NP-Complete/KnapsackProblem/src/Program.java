public class Program {
    private static final int MAX_N = 100;
    private static final int N = 10;
    private static final float M = 10.5F;
    private static double[] C = { 10.3, 9.0, 12.0, 8.0, 4.0, 8.4, 9.1, 17.0,
            6.0, 9.7 };
    private static double[] ArrayM = { 4.0, 2.6, 3.0, 5.3, 6.4, 2.0, 4.0, 5.1,
            3.0, 4.0 };

    private static int[] Taken = new int[MAX_N];
    private static int[] SaveTaken = new int[MAX_N];

    private static int tn, sn;
    private static double maxV, vTemp, tTemp, totalV;

    public static void main(String[] args) {
        int i;
        tn = 0;
        maxV = 0;
        totalV = 0;
        for (i = 0; i < N; i++) {
            totalV += C[i];
        }

        generate(0);
        System.out.printf("Максимално тегло: %.2f\nИзбрани предмети: ", maxV);
        for (i = 0; i < sn; i++) {
            System.out.printf("%d ", SaveTaken[i] + 1);
        }

        System.out.println();
    }

    private static void generate(int i) {
        int k;
        if (tTemp > M) {
            return;
        }

        if (vTemp + totalV < maxV) {
            return;
        }

        if (i == N) {
            if (vTemp > maxV) {
                /* запазване на оптималното решение */
                maxV = vTemp;
                sn = tn;
                for (k = 0; k < tn; k++) {
                    SaveTaken[k] = Taken[k];
                }
            }

            return;
        }

        Taken[tn++] = i;
        vTemp += C[i];
        totalV -= C[i];
        tTemp += ArrayM[i];
        generate(i + 1);
        tn--;
        vTemp -= C[i];
        tTemp -= ArrayM[i];
        generate(i + 1);
        totalV += C[i];
    }
}
