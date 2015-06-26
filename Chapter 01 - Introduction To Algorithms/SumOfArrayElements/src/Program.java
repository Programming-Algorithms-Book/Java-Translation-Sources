import java.util.StringJoiner;

public class Program {
    public static void main(String[] args) {
        int[] elements = new int[] { -5, 8, 22, 251, -158, 73 };
        long sum = getSum(elements);

        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < elements.length; i++) {
            joiner.add(Integer.toString(elements[i]));
        }

        System.out.printf("Сумата на масива с елементи %s е %d\n",
                joiner.toString(), sum);
    }

    private static long getSum(int[] elements) {
        long sum = 0L;
        for (int i = 0; i < elements.length; i++) {
            sum += elements[i];
        }

        return sum;
    }
}
