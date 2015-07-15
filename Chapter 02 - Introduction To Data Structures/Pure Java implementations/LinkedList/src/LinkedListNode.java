public class LinkedListNode<T> {
    private T value;
    private LinkedListNode<T> previous;
    private LinkedListNode<T> next;
    private LinkedList<T> list;

    public LinkedListNode(T value) {
        this.setValue(value);
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedListNode<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(LinkedListNode<T> previous) {
        this.previous = previous;
    }

    public LinkedListNode<T> getNext() {
        return this.next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedList<T> getList() {
        return this.list;
    }

    public void setList(LinkedList<T> list) {
        this.list = list;
    }
}
