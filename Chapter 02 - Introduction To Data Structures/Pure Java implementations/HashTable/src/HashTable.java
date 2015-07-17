import sun.plugin.dom.exception.InvalidStateException;

import java.util.LinkedList;

public class HashTable<TKey, TValue> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double MAX_LOAD = 0.75;

    private LinkedList<Entry<TKey, TValue>>[] chains;
    private double currentLoad;
    private int count;
    private int filledChainsCount;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Капацитета на хеш-таблицата трябва да е положително число.");
        }

        this.chains = this.getNewChains(capacity);
        this.currentLoad = 0;
        this.count = 0;
        this.filledChainsCount = 0;
    }

    public int size() {
        return this.count;
    }

    public void add(TKey key, TValue value) {
        if (key == null) {
            throw new IllegalArgumentException("Ключът на елемент от хеш-таблицата не може да бъде null.");
        }

        Entry<TKey, TValue> existingEntry = this.find(key);
        if (existingEntry != null) {
            throw new InvalidStateException("Вече се съдържа елемент с посочения ключ в хеш-таблицата.");
        }

        this.ensureCapacity();

        int chainIndex = this.getChainIndex(key);
        if (this.chains[chainIndex] == null) {
            this.chains[chainIndex] = new LinkedList<>();
            this.filledChainsCount++;
        }

        Entry<TKey, TValue> newEntry = new Entry<>(key, value);
        this.chains[chainIndex].addLast(newEntry);

        this.count++;
        this.currentLoad = this.filledChainsCount / this.chains.length;
    }

    public TValue get(TKey key) {
        if (key == null) {
            throw new IllegalArgumentException("Ключът на елемент от хеш-таблицата не може да бъде null.");
        }

        TValue result = null;

        Entry<TKey, TValue> entry = this.find(key);
        if (entry != null) {
            result = entry.getValue();
        }

        return result;
    }

    public void update(TKey key, TValue value) {
        if (key == null) {
            throw new IllegalArgumentException("Ключът на елемент от хеш-таблицата не може да бъде null.");
        }

        Entry<TKey, TValue> existingEntry = this.find(key);
        if (existingEntry == null) {
            throw new IllegalStateException("Няма елемент с посочения ключ в хеш-таблицата.");
        }

        existingEntry.setValue(value);
    }

    private Entry<TKey, TValue> find(TKey key) {
        Entry<TKey, TValue> result = null;

        int chainIndex = this.getChainIndex(key);
        if (this.chains[chainIndex] != null) {
            for (Entry<TKey, TValue> entry : this.chains[chainIndex]) {
                if (entry.getKey().equals(key)) {
                    result = entry;
                    break;
                }
            }
        }

        return result;
    }

    private int getChainIndex(TKey key) {
        // Запазваме стойността положителна
        int hashCode = key.hashCode() & Integer.MAX_VALUE;
        int chainIndex = hashCode % this.chains.length;

        return chainIndex;
    }

    private void ensureCapacity() {
        if (this.currentLoad > MAX_LOAD) {
            int newCapacity = this.chains.length * 2;
            LinkedList<Entry<TKey, TValue>>[] oldChains = this.chains;
            this.chains = getNewChains(newCapacity);
            for (LinkedList<Entry<TKey, TValue>> chain : oldChains) {
                if (chain != null) {
                    for (Entry<TKey, TValue> entry : chain) {
                        int newChainIndex = this.getChainIndex(entry.getKey());
                        if (this.chains[newChainIndex] == null) {
                            this.chains[newChainIndex] = new LinkedList<>();
                        }

                        this.chains[newChainIndex].addLast(entry);
                    }
                }
            }
        }
    }

    private LinkedList<Entry<TKey, TValue>>[] getNewChains(int capacity) {
        return (LinkedList<Entry<TKey, TValue>>[]) new LinkedList<?>[capacity];
    }
}
