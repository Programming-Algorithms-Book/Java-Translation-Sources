
public class Result {
    private long firstValue;
    private long secondValue;
    
    public Result(long firstValue, long secondValue) {
        this.setFirstValue(firstValue);
        this.setSecondValue(secondValue);
    }
    
    public long getFirstValue() {
        return firstValue;
    }
    
    public void setFirstValue(long firstValue) {
        this.firstValue = firstValue;
    }
    
    public long getSecondValue() {
        return secondValue;
    }
    
    public void setSecondValue(long secondValue) {
        this.secondValue = secondValue;
    }
}
