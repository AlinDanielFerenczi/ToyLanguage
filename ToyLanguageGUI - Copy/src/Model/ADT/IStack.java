package Model.ADT;

import java.util.stream.Stream;

public interface IStack<T>{
    T pop();
    void push(T value);
    boolean isEmpty();
    Stream<T> stream();
}

