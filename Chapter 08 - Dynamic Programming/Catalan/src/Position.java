public class Position {
    private int pointX;
    private int pointY;

    public Position(int positionX, int positionY) {
        this.setPointX(positionX);
        this.setPointY(positionY);
    }

    public int getPointX() {
        return this.pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return this.pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }
}
