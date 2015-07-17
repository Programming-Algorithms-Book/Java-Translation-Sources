class Entry<TKey, TValue> {
    private TKey key;
    private TValue value;

    public Entry(TKey key, TValue value) {
        this.setKey(key);
        this.setValue(value);
    }

    public TKey getKey() {
        return this.key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public TValue getValue() {
        return this.value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("[%s -> %s]", this.getKey().toString(), this.getValue().toString());
    }
}
