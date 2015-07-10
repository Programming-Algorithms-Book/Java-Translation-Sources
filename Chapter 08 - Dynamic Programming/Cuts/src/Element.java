public class Element {
    private int value;
    private int action;
    
    public Element(int value, int action) {
        this.setValue(value);
        this.setAction(action);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
