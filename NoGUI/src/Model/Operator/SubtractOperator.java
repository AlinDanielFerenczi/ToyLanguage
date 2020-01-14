package Model.Operator;

import Model.Value.IntValue;

public class SubtractOperator implements IArithmeticOperator {

    public SubtractOperator() {}

    public IntValue process(IntValue firstValue, IntValue secondValue) {
        int result = firstValue.getVal() - secondValue.getVal();

        return new IntValue(result);
    }

    public String toString() {
        return " ( Subtract ) ";
    }
}