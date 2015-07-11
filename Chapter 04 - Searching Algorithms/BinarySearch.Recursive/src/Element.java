public class Element {
    private int key;
    private int value;
    
    public Element(int key, int value) {
        this.setKey(key);
        this.setValue(value);
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
