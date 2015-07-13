public class Arc implements Comparable<Arc> {

    private int vertex1;
    private int vertex2;
    private int weight;

    public Arc(int vertex1, int vertex2, int weight) {
        this.setVertex1(vertex1);
        this.setVertex2(vertex2);
        this.setWeight(weight);
    }

    public int getVertex1() {
        return this.vertex1;
    }

    public void setVertex1(int vertex1) {
        this.vertex1 = vertex1;
    }

    public int getVertex2() {
        return this.vertex2;
    }

    public void setVertex2(int vertex2) {
        this.vertex2 = vertex2;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Arc other) {
        return Integer.compare(this.getWeight(), other.getWeight());
    }
}
