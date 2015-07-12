import java.util.Random;

public class Program {
    private static Random Rand = new Random();

    /* Запълва масива със случайни числа */
    public static void initializeArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Rand.nextInt(Integer.MAX_VALUE)
                    % ((2 * numbers.length) + 1);
        }
    }

    /* Извежда масива на екрана */
    public static void printArray(int[] numbers) {
        for (int number : numbers) {
            System.out.printf("%d ", number);
        }

        System.out.println();
    }

    /* Намира максималния елемент */
    public static int findMaxElement(int[] numbers) {
        int maxElement = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxElement) {
                maxElement = numbers[i];
            }
        }

        return maxElement;
    }

    /* Намира минималния елемент */
    public static int findMinElement(int[] numbers) {
        int minElement = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minElement) {
                minElement = numbers[i];
            }
        }

        return minElement;
    }

    /* Намира едновременно максималния и минималния елементи */
    public static Result findMinAndMax(int[] numbers, int n) {
        // n = 20
        int n2 = n / 2;
        int min = numbers[n2];
        int max = numbers[n2];
        for (int i = 0; i < n2; i++) {
            if (numbers[i] > numbers[n - i - 1]) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }

                if (numbers[n - i - 1] < min) {
                    min = numbers[n - i - 1];
                }
            } else {
                if (numbers[n - i - 1] > max) {
                    max = numbers[n - i - 1];
                }

                if (numbers[i] < min) {
                    min = numbers[i];
                }
            }
        }

        return new Result(min, max);
    }

    /* Намира втория по големина елемент */
    public static int findSecondMax(int[] numbers, int n) {
        int x = numbers[0];
        int y = numbers[1];
        if (y > x) {
            int oldValue = x;
            x = y;
            y = oldValue;
        }

        for (int i = 2; i < n; i++) {
            if (numbers[i] > y) {
                y = numbers[i];
                if (y > x) {
                    int oldValue = x;
                    x = y;
                    y = oldValue;
                }
            }
        }

        return y;
    }

    public static void main(String[] args) {
        int arrayLength = 10;
        int[] array = new int[arrayLength];
        initializeArray(array);

        System.out.println("Масивът:");
        printArray(array);

        System.out.printf("Максимален елемент: %d\n", findMaxElement(array));
        System.out.printf("Минимален елемент: %d\n", findMinElement(array));

        Result result = findMinAndMax(array, arrayLength);
        System.out.printf(
                "Едновременно максимален: %d и минимален: %d елемент.\n",
                result.getMin(), result.getMax());

        System.out.printf("Втори по големина елемент: %d\n",
                findSecondMax(array, arrayLength));
    }
}
