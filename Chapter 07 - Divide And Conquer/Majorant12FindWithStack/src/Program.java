import java.util.Stack;

public class Program {
    private static final char DEFAULT_CHAR_VALUE = '\u0000';

    public static void main(String[] args) {
        char[] array = { 'A', 'D', 'A', 'B', 'A', 'B', 'A', 'A', 'B', 'A', 'B',
                'A', 'C', };

        Character majority = findMajority(array);
        if (majority != null) {
            System.out.printf("Мажорант: %d\n", majority);
        } else {
            System.out.println("Няма мажорант.");
        }
    }

    private static Character findMajority(char[] array) {
        char majority = DEFAULT_CHAR_VALUE;
        int size = array.length;
        Stack<Character> stack = new Stack<Character>();
        stack.push(array[0]);
        for (int i = 1; i < size; i++) {
            if (stack.size() == 0) {
                stack.push(array[i]);
            } else if (stack.peek() == array[i]) {
                stack.push(array[i]);
            } else {
                stack.pop();
            }
        }

        if (stack.size() == 0) {
            return null;
        }

        majority = stack.pop();
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == majority) {
                counter++;
            }
        }

        boolean isThereMajority = counter > size / 2;
        return isThereMajority ? majority : null;
    }
}
