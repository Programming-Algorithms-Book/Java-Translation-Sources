import java.util.Arrays;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Program {
    public static void main(String[] args) {
        final String Message = "afbabcdefacbabcdecde";
        Tree tree = huffman(Message);
        System.out.printf("Дърво на Хъфман за %s:", Message);
        printTree(tree, 0);
        writeCodes(tree, new StringBuilder());
    }

    private static Tree huffman(String msg) {
        /* Построяване на таблица на честотите на срещане */
        HashMap<Character, Integer> freqs = new HashMap<>();

        for (char character : msg.toCharArray()) {
            if (freqs.containsKey(character)) {
                freqs.replace(character, freqs.get(character) + 1);
            } else {
                freqs.put(character, 1);
            }
        }

        SortedSet<Character> keys = new TreeSet<Character>(freqs.keySet());
        HeapForest forest = new HeapForest();

        /*
         * За всеки символ с ненулева честота на срещане се създава тривиално
         * дърво
         */
        for (char ch : keys) {
            forest.add(new Tree(ch, freqs.get(ch)));
        }

        while (forest.getSize() > 1) {
            Tree minTree1 = forest.getMin(); /*
                                              * Намиране на двата най-редки
                                              * върха
                                              */
            Tree minTree2 = forest.getMin();

            /* Създаване на нов възел - обединение на двата най-редки */
            Tree tree = new Tree();
            tree.setLeft(minTree1);
            tree.setRight(minTree2);
            tree.setWeight(minTree1.getWeight() + minTree2.getWeight());

            forest.add(tree);
        }

        return forest.getMin();
    }

    /* Извежда дървото на екрана */
    private static void printTree(Tree tree, int h) {
        if (tree != null) {
            printTree(tree.getLeft(), h + 1);

            char[] chars = new char[3 * h];
            Arrays.fill(chars, ' ');
            System.out.print(new String(chars));
            System.out.printf("%1$4s", tree.getWeight());
            if (tree.getLeft() == null) {
                System.out.printf(" %s", tree.getCharacter());
            }

            System.out.println();
            printTree(tree.getRight(), h + 1);
        }
    }

    /* Извежда кодовете */
    private static void writeCodes(Tree tree, StringBuilder code) {
        if (tree != null) {
            code.append('0');
            writeCodes(tree.getLeft(), code);
            code.setLength(code.length() - 1);
            if (tree.getLeft() == null) { /*
                                           * Всеки връх на Хъфм. дърво има 0 или
                                           * 2 наследника
                                           */
                System.out.printf("%s = %s\n", tree.getCharacter(),
                        code.toString());
            }

            code.append('1');
            writeCodes(tree.getRight(), code);
            code.setLength(code.length() - 1);
        }
    }
}
