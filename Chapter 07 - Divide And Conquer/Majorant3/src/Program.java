public class Program {
    private static final char DEFAULT_CHAR_VALUE = '\u0000';

    public static void main(String[] args) {
        char[] array = { 'A', 'C', 'B', 'C', 'B', 'B', 'B', 'C', 'B', 'C', 'B',
                'B', 'A' };
        char majority = findMajority(array);
        if (majority != DEFAULT_CHAR_VALUE) {
            System.out.printf("Мажорант: %s\n", majority);
        } else {
            System.out.println("Няма мажорант.");
        }
    }

    private static char findMajority(char[] array) {
        char majority = DEFAULT_CHAR_VALUE;
        int size2 = array.length / 2;
        for (int i = 0; i < size2; i++) {
            int counter = 0;
            for (int j = i; j < array.length; j++) {
                if (counter + array.length - j <= size2) {
                    break;
                } else if (array[i] == array[j]) {
                    counter++;
                }
            }

            if (counter > size2) {
                majority = array[i];
            }
        }

        return majority;
    }
}
