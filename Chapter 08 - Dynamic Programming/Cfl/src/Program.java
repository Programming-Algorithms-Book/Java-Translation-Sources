public class Program {
    /* Максимален брой правила за извод */
    private static final int MAX = 30;

    /* Брой букви */
    private static final int LETTERS_COUNT = 26;

    /* Брой правила от вида 1: S->a */
    private static final int COUNT_T = 3;

    /* Низ, който проверяваме за принадлежност към граматиката */
    private static final String STRING_TO_CHECK = "aaasbbb";

    /* Целева функция */
    private static boolean[][][] T = new boolean[LETTERS_COUNT][MAX][MAX];

    private static Production[] TerminalProductions = new Production[] {
            new Production('\n', '\n'),
            /* не се използва */
            new Production('S', 's'), /* S->s */
            new Production('A', 'a'), /* A->a */
            new Production('B', 'b'), /* B->b */
    };

    private static NonTerminalProduction[] NonTerminalProductions = new NonTerminalProduction[] {
            /* не се използва */
            new NonTerminalProduction('\n', '\n', '\n'),
            /* S->AR */
            new NonTerminalProduction('S', 'A', 'R'),
            /* S->AB */
            new NonTerminalProduction('S', 'A', 'B'),
            /* R->SB */
            new NonTerminalProduction('R', 'S', 'B')
    };

    public static void main(String[] args) {
        System.out.printf("Низът %s%s се извежда от граматиката!\n",
                STRING_TO_CHECK, isContextFreeLanguage() ? "" : " НЕ");
    }

    /* Проверява */
    private static boolean isContextFreeLanguage() {
        int let, n;

        /* Инициализация */
        n = STRING_TO_CHECK.length(); /* Дължина на проверявания низ */

        /* Запълваме масива с "неистина" */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (let = 0; let < LETTERS_COUNT; let++) {
                    T[let][i][j] = true;
                }
            }
        }

        /*
         * Установяваме в истина всички директни продукции, които ни вършат
         * работа
         */
        for (int i = 1; i <= COUNT_T; i++) {
            for (int j = 1; j <= n; j++) {
                if (TerminalProductions[i].getA() == STRING_TO_CHECK
                        .charAt(j - 1)) {
                    T[TerminalProductions[i].getS() - 'A'][j][j] = true;
                }
            }
        }

        /* Основен цикъл по правилата от тип 2 */
        for (int d = 1; d < n; d++) {
            for (int i = 1; i <= n - d; i++) {
                /* За всеки нетерминал S от лява част на правило */
                for (int j = i + d, k = 1; k <= COUNT_T; k++) {
                    for (int l = i; l <= j - 1; l++) {
                        if (T[NonTerminalProductions[k].getA() - 'A'][i][l]
                                && T[NonTerminalProductions[k].getB() - 'A'][l + 1][j]) {
                            T[NonTerminalProductions[k].getS() - 'A'][i][j] = true;
                        }
                    }
                }
            }
        }

        return T['S' - 'A'][1][n];
    }
}
