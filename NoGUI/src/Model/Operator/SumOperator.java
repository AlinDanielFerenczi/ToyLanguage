package Model.Operator;

import Model.Value.IntValue;

public class SumOperator implements IArithmeticOperator {

    public SumOperator() {}

    public IntValue process(IntValue firstValue, IntValue secondValue) {
        int result = firstValue.getVal() + secondValue.getVal();

        return new IntValue(result);
    }

    public String toString() {
        return " ( Sum ) ";
    }
}
