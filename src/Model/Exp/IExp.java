package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Value.IValue;
import Model.ProgramException;

public interface IExp {
    IValue eval(IDictionary<String,IValue> tbl, IHeap<IValue> heap) throws ProgramException;
}

