public class Tree {

    /* Символ (буква) */
    private char character;

    /* Общо тегло на дървото */
    private int weight;

    /* Ляв и десен наследници */
    private Tree left;

    private Tree right;

    public Tree() {
    }
    
    public Tree(char character, int weight) {
        this.setCharacter(character);
        this.setWeight(weight);
    }
    
    public char getCharacter() {
        return this.character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Tree getLeft() {
        return this.left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return this.right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
}
