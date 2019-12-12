package Model.ADT;

import Model.Value.IValue;
import com.sun.jdi.Value;

import java.util.*;
import java.util.stream.Collectors;

public class ProgramHeap<V> implements IHeap<V>{
    private int free = 1;
    private Map<Integer,V> dictionary;

    public ProgramHeap() {
        dictionary = new HashMap<Integer,V>();
    }

    public V lookup(int address) {
        return dictionary.get(address);
    }

    public int add(V content) {
        dictionary.put(free, content);
        free++;
        return free-1;
    }

    public void remove(int address) {
        dictionary.remove(address);
    }

    public boolean isDefined(int address) {
        return dictionary.containsKey(address);
    }

    public void update(int address, V value) {
        dictionary.put(address, value);
    }

    public Map<Integer, V> getContent() {
        return dictionary;
                //.entrySet()
                //.parallelStream()
                //.map(entry->new HashMap.SimpleEntry<Integer,IValue>(entry.getKey(),(IValue)entry.getValue()))
                //.collect(Collectors.toSet());
    }

    public void setContent(Map<Integer, V> entries) {
        dictionary = entries;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        dictionary.keySet().forEach(x-> stringBuilder.append(x.toString())
                .append("->")
                .append(dictionary.get(x).toString()));

        return stringBuilder.toString();
    }
}
