/**
 * Продукции, отиващи в терминали: S->a
 */
public class Production {
    private char s;
    private char a;

    public Production(char s, char a) {
        this.setA(a);
        this.setS(s);
    }

    public char getS() {
        return this.s;
    }

    public void setS(char s) {
        this.s = s;
    }

    public char getA() {
        return this.a;
    }

    public void setA(char a) {
        this.a = a;
    }
}