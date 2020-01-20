package Model.ADT;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramBarrier implements IBarrier{
    private int free = 1;
    private Map<Integer, Pair<Integer, ArrayList<Integer>>> dictionary;

    public ProgramBarrier() {
        dictionary = new HashMap<Integer,Pair<Integer, ArrayList<Integer>>>();
    }

    public Pair<Integer, ArrayList<Integer>> lookup(int address) {
        return dictionary.get(address);
    }

    public int add(Pair<Integer, ArrayList<Integer>> content) {
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

    public void update(int address, Pair<Integer, ArrayList<Integer>> value) {
        dictionary.put(address, value);
    }

    public Map<Integer, Pair<Integer, ArrayList<Integer>>> getContent() {
        return dictionary;
        //.entrySet()
        //.parallelStream()
        //.map(entry->new HashMap.SimpleEntry<Integer,IValue>(entry.getKey(),(IValue)entry.getValue()))
        //.collect(Collectors.toSet());
    }

    public void setContent(Map<Integer, Pair<Integer, ArrayList<Integer>>> entries) {
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
