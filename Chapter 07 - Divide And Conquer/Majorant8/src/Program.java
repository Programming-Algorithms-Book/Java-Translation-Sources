public class Program {
    public static void main(String[] args) {
        char[] array = { 'A', 'A', 'A', 'B', 'C', 'B', 'B', 'C', 'C', 'C', 'B',
                'C', 'C', };
        char majority = findMajority(array);
        System.out.printf("Мажорант: %s\n", majority);
    }

    private static char findMajority(char[] array) {
        int size = array.length;
        boolean part = false;
        do {
            int i;
            int currentCounter;
            for (currentCounter = 0, i = 1; i < size; i += 2) {
                if (array[i - 1] == array[i]) {
                    array[currentCounter++] = array[i];
                }
            }

            if (i == size) {
                array[currentCounter++] = array[i - 1];
                part = true;
            } else if (part || array[size - 2] == array[size - 1]) {
                array[currentCounter] = array[size - 2];
            } else {
                currentCounter--;
            }

            size = currentCounter;
        } while (size > 1);
        return array[0];
    }
}
