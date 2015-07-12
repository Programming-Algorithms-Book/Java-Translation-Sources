public class Program {
    public static void heapSort(int[] input) {
        // Build-Max-Heap
        int heapSize = input.length;
        for (int p = (heapSize - 1) / 2; p >= 0; p--) {
            maxHeapify(input, heapSize, p);
        }

        for (int i = input.length - 1; i > 0; i--) {
            // Swap
            int temp = input[i];
            input[i] = input[0];
            input[0] = temp;

            heapSize--;
            maxHeapify(input, heapSize, 0);
        }
    }

    public static void main(String[] args) {
        int[] array = { 1, 4, 2, 3, 4, 2, 6, 5, 4, 4, 4, 4, 4 };
        int majority = findMajority(array);
        if (majority != 0) {
            System.out.printf("Мажорант: %s\n", majority);
        } else {
            System.out.println("Няма мажорант.");
        }
    }

    private static void maxHeapify(int[] input, int heapSize, int index) {
        int left = ((index + 1) * 2) - 1;
        int right = (index + 1) * 2;
        int largest = 0;

        if (left < heapSize && input[left] > input[index]) {
            largest = left;
        } else {
            largest = index;
        }

        if (right < heapSize && input[right] > input[largest]) {
            largest = right;
        }

        if (largest != index) {
            int temp = input[index];
            input[index] = input[largest];
            input[largest] = temp;

            maxHeapify(input, heapSize, largest);
        }
    }

    private static int findMajority(int[] array) {
        int majority = 0;
        int size2 = array.length / 2;
        heapSort(array);
        if (countElements(array, array[size2]) > size2) {
            majority = array[size2];
        }

        return majority;
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
}
