public class Program {
    private static final int MAX = 100;
    private static final int NOT_CALCULATED = 2;

    /* Изречение за проверка */
    private static final String CheckString = "NNNNNNNNECINNxqpCDNNNNNwNNNtNNNNs";

    /* Целева функция */
    private static int[][] F = new int[MAX][MAX];

    /* Дължина на изречението */
    private static int n;

    public static void main(String[] args) {
        init();
        System.out.printf("Изречението е %s\n",
                check(0, n - 1) != 0 ? "правилно!" : "НЕПРАВИЛНО!!!");
    }

    private static void init() {
        n = CheckString.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                F[i][j] = NOT_CALCULATED;
            }
        }
    }

    private static int check(int start, int end) {
        int k;
        if (F[start][end] != NOT_CALCULATED) {
            return F[start][end];
        } else {
            /* Вместо следващите 2 реда */
            if (start == end) {
                F[start][end] = (CheckString.charAt(start) >= 'p' && CheckString
                        .charAt(start) <= 'z') ? 1 : 0;
            } else if (CheckString.charAt(start) == 'N') {
                F[start][end] = check(start + 1, end);
            } else if (CheckString.charAt(start) == 'C'
                    || CheckString.charAt(start) == 'D'
                    || CheckString.charAt(start) == 'E'
                    || CheckString.charAt(start) == 'I') {
                k = start + 1;
                while (k < end
                        && !(check(start + 1, k) != 0 && check(k + 1, end) != 0)) {
                    k++;
                }

                F[start][end] = (k != end) ? 1 : 0;
            } else {
                F[start][end] = 0;
            }

            return F[start][end];
        }
    }
}
