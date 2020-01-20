package Model.Operator;

import Model.Value.BoolValue;

public class OrOperator implements ILogicOperator {

    public OrOperator() {}

    public BoolValue process(BoolValue firstValue, BoolValue secondValue) {
        boolean result = firstValue.getVal() || secondValue.getVal();

        return new BoolValue(result);
    }

    public String toString() {
        return " ( Or ) ";
    }
}