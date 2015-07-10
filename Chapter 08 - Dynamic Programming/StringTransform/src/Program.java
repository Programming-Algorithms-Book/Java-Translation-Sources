public class Program {
    private static final int CostDelete = 1;
    private static final int CostInsert = 2;

    /* Изходен низ (първият символ няма значение) */
    private static String S1 = "_abracadabra";

    /* Низ-цел (първият символ няма значение) */
    private static String S2 = "_mabragabra";

    /* Дължина на първия низ */
    private static int N1 = S1.length() - 1;

    /* Дължина на втория низ */
    private static int N2 = S2.length() - 1;

    /* Целева функция */
    private static int[][] F = new int[N1 + 1][N2 + 1];

    public static void main(String[] args) {
        System.out.printf("Минимално разстояние между двата низа: %d\n",
                EditDistance());
        PrintEditOperations(N1, N2);
    }

    private static int ReplaceOrMatch(char c1, char c2) {
        return (c1 == c2) ? 0 : 3;
    }

    /* Намира разстоянието между два низа */

    private static int EditDistance() {
        /* Инициализация */
        for (int i = 0; i <= N1; i++) {
            F[i][0] = i * CostDelete;
        }

        for (int j = 0; j <= N2; j++) {
            F[0][j] = j * CostInsert;
        }

        /* Основен цикъл */
        for (int i = 1; i <= N1; i++) {
            for (int j = 1; j <= N2; j++) {
                F[i][j] = Math.min(
                        F[i - 1][j - 1]
                                + ReplaceOrMatch(S1.charAt(i), S2.charAt(j)),
                        Math.min(F[i][j - 1] + CostInsert, F[i - 1][j]
                                + CostDelete));
            }
        }

        return F[N1][N2];
    }

    /* Извежда операциите по редактирането */
    private static void PrintEditOperations(int i, int j) {
        if (j == 0) {
            for (j = 1; j <= i; j++) {
                System.out.printf("DELETE(%d) ", j);
            }
        } else if (i == 0) {
            for (i = 1; i <= j; i++) {
                System.out.printf("INSERT(%d, %s) ", i, S2.charAt(i));
            }
        } else if (i > 0 && j > 0) {
            if (F[i][j] == F[i - 1][j - 1]
                    + ReplaceOrMatch(S1.charAt(i), S2.charAt(j))) {
                PrintEditOperations(i - 1, j - 1);
                if (ReplaceOrMatch(S1.charAt(i), S2.charAt(j)) > 0) {
                    System.out.printf("REPLACE(%d, %s) ", i, S2.charAt(j));
                }
            } else if (F[i][j] == F[i][j - 1] + CostInsert) {
                PrintEditOperations(i, j - 1);
                System.out.printf("INSERT(%d, %s) ", i, S2.charAt(j));
            } else if (F[i][j] == F[i - 1][j] + CostDelete) {
                PrintEditOperations(i - 1, j);
                System.out.printf("DELETE(%d) ", i);
            }
        }
    }
}
