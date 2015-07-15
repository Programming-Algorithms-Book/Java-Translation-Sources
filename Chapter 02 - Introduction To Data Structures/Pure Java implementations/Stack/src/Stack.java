import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> {
    private static final int INITIAL_CAPACITY = 16;

    private int capacity;
    private int top;
    private Object[] items;

    public Stack() {
        this.capacity = INITIAL_CAPACITY;
        this.top = 0;
        this.items = new Object[this.capacity];
    }

    public void push(T item) {
        this.ensureCapacity();

        this.items[this.top] = item;
        this.top++;
    }

    public T pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        this.top--;
        return (T) this.items[this.top];
    }

    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        return (T) this.items[this.top - 1];
    }

    public int size() {
        return this.top;
    }

    public boolean isEmpty() {
        return this.top == 0;
    }

    private void ensureCapacity() {
        if (this.capacity < this.top + 1) {
            this.capacity *= 2;
            this.items = Arrays.copyOf(this.items, this.capacity);
        }
    }
}
