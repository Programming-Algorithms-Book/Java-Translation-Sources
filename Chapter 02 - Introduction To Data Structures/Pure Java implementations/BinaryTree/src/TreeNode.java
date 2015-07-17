public class TreeNode<TKey, TValue> {
    private TKey key;
    private TValue value;
    private TreeNode<TKey, TValue> parent;
    private TreeNode<TKey, TValue> leftChild;
    private TreeNode<TKey, TValue> rightChild;

    public TreeNode(TKey key, TValue value) {
        this.setKey(key);
        this.setValue(value);
    }

    public TKey getKey() {
        return this.key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public TValue getValue() {
        return this.value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }

    public TreeNode<TKey, TValue> getParent() {
        return this.parent;
    }

    public void setParent(TreeNode<TKey, TValue> parent) {
        this.parent = parent;
    }

    public TreeNode<TKey, TValue> getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(TreeNode<TKey, TValue> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<TKey, TValue> getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(TreeNode<TKey, TValue> rightChild) {
        this.rightChild = rightChild;
    }
}
