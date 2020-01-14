package Model.Operator;

import Model.ProgramException;
import Model.Value.BoolValue;

public interface ILogicOperator {
    BoolValue process(BoolValue firstExp, BoolValue secondExp) throws ProgramException;
}
