package Model.Operator;

import Model.Value.BoolValue;
import Model.Value.IntValue;

public interface IRelationalOperator {
    BoolValue process(IntValue firstValue, IntValue secondValue);
}
