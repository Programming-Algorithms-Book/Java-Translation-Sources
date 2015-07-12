import java.util.Random;

public class Program {
    private static final int N = 100;

    public static void main(String[] args) {
        Node l = generate(N);
        System.out.println("Преди сортирането:");
        printList(l);
        System.out.println();
        l = mergeSort(l, N);
        System.out.println("След сортирането:");
        printList(l);
    }

    private static Node generate(int n) {
        Random rand = new Random();
        Node p = null;
        for (int i = 0; i < n; i++) {
            Node q = new Node(rand.nextInt() % ((2 * n) + 1), p);
            p = q;
        }

        return p;
    }

    /* Извежда списъка на екрана */
    private static void printList(Node p) {
        for (; p != null; p = p.getNext()) {
            System.out.printf("%1$5d", p.getValue());
        }
    }

    private static Node mergeSort(Node c, int n) {
        /* Ако списъкът съдържа само един елемент: не се прави нищо */
        if (n < 2) {
            return c;
        }

        Node a = c;
        int n2 = n / 2;
        /* Разделяне на списъка на две части */
        for (int i = 2; i <= n2; i++) {
            c = c.getNext();
        }

        Node b = c.getNext();
        c.setNext(null);

        /* Сортиране поотделно на двете части, последвано от сливане */
        return merge(mergeSort(a, n2), mergeSort(b, n - n2));
    }

    private static Node merge(Node a, Node b) {
        /* Предполага се, че и двата списъка съдържат поне по един елемент */
        Node tail = new Node();
        Node head = tail;
        while (true) {
            if (a.getValue() < b.getValue()) {
                tail.setNext(a);
                a = a.getNext();
                tail = tail.getNext();
                if (a == null) {
                    tail.setNext(b);
                    break;
                }
            } else {
                tail.setNext(b);
                b = b.getNext();
                tail = tail.getNext();
                if (b == null) {
                    tail.setNext(a);
                    break;
                }
            }
        }

        return head.getNext();
    }
}
