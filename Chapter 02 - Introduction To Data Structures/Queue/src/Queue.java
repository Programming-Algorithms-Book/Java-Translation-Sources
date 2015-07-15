import java.util.Arrays;

public class Queue<T> {
    private static final int INITIAL_CAPACITY = 16;

    private int capacity;
    private int count;
    private int front;
    private int rear;
    private Object[] items;

    public Queue() {
        this.capacity = INITIAL_CAPACITY;
        this.count = 0;
        this.front = 0;
        this.rear = 0;
        this.items = new Object[this.capacity];
    }

    public void enqueue(T item) {
        this.ensureCapacity();

        this.items[this.rear] = item;
        this.count++;
        this.rear++;

        if (this.rear == this.capacity) {
            this.rear = 0;
        }
    }

    public T dequeue() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot dequeue an empty queue.");
        }

        T item = (T) this.items[this.front];

        this.count--;
        this.front++;

        if (this.front == this.capacity) {
            this.front = 0;
        }

        return item;
    }

    public T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot peek an empty queue.");
        }

        return (T) this.items[this.front];
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    private void ensureCapacity() {
        // Проверка за препълване
        if (this.front == this.rear && !this.isEmpty()) {
            this.capacity *= 2;
            this.items = Arrays.copyOf(this.items, this.capacity);
            this.front = 0;
            this.rear = this.count;
        }
    }
}
