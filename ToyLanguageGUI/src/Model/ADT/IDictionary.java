package Model.ADT;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface IDictionary<K,V> extends Cloneable {
    V lookup(K key);
    void add(K key, V value);
    void remove(K key);
    boolean isDefined(K key);
    void update(K key, V value);
    Map<K,V> getContent();
    IDictionary<K,V> clone() throws CloneNotSupportedException;
}