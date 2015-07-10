public class Element {
    private int number;
    private int last;

    public Element(int number, int last) {
        this.setNumber(number);
        this.setLast(last);
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLast() {
        return this.last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}
