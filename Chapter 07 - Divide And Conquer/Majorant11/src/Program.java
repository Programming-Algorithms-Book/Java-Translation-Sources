public class Program {
    public static void main(String[] args) {
        char[] array = { 'A', 'D', 'A', 'B', 'A', 'B', 'A', 'A', 'B', 'A', 'B',
                'A', 'C', };
        char majority = findMajority(array);
        System.out.printf("Мажорант: %s\n", majority);
    }

    private static char findMajority(char[] array) {
        int size = array.length;
        int[] count = new int[size];
        for (int i = 0; i < size; i++) {
            count[i] = 1;
        }

        do {
            int currentCounter = 0;
            int i;
            for (i = 1; i < size; i += 2) {
                if (array[i - 1] == (array[i])) {
                    count[currentCounter] = count[i - 1] + count[i];
                    array[currentCounter++] = array[i];
                } else if (count[i] > count[i - 1]) {
                    count[currentCounter] = count[i] - count[i - 1];
                    array[currentCounter++] = array[i];
                } else if (count[i] < count[i - 1]) {
                    count[currentCounter] = count[i - 1] - count[i];
                    array[currentCounter++] = array[i - 1];
                }
            }

            if ((size & 1) == 1) {
                count[currentCounter] = count[i - 1];
                array[currentCounter++] = array[i - 1];
            }

            size = currentCounter;
        } while (size > 1);
        return array[0];
    }
}
