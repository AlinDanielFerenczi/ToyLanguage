package Model.Operator;

import Model.Value.BoolValue;

public class AndOperator implements ILogicOperator {

    public AndOperator() {}

    public BoolValue process(BoolValue firstValue, BoolValue secondValue) {
        boolean result = firstValue.getVal() && secondValue.getVal();

        return new BoolValue(result);
    }

    public String toString() {
        return " ( And ) ";
    }
}