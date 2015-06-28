public class Program {
    private static int[][] A = { { 0, 1, 0, 0, 0, 0 }, { 1, 0, 1, 0, 0, 1 },
            { 0, 1, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0 },
            { 0, 1, 0, 1, 0, 0 } };

    private static int Rows = A.length;

    public static void main(String[] args) {
        System.out.println("Оцветяване на върховете по алгоритъм 1:");
        int[] colors1 = solve1();
        showColor(colors1);
        System.out.println("Оцветяване на върховете по алгоритъм 2:");
        int[] colors2 = solve2();
        showColor(colors2);
    }

    /* върховете се разглеждат в произволен, а не сортиран по степента им ред */

    private static int[] solve1() {
        int[] colors = new int[Rows];
        for (int i = 0; i < Rows; i++) {
            /* оцветява i-тия връх с най-малкият възможен цвят */

            int c = 0;
            boolean flag;
            do {
                c++;
                flag = true;
                for (int j = 0; j < i; j++) {
                    if (A[i][j] == 1 && colors[j] == c) {
                        flag = false;
                        break;
                    }
                }
            } while (!flag);
            colors[i] = c;
        }

        return colors;
    }

    private static int[] solve2() {
        int c = 0, cn = 0;
        int[] colors = new int[Rows];
        /*
         * оцветява върхове само с първия цвят, докато е възможно, след това
         * само с втория и т.н., докато всички върхове бъдат оцветени
         */
        while (cn < Rows) {
            c++;
            for (int i = 0; i < Rows; i++) {
                if (colors[i] == 0) {
                    boolean flag = true;
                    for (int j = 0; j < Rows; j++) {
                        if (A[i][j] == 1 && colors[j] == c) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        colors[i] = c;
                        cn++;
                    }
                }
            }
        }

        return colors;
    }

    private static void showColor(int[] colors) {
        for (int i = 0; i < colors.length; i++) {
            System.out.printf("%d--%d; ", i + 1, colors[i]);
        }

        System.out.println();
    }
}
