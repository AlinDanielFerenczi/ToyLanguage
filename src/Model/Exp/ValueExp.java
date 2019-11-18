package Model.Exp;

import Model.ADT.IDictionary;
import Model.Value.IValue;
import Model.ProgramException;

public class ValueExp implements IExp{
    private IValue value;
    public ValueExp(IValue e) { value = e;  }
    public IValue eval(IDictionary<String,IValue> tbl) throws ProgramException { return value; }

    public String toString() {
        return " ( Value Exp "+value.toString()+" ) ";
    }
}
