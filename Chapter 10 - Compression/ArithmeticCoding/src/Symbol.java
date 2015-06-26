
public class Symbol {
    private double low;
    private double high;
    private char character;
    
    public Symbol(double low, double high, char character) {
        this.setLow(low);
        this.setHigh(high);
        this.setCharacter(character);
    }
    
    public double getLow() {
        return this.low;
    }
    
    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return this.high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public char getCharacter() {
        return this.character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
