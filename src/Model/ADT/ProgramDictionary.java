package Model.ADT;

import java.util.Map;
import java.util.HashMap;

public class ProgramDictionary<K,V> implements IDictionary<K,V>{
    private Map<K,V> dictionary;

    public ProgramDictionary() {
        dictionary = new HashMap<K,V>();
    }

    public V lookup(K key) {
        return dictionary.get(key);
    }

    public void add(K key, V value) {
        dictionary.put(key, value);
    }

    public void remove(K key) {
        dictionary.remove(key);
    }

    public boolean isDefined(K key) {
        return dictionary.get(key) != null;
    }

    public void update(K key, V value) {
        dictionary.put(key, value);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        dictionary.keySet().forEach(x-> stringBuilder.append(x.toString())
                .append(" =")
                .append(dictionary.get(x).toString()));

        return stringBuilder.toString();
    }
}