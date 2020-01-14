package Model.ADT;

import java.util.List;
import java.util.stream.Stream;

public interface IList<T> {
    void add(T value);
    void remove(T value);
    T current(int index);
    boolean contains(T elem);
    IList<T> getContents();
    Stream<T> stream();
    int size();

    void addAll(List<T> newPrgList);
}