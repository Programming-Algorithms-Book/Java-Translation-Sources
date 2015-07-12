public class Node {
    private static Node z;
    
    static {
        z = new Node(Integer.MAX_VALUE, null);
        z.setNext(z);
    }
    
    private int value;
    private Node next;

    public Node(int value, Node next) {
        this.setValue(value);
        this.setNext(next);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static Node getZ() {
        return z;
    }
}
