package Model.ADT;

import java.util.Stack;

public class ProgramStack<T> implements IStack<T> {
    private Stack<T> stack;

    public ProgramStack() {
        stack = new Stack<T>();
    }

    public T pop() {
        return stack.pop();
    }

    public void push(T value) {
        stack.push(value);
    }

    public boolean isEmpty() { return stack.empty(); }

    public String toString() {
        if(stack.empty())
            return "";
        return stack.peek().toString();
    }
}
