public class NodeElement {
    private Element data;
    private NodeElement next;

    public NodeElement() {
        this.setData(new Element(0));
    }

    public Element getData() {
        return data;
    }

    public void setData(Element data) {
        this.data = data;
    }

    public NodeElement getNext() {
        return next;
    }

    public void setNext(NodeElement next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return Integer.toString(this.getData().getKey());
    }
}
