import java.util.HashMap;

public class Program {
    private static final char DEFAULT_CHAR_VALUE = '\u0000';

    public static void main(String[] args) {
        char[] array = { 'A', 'A', 'A', 'C', 'C', 'B', 'B', 'C', 'C', 'C', 'B',
                'C', 'C', };
        char majority = findMajority(array);
        if (majority != DEFAULT_CHAR_VALUE) {
            System.out.printf("Мажорант: %s\n", majority);
        } else {
            System.out.println("Няма мажорант.");
        }
    }

    private static char findMajority(char[] array) {
        int arrayLength = array.length;
        int length2 = arrayLength / 2;
        char majority = DEFAULT_CHAR_VALUE;
        HashMap<Character, Integer> counter = new HashMap<Character, Integer>(
                arrayLength);

        /* Инициализация */
        for (int i = 0; i < arrayLength; i++) {
            if (!counter.containsKey(array[i])) {
                counter.put(array[i], 0);
            }
        }

        /* Броене */
        for (int j = 0; j < arrayLength; j++) {
            counter.replace(array[j], counter.get(array[j]) + 1);
        }

        /* Проверка за мажорант */
        for (int i = 0; i < arrayLength; i++) {
            if (counter.get(array[i]) > length2) {
                majority = array[i];
                break;
            }
        }

        return majority;
    }
}
