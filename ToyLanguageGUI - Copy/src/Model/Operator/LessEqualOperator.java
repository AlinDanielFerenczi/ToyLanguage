package Model.Operator;

import Model.Value.BoolValue;
import Model.Value.IntValue;

public class LessEqualOperator implements IRelationalOperator {
    public LessEqualOperator() {}

    public BoolValue process(IntValue firstValue, IntValue secondValue) {
        boolean result = firstValue.getVal() <= secondValue.getVal();

        return new BoolValue(result);
    }
    public BoolValue process(BoolValue firstValue, BoolValue secondValue) {
        boolean result = firstValue.getVal() != secondValue.getVal();

        return new BoolValue(result);
    }

    @Override
    public String toString() {
        return " ( Less Equal ) ";
    }
}