package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Value.IValue;
import Model.ProgramException;

public class ValueExp implements IExp{
    private IValue value;
    public ValueExp(IValue holdThis) { value = holdThis;  }
    public IValue eval(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws ProgramException { return value; }

    public String toString() {
        return " ( Value Exp "+value.toString()+" ) ";
    }
}
