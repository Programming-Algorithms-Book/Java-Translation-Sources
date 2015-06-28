import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Program {
    /* трябва да бъде такова, че psize % 4 == 0 */
    private static final int SIZE_P = 200;
    private static final int MAX_STEPS = 1000;
    private static final int N = 20; /* брой върхове на графа */
    private static final int MAX_N = 100;

    private static Random Rand = new Random();

    public static void main(String[] args) {
        int[][] population = new int[SIZE_P][MAX_N]; /* цикли на популацията */
        int[] result = new int[SIZE_P];

        int[][] a = initGraph(); /* матрица на теглата */

        /* Решение с генериране на произволни цикли */
        for (int s = 0; s < MAX_STEPS; s++) {
            for (int i = 0; i < SIZE_P; i++) {
                randomCycle(population, i);
                result[i] = evaluate(a, population, i);
            }
        }

        System.out
                .printf("Оптимално решение, намерено при генериране на произволни %d цикъла: %d\n",
                        SIZE_P * MAX_STEPS,
                        Collections.min(Arrays.stream(result).boxed()
                                .collect(Collectors.toList())));

        /* Решение с Генетичен Алгоритъм със същия брой итерации */
        for (int s = 0; s < MAX_STEPS; s++) {
            reproduce(result, a, s, population);
        }

        StringJoiner joiner = new StringJoiner(", ");

        int itemsToTake = result.length > 10 ? 10 : result.length;

        for (int i = 0; i < itemsToTake; i++) {
            joiner.add(Integer.toString(result[i]));
        }

        System.out
                .println("Най-късите цикли, намерени от генетичния алгоритъм:");
        System.out.println(joiner.toString());
    }

    /* създаване на произволен граф */

    private static int[][] initGraph() {
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = Rand.nextInt(100) + 1;
            }
        }

        return a;
    }

    private static int evaluate(int[][] a, int[][] population, int t) {
        int res = 0;
        for (int i = 0; i < N - 1; i++) {
            res += a[population[t][i]][population[t][i + 1]];
        }

        return res + a[population[t][N - 1]][population[t][0]];
    }

    private static void randomCycle(int[][] population, int t) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N; i++) {
            int p = Rand.nextInt(N - i) + 1;
            int j = 0;
            while (p > 0) {
                while (used[j]) {
                    j++;
                }

                p--;
                j++;
            }

            population[t][i] = j - 1;
            used[j - 1] = true;
        }
    }

    private static void combine(int[] result, int[][] a, int[][] population,
            int p1, int p2, int q1, int q2) {
        int[] uq1 = new int[N];
        int[] uq2 = new int[N];
        /* генерира наследници q1, q2 от родителите p1, p2 */
        int k = Rand.nextInt(N - 1) + 1; /* разменя в точката k */
        for (int i = 0; i < N; i++) {
            uq1[i] = 0;
            uq2[i] = 0;
        }

        for (int i = 0; i < k; i++) {
            population[q1][i] = population[p1][i];
            uq1[population[p1][i]]++;
            population[q2][i] = population[p2][i];
            uq2[population[p2][i]]++;
        }

        for (int i = k; i < N; i++) {
            if (uq1[population[p2][i]] == 0) {
                population[q1][i] = population[p2][i];
                uq1[population[p2][i]]++;
            } else {
                int j = 0;
                for (; uq1[j] != 0; j++) {
                }

                population[q1][i] = j;
                uq1[j]++;
            }

            if (uq2[population[p1][i]] == 0) {
                population[q2][i] = population[p1][i];
                uq2[population[p1][i]]++;
            } else {
                int j = 0;
                for (j = 0; uq2[j] != 0; j++) {
                }

                population[q2][i] = j;
                uq2[j]++;
            }
        }

        result[q1] = evaluate(a, population, q1);
        result[q2] = evaluate(a, population, q2);
    }

    private static void mutate(int[] result, int[][] a, int[][] population) {
        /* ако се получат две поредни еднакви решения - едното "мутира" */
        for (int i = 0; i < SIZE_P - 1; i++) {
            boolean flag = false;
            for (int k = 0; k < N; k++) {
                if (population[i][k] != population[i + 1][k]) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                /* цикъл i мутира */
                int p1 = Rand.nextInt(N);
                int p2 = Rand.nextInt(N);
                int swap = population[i][p1];
                population[i][p1] = population[i][p2];
                population[i][p2] = swap;
                result[i] = evaluate(a, population, i);
            }
        }
    }

    private static void reproduce(int[] result, int[][] a, int s,
            int[][] population) {
        /*
         * замества най-неоптималните цикли, като комбинира произволни Ј от
         * първата половина
         */
        for (int i = 0; i < (SIZE_P - 1) / 2; i += 2) {
            /* randomCycle(i); */
            combine(result, a, population, i, i + 1, SIZE_P - i - 1, SIZE_P - i
                    - 2);
            result[i] = evaluate(a, population, i);
        }

        /* сортира популацията по оптималност */
        for (int i = 0; i < SIZE_P - 1; i++) {
            for (int j = i + 1; j < SIZE_P; j++) {
                if (result[j] < result[i]) {
                    for (int k = 0; k < N; k++) {
                        int swap = population[i][k];
                        population[i][k] = population[j][k];
                        population[j][k] = swap;
                    }

                    {
                        int swap = result[i];
                        result[i] = result[j];
                        result[j] = swap;
                    }
                }
            }
        }

        if (s == MAX_STEPS - 1) {
            return;
        }

        mutate(result, a, population);
    }
}
