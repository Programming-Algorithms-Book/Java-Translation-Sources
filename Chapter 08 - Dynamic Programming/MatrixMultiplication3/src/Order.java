public class Order {
    private int left;
    private int right;

    public Order(int left, int right) {
        this.setLeft(left);
        this.setRight(right);
    }

    public int getLeft() {
        return this.left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return this.right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
