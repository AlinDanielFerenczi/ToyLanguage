package Model.ADT;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public interface IBarrier {
    int add(Pair<Integer, ArrayList<Integer>> value);
    void remove(int key);
    boolean isDefined(int key);
    void update(int key, Pair<Integer, ArrayList<Integer>> value);
    Map<Integer, Pair<Integer, ArrayList<Integer>>> getContent();
    void setContent(Map<Integer, Pair<Integer, ArrayList<Integer>>> entries);
    Pair<Integer, ArrayList<Integer>> lookup(int key);
}
