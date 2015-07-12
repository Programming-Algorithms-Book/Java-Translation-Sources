public class Program {
    private static final char DEFAULT_CHAR_VALUE = '\u0000';

    public static void main(String[] args) {
        char[] array = { 'A', 'A', 'A', 'C', 'C', 'B', 'B', 'C', 'C', 'C', 'B',
                'C', 'C', };
        Character majority = findMajority(array, 0, array.length - 1);
        if (majority != null) {
            System.out.printf("Мажорант: %s\n", majority);
        } else {
            System.out.println("Няма мажорант.");
        }
    }

    private static int count(char[] array, int left, int right, char candidate) {
        int counter = 0;
        for (; left <= right; left++) {
            if (array[left] == candidate) {
                counter++;
            }
        }

        return counter;
    }

    private static Character findMajority(char[] array, int left, int right) {
        Character majority = DEFAULT_CHAR_VALUE;
        if (left == right) {
            majority = array[left];
            return majority;
        }

        int middle = (left + right) / 2;

        majority = findMajority(array, left, middle);

        if (majority != null) {
            if (count(array, left, right, majority) > (right - left + 1) / 2) {
                return majority;
            }
        }

        majority = findMajority(array, middle + 1, right);

        if (majority != null) {
            if (count(array, left, right, majority) > (right - left + 1) / 2) {
                return majority;
            }
        }

        return null;
    }
}
