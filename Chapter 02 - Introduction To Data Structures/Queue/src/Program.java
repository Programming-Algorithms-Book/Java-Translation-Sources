public class Program {
    private static final int MAX = 16;

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        for (int i = 0; i < 2 * MAX; i++) {
            queue.enqueue(i);
            int itemInFront = queue.dequeue();
            System.out.print(itemInFront + " ");
        }

        System.out.println("\n");

        queue.enqueue(123);
        System.out.println("\nПоглед над най-предния елемент: " + queue.peek());

        queue.dequeue();

        // Това ще причини грешка при изключване, тъй като опашката е празна
        try {
            queue.dequeue();
        } catch (IllegalStateException ise) {
            System.out.println("\n" + ise.getMessage());
        }
    }
}
