import java.util.Stack;

public class Program {
    private static final int N = 7892;

    public static void main(String[] args) {
        Stack<Integer> digits = new Stack<>();
        int number = N;
        while (number > 0) {
            digits.push(number % 10);
            number /= 10;
        }

        while (digits.size() > 0) {
            System.out.print(digits.pop());
        }

        System.out.println();
    }
}
