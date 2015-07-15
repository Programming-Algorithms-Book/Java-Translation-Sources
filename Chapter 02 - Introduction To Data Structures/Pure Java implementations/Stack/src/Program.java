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

        System.out.println("\nБрой въведени числа: " + stack.size());

        System.out.println("\nНай-горен елемент в стека: " + stack.peek());

        System.out.println("\nВъведените числа са:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
