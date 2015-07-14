public class Program {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(57);
        stack.push(6);
        stack.push(111);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}