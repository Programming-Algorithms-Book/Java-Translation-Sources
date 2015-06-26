import java.util.ArrayList;
import java.util.List;

public class HeapForest {
    private List<Tree> list = new ArrayList<Tree>();

    public int getSize() {
        return this.list.size();
    }

    public Tree getMin() {
        Tree first = this.list.get(0);
        this.list.set(0, this.list.get(this.list.size() - 1));
        this.list.remove(this.list.size() - 1);
        this.siftDown(0);
        return first;
    }

    public void add(Tree elem) {
        this.list.add(elem);
        this.siftUp(this.list.size() - 1);
    }

    private void siftUp(int index) {
        int parent = (index / 2) - 1 + (index % 2);
        if (parent < 0) {
            return;
        }

        if (this.list.get(parent).getWeight() <= this.list.get(index)
                .getWeight()) {
            return;
        }

        Tree temp = this.list.get(index);
        this.list.set(index, this.list.get(parent));
        this.list.set(parent, temp);
        this.siftUp(parent);
    }

    private void siftDown(int index) {
        int minChild = this.minChild(index);
        if (minChild == -1) {
            return;
        }

        if (this.list.get(index).getWeight() <= this.list.get(minChild)
                .getWeight()) {
            return;
        }

        Tree temp = this.list.get(index);
        this.list.set(index, this.list.get(minChild));
        this.list.set(minChild, temp);
        this.siftDown(minChild);
    }

    private int minChild(int index) {
        int left = (index * 2) + 1;
        if (left >= this.list.size()) {
            return -1;
        }

        if (left == this.list.size() - 1
                || this.list.get(left).getWeight() <= this.list.get(left + 1)
                        .getWeight()) {
            return left;
        }

        return left + 1;
    }
}
