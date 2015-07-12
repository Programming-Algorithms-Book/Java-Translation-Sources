
public class CList {
    private int point;
    private Element[] data;
    private CList next;
    private int length;
    
    public CList() {
        this.setPoint(0);
        this.setLength(0);
    }
    
    public CList(int point, Element[] data, int length) {
        this.setPoint(point);
        this.setData(data);
        this.setLength(length);
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Element[] getData() {
        return this.data;
    }

    public void setData(Element[] data) {
        this.data = data;
    }

    public CList getNext() {
        return this.next;
    }

    public void setNext(CList next) {
        this.next = next;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public CList getCopy() {
        CList copy = new CList();
        copy.setLength(this.getLength());
        copy.setPoint(this.getPoint());
        copy.setData(this.getData() != null ? this.getData().clone() : null);
        copy.setNext(this.getNext());
        return copy;
    }
}
