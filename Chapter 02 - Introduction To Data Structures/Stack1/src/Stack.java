public class Stack<T> {
    private T items;
    private int top;

    public Stack() {
        this.items = new T[10];
        this.top = 0;
    }

    public void push(T item) {
        this.items[top] = item;
        this.top++;
    }

    public T pop() {
        this.top--;
        return this.items[];
    }

    public boolean isEmpty() {
        return this.top == 0;
    }
}