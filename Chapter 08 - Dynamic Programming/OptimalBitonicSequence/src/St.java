public class St {
    /* Дължина на максималната ненамаляваща подредица, завършваща в i */
    private int length;

    /* Индекс на предишния елемент в макс. редица */
    private int back;

    /* Сума на елементите на максималната редица */
    private long sum;

    public St(int length, int back, long sum) {
        this.setLength(length);
        this.setBack(back);
        this.setSum(sum);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
