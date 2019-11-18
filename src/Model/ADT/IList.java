package Model.ADT;

public interface IList<T> {
    void add(T value);
    void remove(T value);
    T current(int index);
}