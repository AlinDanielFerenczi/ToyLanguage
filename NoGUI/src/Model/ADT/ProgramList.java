package Model.ADT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProgramList<T> implements IList<T> {
    private List<T> list;

    public ProgramList() {
        list = new ArrayList<T>();
    }

    public ProgramList(T[] input) {
        list = new ArrayList<T>(Arrays.asList(input));
    }

    public Stream<T> stream() {
        return list.stream();
    }

    public boolean contains(T elem) {
        return list.contains(elem);
    }

    public void add(T value) {
        list.add(value);
    }

    public void remove(T value) {
        list.remove(value);
    }

    public T current(int index) throws IndexOutOfBoundsException{
        if(list.isEmpty())
            throw new IndexOutOfBoundsException("Not enough elements for action!");

        return list.get(index);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(x-> stringBuilder.append(x.toString()));

        return stringBuilder.toString();
    }

    public IList<T> getContents() {
        return null;
    }

    public int size() {
        return list.size();
    }

    public void addAll(List<T> newPrgList) {
        list.addAll(newPrgList);
    }
}
