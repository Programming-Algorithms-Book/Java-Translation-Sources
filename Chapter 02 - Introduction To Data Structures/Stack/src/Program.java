import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("Въведете числа (0 за изход): ");

        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        while (number != 0) {
            stack.push(number);
            number = scanner.nextInt();
        }

        System.out.println("Въведените числа са:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
