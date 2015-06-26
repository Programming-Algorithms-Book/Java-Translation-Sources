import java.util.StringJoiner;

public class Program {
    public static void main(String[] args) {
        int[] elements = new int[] { -5, 8, 22, 25, -158, 73 };
        long product = getProduct(elements);

        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < elements.length; i++) {
            joiner.add(Integer.toString(elements[i]));
        }

        System.out.printf("Произведението на масива с елементи %s е %d\n",
                joiner.toString(), product);
    }

    private static long getProduct(int[] elements) {
        long product = 1L;
        for (int i = 0; i < elements.length; i++) {
            product *= elements[i];
        }

        return product;
    }
}
