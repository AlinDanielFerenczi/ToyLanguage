package Model.Operator;

import Model.ProgramException;
import Model.Value.IntValue;

public class DivideOperator implements IArithmeticOperator {

    public DivideOperator() {}

    public IntValue process(IntValue firstValue, IntValue secondValue) throws ProgramException {
        int value = secondValue.getVal();
        if(value == 0)
            throw new ProgramException("division by 0");

        int result = firstValue.getVal() / secondValue.getVal();

        return new IntValue(result);
    }

    @Override
    public String toString() {
        return " ( Divide )";
    }
}