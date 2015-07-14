import java.util.Random;

public class Program {
    private static final int MAX_VALUE = 100;
    private static Random Rand = new Random();

    public static void main(String[] args) throws Exception {
        NodeElement head = initialize();
        System.out.println("Масива преди сортиране.");
        printList(head);
        System.out.println("Масива след сортиране.");
        head = bitwiseSort(head);
        check(head);
        printList(head);
    }

    private static NodeElement initialize() {
        NodeElement head = null;
        for (int i = 0; i < MAX_VALUE; i++) {
            NodeElement element = new NodeElement();
            element.getData().setKey(Rand.nextInt(MAX_VALUE * 2));
            element.setNext(head);
            head = element;
        }

        return head;
    }

    private static NodeElement bitwiseSort(NodeElement head) {
        /* 0. Определяне на максималната битова маска */
        long maxBit = (long) Integer.MAX_VALUE + 1;

        /* 1. Фиктивен елемент в началото на списъците */
        NodeElement zeroList = new NodeElement();
        NodeElement oneList = new NodeElement();
        NodeElement oneEndList;
        NodeElement zeroEndList;

        /* 2. Сортиране */
        for (long bitPower = 1; bitPower < maxBit; bitPower <<= 1) {
            /* 2.1. Разпределяне по списъци */
            for (zeroEndList = zeroList, oneEndList = oneList; head != null; head = head
                    .getNext()) {
                if ((head.getData().getKey() & bitPower) == bitPower) {
                    oneEndList.setNext(head);
                    oneEndList = oneEndList.getNext();
                } else {
                    zeroEndList.setNext(head);
                    zeroEndList = zeroEndList.getNext();
                }
            }

            /* 2.2. Обединение на списъците */
            oneEndList.setNext(null);
            zeroEndList.setNext(oneList.getNext());
            head = zeroList.getNext();
        }

        return head;
    }

    private static void printList(NodeElement head) {
        NodeElement firstElement = head;
        for (; head.getNext() != null; head = head.getNext()) {
            System.out.print(head.getData().getKey() + " ");
        }

        head = firstElement;
    }

    private static void check(NodeElement head) throws Exception {
        for (; head.getNext() != null; head = head.getNext()) {
            if (head.getData().getKey() > head.getNext().getData().getKey()) {
                throw new Exception("Масива не е сортиран правилно.");
            }
        }
    }
}