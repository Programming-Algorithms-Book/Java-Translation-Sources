public class Program {
    private static final int MAX = 100;

    /* Брой накрайници */
    private static final int N = 9;

    /* Целева функция */
    private static int[] F = new int[MAX];
    private static int[] NextConductor = new int[MAX];

    /* Изх. пермутация */
    private static int[] Permutation = new int[] { 0, 9, 1, 3, 6, 2, 7, 5, 4, 8 };

    public static void main(String[] args) {
        solve();
        print();
    }

    private static void solve() {
        /* Инициализиране */
        for (int i = 1; i <= N; i++) {
            F[i] = 1;
        }

        /* Основен цикъл */
        for (int k = N; k >= 1; k--) {
            for (int i = k + 1; i <= N; i++) {
                if (Permutation[k] < Permutation[i]) {
                    if (1 + F[i] > F[k]) {
                        F[k] = 1 + F[i];
                        NextConductor[k] = i;
                    }
                }
            }
        }
    }

    private static void print() {
        int i, max, index = 1;
        for (max = F[index], i = 2; i <= N; i++) {
            if (F[i] > max) {
                max = F[i];
                index = i;
            }
        }

        System.out.printf("Максимален брой кабели: %d\n", max);
        do {
            System.out.printf("%d ", index);
            index = NextConductor[index];
        } while (index != 0);

        System.out.println();
    }
}
