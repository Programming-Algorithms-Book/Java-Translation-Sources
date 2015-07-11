public class Element<T> {
    private int key;
    private T value;
    
    public Element(int key, T value) {
        this.setKey(key);
        this.setValue(value);
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
