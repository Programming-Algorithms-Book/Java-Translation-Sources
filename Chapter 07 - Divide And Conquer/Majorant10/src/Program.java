public class Program {
    public static void Main() {
        char[] array = { 'A', 'C', 'A', 'C', 'C', 'B', 'B', 'C', 'C', 'C', 'B',
                'C', 'A', };
        char majority = findMajority(array);
        System.out.printf("Мажорант: %s\n", majority);
    }

    private static char findMajority(char[] array) {
        int size = array.length;
        do {
            int currentCounter = 0;
            for (int i = 1; i < size; i += 2) {
                if (array[i - 1] == array[i]) {
                    array[currentCounter++] = array[i];
                }
            }

            if ((currentCounter & 1) == 0) {
                array[currentCounter++] = array[size - 1];
            }

            size = currentCounter;
        } while (size > 1);
        return array[0];
    }
}
