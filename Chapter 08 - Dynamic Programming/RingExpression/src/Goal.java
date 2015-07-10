public class Goal {
    private long min;
    private long max;
    private int lenMin;
    private int lenMax;

    public Goal(long min, long max, int lenMin, int lenMax) {
        this.setMin(min);
        this.setMax(max);
        this.setLenMin(lenMin);
        this.setLenMax(lenMax);
    }

    public long getMin() {
        return this.min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public int getLenMin() {
        return this.lenMin;
    }

    public void setLenMin(int lenMin) {
        this.lenMin = lenMin;
    }

    public int getLenMax() {
        return this.lenMax;
    }

    public void setLenMax(int lenMax) {
        this.lenMax = lenMax;
    }
}
