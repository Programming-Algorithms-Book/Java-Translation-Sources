import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class HashTable<TKey, TValue> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float MAX_LOAD = 0.75f;

    private LinkedList<Entry<TKey, TValue>>[] chains;
    private float currentLoad;
    private int count;
    private int filledChainsCount;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Капацитета на хеш-таблицата трябва да е положително число.");
        }

        this.chains = (LinkedList<Entry<TKey, TValue>>[]) new LinkedList<?>[capacity];
        this.currentLoad = 0.0f;
        this.count = 0;
        this.filledChainsCount = 0;
    }

    public int size() {
        return this.count;
    }

    public TValue get(TKey key) {
        throw new NotImplementedException();
    }
}
