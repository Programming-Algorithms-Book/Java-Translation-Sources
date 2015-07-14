
public class List {
    private Element data;
    private List next;
    
    public List(Element data, List next) {
        this.setData(data);
        this.setNext(next);
    }
    
    public Element getData() {
        return this.data;
    }
    
    public void setData(Element data) {
        this.data = data;
    }
    
    public List getNext() {
        return this.next;
    }
    
    public void setNext(List next) {
        this.next = next;
    }
}
