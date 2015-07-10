
public class Dist {
    private int last;
    private int value;
    
    public Dist(int last, int value) {
        this.setLast(last);
        this.setValue(value);
    }

    public int getLast() {
        return this.last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
