package Model.ADT;

public interface IDictionary<K,V> {
    V lookup(K key);
    void add(K key, V value);
    void remove(K key);
    boolean isDefined(K key);
    void update(K key, V value);
}