public class Program {
    private static final int MAX = 200;

    /* Динамично оптимиране */
    private static int[] Array = new int[MAX];

    public static void main(String[] args) {
        System.out.println(calculateBinomDynamic(7, 3));
    }

    private static int calculateBinomDynamic(int n, int k) {
        int j;
        for (int i = 0; i <= n; i++) {
            Array[i] = 1;
            if (i > 1) {
                if (k < i - 1) {
                    j = k;
                } else {
                    j = i - 1;
                }

                for (; j >= 1; j--) {
                    Array[j] += Array[j - 1];
                }
            }
        }

        return Array[k];
    }
}
