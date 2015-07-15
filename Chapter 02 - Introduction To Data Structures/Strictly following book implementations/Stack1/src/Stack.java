public class Stack<T> {
    private static final int CAPACITY = 10;

    private Object[] items;
    private int top;

    public Stack() {
        this.items = new Object[CAPACITY];
        this.top = 0;
    }

    public void push(T item) {
        this.items[top] = item;
        this.top++;
    }

    public T pop() {
        this.top--;
        return (T)this.items[this.top];
    }

    public boolean isEmpty() {
        return this.top == 0;
    }
}