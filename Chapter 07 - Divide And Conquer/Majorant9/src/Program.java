public class Program {
    private static final char DEFAULT_CHAR_VALUE = '\u0000';

    public static void main(String[] args) {
        char[] array = { 'A', 'A', 'A', 'C', 'C', 'C', 'B', 'C', 'B', 'C', 'C',
                'C', 'A', };
        char majority = findMajority(array);
        System.out.printf("Мажорант: %s\n", majority);
    }

    private static char findMajority(char[] array) {
        int size = array.length;
        char majority = DEFAULT_CHAR_VALUE;
        do {
            int currentCounter = 0;
            for (int i = 1; i < size; i += 2) {
                if (array[i - 1] == array[i]) {
                    array[currentCounter++] = array[i];
                }
            }

            if (size % 2 == 1) {
                majority = array[size - 1];
            }

            size = currentCounter;
        } while (size > 0);

        return majority;
    }
}
