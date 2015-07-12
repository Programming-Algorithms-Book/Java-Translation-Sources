import java.util.Random;

public class Program {
    private static final int N = 100;

    public static void main(String[] args) {
        Node l = generate(N);
        System.out.println("Преди сортирането:");
        printList(l);
        System.out.println();

        /* Предполага се, че списъкът съдържа поне 1 елемент */
        l = mergeSort(l);
        System.out.println("След сортирането:");
        printList(l);
    }

    /* Генерира примерно множество */
    private static Node generate(int n) {
        Random rand = new Random();
        Node p = Node.getZ();
        for (int i = 0; i < n; i++) {
            Node q = new Node(rand.nextInt() % ((2 * n) + 1), p);
            p = q;
        }

        return p;
    }

    /* Извежда списъка на екрана */
    private static void printList(Node p) {
        for (; p != Node.getZ(); p = p.getNext()) {
            System.out.printf("%1$5d", p.getValue());
        }
    }

    private static Node merge(Node a, Node b) {
        /* Предполага се, че и двата списъка съдържат поне по един елемент */
        Node tail = Node.getZ();
        do {
            if (a.getValue() < b.getValue()) {
                tail.setNext(a);
                tail = a;
                a = a.getNext();
            } else {
                tail.setNext(b);
                tail = b;
                b = b.getNext();
            }
        } while (tail != Node.getZ());
        tail = Node.getZ().getNext();
        Node.getZ().setNext(Node.getZ());
        return tail;
    }

    private static Node mergeSort(Node c) {
        /* Ако списъкът съдържа само един елемент: не се прави нищо */
        if (c.getNext() == Node.getZ()) {
            return c;
        }

        Node a = c;
        Node b = c.getNext().getNext().getNext();
        /* Списъкът се разделя на две части */
        while (b != Node.getZ()) {
            b = b.getNext().getNext();
            c = c.getNext();
        }

        b = c.getNext();
        c.setNext(Node.getZ());

        /* Сортиране поотделно на двете части, последвано от сливане */
        return merge(mergeSort(a), mergeSort(b));
    }
}
