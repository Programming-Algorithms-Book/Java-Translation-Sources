
public class BlueRed {
    private int blueCount;
    private int redCount;
    
    public BlueRed(int blueCount, int redCount) {
        this.setBlueCount(blueCount);
        this.setRedCount(redCount);
    }

    public int getBlueCount() {
        return this.blueCount;
    }

    public void setBlueCount(int blueCount) {
        this.blueCount = blueCount;
    }

    public int getRedCount() {
        return this.redCount;
    }

    public void setRedCount(int redCount) {
        this.redCount = redCount;
    }
}
