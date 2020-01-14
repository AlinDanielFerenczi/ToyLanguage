package Model.Operator;

import Model.Value.BoolValue;
import Model.Value.IntValue;

public class EqualOperator implements IRelationalOperator {
    public EqualOperator() {}
    public BoolValue process(IntValue firstValue, IntValue secondValue) {
        boolean result = firstValue.getVal() == secondValue.getVal();

        return new BoolValue(result);
    }

    public String toString() {
        return " ( Equal ) ";
    }
}