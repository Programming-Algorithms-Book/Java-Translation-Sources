public class Node { 
    private int value;
    private Node next;

    public Node() {
    }
    
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
}
