public class Program {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(57);
        stack.push(6);
        stack.push(111);
        stack.push(256);
        stack.push(8192);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}