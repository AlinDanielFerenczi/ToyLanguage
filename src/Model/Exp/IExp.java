package Model.Exp;

import Model.ADT.IDictionary;
import Model.Value.IValue;
import Model.ProgramException;

public interface IExp {
    IValue eval(IDictionary<String,IValue> tbl) throws ProgramException;
}

