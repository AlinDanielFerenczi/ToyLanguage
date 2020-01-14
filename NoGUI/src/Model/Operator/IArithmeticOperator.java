package Model.Operator;

import Model.ProgramException;
import Model.Value.IntValue;

public interface IArithmeticOperator {
    IntValue process(IntValue firstExp, IntValue secondExp) throws ProgramException;
}
