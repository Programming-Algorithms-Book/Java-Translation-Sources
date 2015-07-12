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
        Node head = new Node(0, c);
        head.setNext(c);
        Node a = Node.getZ();
        for (int n = 1; a != head.getNext(); n <<= 1) {
            Node todo = head.getNext();
            c = head;
            while (todo != Node.getZ()) {
                Node t = todo;

                /* Отделяне на a[] */
                a = t;
                for (int i = 1; i < n; i++) {
                    t = t.getNext();
                }

                /* Отделяне на b[] */
                Node b = t.getNext();
                t.setNext(Node.getZ());
                t = b;
                for (int i = 1; i < n; i++) {
                    t = t.getNext();
                }

                /* Сливане на a[] и b[] */
                todo = t.getNext();
                t.setNext(Node.getZ());
                c.setNext(merge(a, b));

                /* Пропускане на слетия масив */
                for (int i = 1; i <= n * 2; i++) {
                    c = c.getNext();
                }
            }
        }

        return head.getNext();
    }
}
