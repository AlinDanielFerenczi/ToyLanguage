package Model.ADT;

import java.util.ArrayList;
import java.util.List;

public class ProgramList<T> implements IList<T> {
    private List<T> list;

    public ProgramList() {
        list = new ArrayList<T>();
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
}
