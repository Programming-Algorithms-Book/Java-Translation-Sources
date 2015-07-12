public class Program {
    public static void main(String[] args) {
        int numberOfDisks = 5;
        System.out.printf("Брой дискове: %d\n", numberOfDisks);
        solveHanoy('A', 'C', 'B', numberOfDisks);
    }

    private static void moveDisk(int n, char a, char b) {
        System.out.printf("Преместете диск %d от %s на %s\n", n, a, b);
    }

    private static void solveHanoy(char a, char c, char b, int n) {
        if (n == 1) {
            moveDisk(1, a, c);
        } else {
            solveHanoy(a, b, c, n - 1);
            moveDisk(n, a, c);
            solveHanoy(b, c, a, n - 1);
        }
    }
}
