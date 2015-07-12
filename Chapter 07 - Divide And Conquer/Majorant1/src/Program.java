public class Program {
    private static final char DEFAULT_CHAR_VALUE = '\u0000';

    public static void main(String[] args) {
        char[] array = { 'A', 'C', 'C', 'C', 'C', 'B', 'B', 'C', 'C', 'C', 'B',
                'C', 'C' };
        char majority = DEFAULT_CHAR_VALUE;
        majority = findMajority(array);
        if (majority != DEFAULT_CHAR_VALUE) {
            System.out.printf("Мажорант: %s\n", majority);
        } else {
            System.out.println("Масивът няма мажорант.");
        }
    }

    private static int countElements(char[] array, char candidate) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == candidate) {
                counter++;
            }
        }

        return counter;
    }

    private static char findMajority(char[] array) {
        char majority = DEFAULT_CHAR_VALUE;
        int size = array.length / 2;
        for (int i = 0; i < array.length; i++) {
            if (countElements(array, array[i]) > size) {
                majority = array[i];
            }
        }

        return majority;
    }
}
