public class Element {
    private int key;
    private int value;
    private Element next;
    
    public Element(int key, int value) {
        this.setKey(key);
        this.setValue(value);
    }
    
    public Element(int key, int value, Element next) {
        this(key, value);
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

    public Element getNext() {
        return this.next;
    }

    public void setNext(Element next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }
}
