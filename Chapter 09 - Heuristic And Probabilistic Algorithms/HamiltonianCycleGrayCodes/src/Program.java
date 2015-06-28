import java.util.StringJoiner;

public class Program {
    public static void main(String[] args) {
        /* код на Грей, Хамилтонов цикъл в n-мерен двоичен куб (Хиперкуб) */
        final int DIMENSIONS = 3;
        int[] a = new int[DIMENSIONS + 1];
        forwgray(a, DIMENSIONS);
    }

    private static void forwgray(int[] a, int k) {
        if (k == 0) {
            print(a);
            return;
        }

        a[k] = 0;
        forwgray(a, k - 1);
        a[k] = 1;
        backgray(a, k - 1);
    }

    private static void backgray(int[] a, int k) {
        if (k == 0) {
            print(a);
            return;
        }

        a[k] = 1;
        forwgray(a, k - 1);
        a[k] = 0;
        backgray(a, k - 1);
    }

    private static void print(int[] a) {
        StringJoiner joiner = new StringJoiner(" ");

        for (int i = 1; i < a.length; i++) {
            joiner.add(Integer.toString(a[i]));
        }

        System.out.println(joiner.toString());
    }
}
