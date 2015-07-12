public class Result {
    private int min;
    private int max;

    public Result(int min, int max) {
        this.setMin(min);
        this.setMax(max);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
