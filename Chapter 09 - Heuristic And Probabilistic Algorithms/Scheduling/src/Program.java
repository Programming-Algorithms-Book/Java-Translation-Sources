public class Program {
    public static void main(String[] args) {
        int[] v = { 50, 40, 30, 20, 15 };
        int[] d = { 2, 1, 2, 2, 1 };

        /* оригинална номерация на задачите */
        int[] p = { 5, 1, 2, 4, 3 };

        solve(d, v, p);
    }

    private static boolean feasible(int[] index, int[] d, int k) {
        int s = 0;
        for (int i = 0; i < index.length; i++) {
            s += index[i];
            if (i == d[k] - 1) {
                s += 1;
            }

            if (s > i + 1) {
                return false;
            }
        }

        return true;
    }

    private static void solve(int[] d, int[] v, int[] p) {
        int[] index = new int[d.length];
        int[] taken = new int[d.length];
        int tn = 0;
        for (int k = 0; k < index.length; k++) {
            if (feasible(index, d, k)) {
                taken[tn++] = k;
                index[d[k] - 1]++;
            }
        }

        System.out.print("Оптимално разписание: ");

        int income = 0;
        for (int i = 0; i < tn; i++) {
            System.out.printf("%d ", p[taken[i]]);
            income += v[taken[i]];
        }

        System.out.println();
        System.out.printf("Общ доход: %d\n", income);
    }
}
