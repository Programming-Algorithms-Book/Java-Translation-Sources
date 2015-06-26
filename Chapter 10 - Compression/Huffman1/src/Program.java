import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        final String Message = "afbabcdefacbabcdecde";
        Tree tree = huffman(Message);
        System.out.printf("Дърво на Хъфман за %s:\n", Message);
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

        ArrayList<Tree> forest = new ArrayList<Tree>();
        /*
         * За всеки символ с ненулева честота на срещане се създава тривиално
         * дърво
         */
        for (char ch : freqs.keySet()) {
            forest.add(new Tree(ch, freqs.get(ch), freqs.get(ch)));
        }

        while (forest.size() > 1) {
            /*
             * Намиране на двата най-редки върха
             */
            MinResult result = findMins(forest);
            int i = result.getMin1();
            int j = result.getMin2();

            /* Създаване на нов възел - обединение на двата най-редки */
            Tree tree = new Tree();
            tree.setLeft(forest.get(i));
            tree.setRight(forest.get(j));
            tree.setWeight(forest.get(i).getWeight()
                    + forest.get(j).getWeight());
            forest.set(i, tree);

            /* j-тото дърво не е нужно повече. Заместване с последното. */
            forest.set(j, forest.get(forest.size() - 1));
            forest.remove(forest.size() - 1);
        }

        return forest.get(0);
    }

    /* Намира двата най-редки елемента */
    private static MinResult findMins(List<Tree> forest) {
        int min1 = 0;
        int min2 = 0;

        if (forest.get(0).getWeight() <= forest.get(1).getWeight()) {
            min1 = 0;
            min2 = 1;
        } else {
            min1 = 1;
            min2 = 0;
        }

        for (int i = 2; i < forest.size(); i++) {
            if (forest.get(i).getWeight() < forest.get(min1).getWeight()) {
                min2 = min1;
                min1 = i;
            } else if (forest.get(i).getWeight() < forest.get(min2).getWeight()) {
                min2 = i;
            }
        }

        return new MinResult(min1, min2);
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
