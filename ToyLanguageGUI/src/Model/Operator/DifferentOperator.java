package Model.Operator;

import Model.Value.BoolValue;
import Model.Value.IntValue;

public class DifferentOperator implements IRelationalOperator {
    public BoolValue process(IntValue firstValue, IntValue secondValue) {
        boolean result = firstValue.getVal() != secondValue.getVal();

        return new BoolValue(result);
    }

    public String toString() {
        return " ( Different ) ";
    }
}