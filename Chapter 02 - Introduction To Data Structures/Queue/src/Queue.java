import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Queue<T> {
    private static final int INITIAL_CAPACITY = 16;

    private int capacity;
    private int front;
    private int rear;
    private Object[] items;

    public Queue() {
        this.capacity = INITIAL_CAPACITY;
        this.front = 0;
        this.rear = 0;
        this.items = new Object[this.capacity];
    }

    public void enqueue(T item) {
        this.ensureCapacity();

        throw new NotImplementedException();
    }

    public T dequeue() {
        throw new NotImplementedException();
    }

    public int size() {
        throw new NotImplementedException();
    }

    public boolean isEmpty() {
        throw new NotImplementedException();
    }

    private void ensureCapacity() {
        throw new NotImplementedException();
    }
}
