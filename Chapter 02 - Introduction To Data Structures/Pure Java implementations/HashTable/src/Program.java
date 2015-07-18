public class Program {
    public static void main(String[] args) {
        HashTable<Integer, Integer> hashTable = new HashTable<>();

        // Добавя елементи в хеш-таблицата
        hashTable.add(1234, 100);
        hashTable.add(1774, 120);
        hashTable.add(86, 180);

        System.out.println("Елемент с ключ 86: " + hashTable.get(86));
        System.out.println("Елемент с ключ 1234: " + hashTable.get(1234));
        System.out.println("Елемент с ключ 1774: " + hashTable.get(1774));

        // Извлича елемент с несъществуващ в хеш-таблицата ключ
        System.out.println("Елемент с ключ 1773: " + hashTable.get(1773));

        // Обновява стойността на елемента с ключ 1234
        hashTable.update(1234, 555);
        System.out.println("Елемент с ключ 1234: " + hashTable.get(1234));

        // Премахва елемента с ключ 1234
        hashTable.remove(1234);
        System.out.println("Елемент с ключ 1234: " + hashTable.get(1234));

        System.out.println("Брой елементи в хеш-таблицата: " + hashTable.size());
        hashTable.clear();
        System.out.println("Брой елементи в хеш-таблицата: " + hashTable.size());
    }
}
