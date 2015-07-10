public class Program {
    private static final String S = "bacacbcabbbcacab";
    private static final int LettersCount = 3; /* Брой букви */

    /* Таблица на умножение */
    private static char[][] Rel = { { 'b', 'b', 'a' }, { 'c', 'b', 'a' },
            { 'a', 'c', 'c' } };

    private static Boolean NotCalculated = null;
    private static Boolean[][][] Table = new Boolean[S.length()][S.length()][LettersCount];
    private static int[][] Split = new int[S.length()][S.length()];

    public static void main(String[] args) {
        int len = S.length();

        if (can(0, len - 1, 0)) {
            putBrackets(0, len - 1);
        } else {
            System.out.print("Няма решение");
        }
    }

    private static boolean can(int i, int j, int ch) {
        int c1, c2, pos;
        if (Table[i][j][ch] != NotCalculated) {
            return Table[i][j][ch]; /* Вече сметнато */
        }

        if (i == j) {
            return S.charAt(i) == (ch + 'a');
        }

        for (c1 = 0; c1 < LettersCount; c1++) {
            for (c2 = 0; c2 < LettersCount; c2++) {
                if (Rel[c1][c2] == ch + 'a') {
                    for (pos = i; pos <= j - 1; pos++) {
                        if (can(i, pos, c1)) {
                            if (can(pos + 1, j, c2)) {
                                Table[i][j][ch] = true;
                                Split[i][j] = pos;
                                return true;
                            }
                        }
                    }
                }
            }
        }

        Table[i][j][ch] = false;
        return false;
    }

    private static void putBrackets(int i, int j) {
        /* Поставя скобите с израза */
        if (i == j) {
            System.out.print(S.charAt(i));
        } else {
            System.out.print("(");
            putBrackets(i, Split[i][j]);
            System.out.print("*");
            putBrackets(Split[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
