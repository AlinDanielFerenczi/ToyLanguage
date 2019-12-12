package Model.ADT;

import Model.Value.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface IHeap<V> {
    V lookup(int key);
    int add(V value);
    void remove(int key);
    boolean isDefined(int key);
    void update(int key, V value);
    Map<Integer,V> getContent();
    void setContent(Map<Integer,V> entries);
}
