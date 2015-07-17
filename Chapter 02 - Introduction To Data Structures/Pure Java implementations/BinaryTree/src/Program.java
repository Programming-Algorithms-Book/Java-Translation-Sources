import java.util.Random;

public class Program {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer, String> tree = new BinaryTree<>();

        // Включва 10 върха с произволни ключове
        for (int i = 0; i < 10; i++) {
            int key = (random.nextInt() % 20) + 1;
            System.out.println("Вмъква се елемент с ключ " + key);
            tree.add(key, "someinfo" + i);
        }

        System.out.println("Дърво: ");
        System.out.println(tree + "\n");

        // Претърсва за елемента с ключ 5
        TreeNode<Integer, String> result = tree.find(5);
        if (result != null) {
            System.out.println("Намерен е: " + result.getValue());
        }

        // Изтрива произволни 10 върха от дървото
        for (int i = 0; i < 10; i++) {
            try {
                int key = (random.nextInt() % 20) + 1;
                System.out.println("Изтрива се елемента с ключ " + key);
                tree.remove(key);
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }

        System.out.println("Дърво: ");
        System.out.println(tree);
    }
}
