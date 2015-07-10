/* Продукции, отиващи в нетерминали: S->AB */
public class NonTerminalProduction {
    private char s;
    private char a;
    private char b;
    
    public NonTerminalProduction(char s, char a, char b) {
        this.setS(s);
        this.setA(a);
        this.setB(b);
    }
    
    public char getS() {
        return s;
    }
    
    public void setS(char s) {
        this.s = s;
    }
    
    public char getA() {
        return a;
    }
    
    public void setA(char a) {
        this.a = a;
    }
    
    public char getB() {
        return b;
    }
    
    public void setB(char b) {
        this.b = b;
    }
}