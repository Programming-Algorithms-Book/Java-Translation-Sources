import java.util.Random;

import javax.management.OperationsException;

public class Program {
    private static final int N = 12;
    private static final int M = 14;
    private static Random Rand = new Random();

    public static void main(String[] args) {
        Element[] a = new Element[N];
        Element[] b = new Element[M];
        Element[] c = new Element[N + M];
        initElementsArray(a);
        initElementsArray(b);
        initElementsArray(c);
        initializeArray(a, 200, 20);
        initializeArray(b, 200, 20);
        System.out.println("Преди сливането:");
        System.out.println("Масивът A:");
        printArrays(a);
        System.out.println("Масивът B:");
        printArrays(b);
        binaryMerge(a, b, c);
        System.out.println("След сливането C:");
        printArrays(c);
    }

    private static void initializeArray(Element[] array, int modul1, int modul2) {
        array[0].setKey(Rand.nextInt() % modul1);
        ;
        for (int i = 1; i < array.length; i++) {
            array[i].setKey(array[i - 1].getKey() + (Rand.nextInt() % modul2));
        }
    }

    private static int binarySearch(Element[] elements, int left, int right,
            Element element) {
        do {
            int middle = (left + right) / 2;
            if (elements[middle].getKey() < element.getKey()) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        } while (left <= right);

        return right;
    }

    private static void binaryMerge(Element[] a, Element[] b, Element[] c) {
        int n = a.length;
        int m = b.length;
        int power, elementsCount;
        int totalLength = a.length + b.length;
        int k;
        while (n > 0 && m > 0) {
            power = (int) (Math.log(n / m) / Math.log(2));
            elementsCount = 1 << power; /* elementsCount <-- 2^power */
            if (m <= n) {
                if (b[m - 1].getKey() < a[n - elementsCount].getKey()) {
                    /*
                     * Прехвърляне на a[n-t2-1],...,a[n] в изходната
                     * последователност
                     */
                    totalLength -= elementsCount;
                    n = elementsCount;
                    for (int j = 0; j < elementsCount; j++) {
                        c[totalLength + j] = a[n + j];
                    }
                } else {
                    k = binarySearch(a, n - elementsCount, n - 1, b[m - 1]);
                    for (int j = 0; j < n - k - 1; j++) {
                        c[totalLength - n + k + j + 1] = a[k + j + 1];
                    }

                    totalLength -= n - k - 1;
                    n = k + 1;
                    c[--totalLength] = b[--m];
                }
            } else {
                if (a[n - 1].getKey() < b[m - elementsCount].getKey()) {
                    totalLength -= elementsCount;
                    m -= elementsCount;
                    for (int j = 0; j < elementsCount; j++) {
                        c[totalLength + j] = b[m + j];
                    }
                } else {
                    k = binarySearch(b, m - elementsCount, m - 1, a[n - 1]);
                    for (int j = 0; j < m - k - 1; j++) {
                        c[totalLength - m + k + j + 1] = b[k + j + 1];
                    }

                    totalLength -= m - k - 1;
                    m = k + 1;
                    c[--totalLength] = a[--n];
                }
            }
        }

        if (n == 0) {
            for (int i = 0; i < m; i++) {
                c[i] = b[i];
            }
        } else {
            for (int i = 0; i < n; i++) {
                c[i] = a[i];
            }
        }
    }

    private static void printArrays(Element[] list) /*
                                                     * Извежда ключовете на
                                                     * масива на екрана
                                                     */
    {
        for (Element element : list) {
            System.out.printf("%d ", element.getKey());
        }

        System.out.println();
    }

    private static void check(Element[] elemets) throws OperationsException /*
                                                                             * Проверява
                                                                             * за
                                                                             * възходящ
                                                                             * ред
                                                                             */
    {
        for (int i = 1; i < elemets.length; i++) {
            if (elemets[i - 1].getKey() <= elemets[i].getKey()) {
                throw new OperationsException("Wrong order");
            }
        }
    }

    private static void initElementsArray(Element[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Element(0);
        }
    }
}
