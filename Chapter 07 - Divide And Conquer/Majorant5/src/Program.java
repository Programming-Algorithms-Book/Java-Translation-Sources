public class Program {
    public static void main(String[] args) {
        int[] array = { 1, 4, 2, 3, 4, 2, 6, 5, 4, 4, 4, 4, 4 };
        int majority = findMajority(array);
        if (majority != 0) {
            System.out.printf("Мажорант: %d\n", majority);
        } else {
            System.out.println("Няма мажорант.");
        }
    }

    private static int countElements(int[] array, int candidate) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == candidate) {
                counter++;
            }
        }

        return counter;
    }

    private static int findMajority(int[] array) {
        int majority = 0;
        int median = findMedian(array);
        if (countElements(array, median) > array.length / 2) {
            majority = median;
        }

        return majority;
    }

    private static int findMedian(int[] array) {
        // TODO: Implement blum, floyd prat, rivest algorithm for finding
        return array[0];
    }
}
