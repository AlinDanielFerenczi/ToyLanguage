package Model.Exp;

import Model.ADT.IDictionary;
import Model.ProgramException;
import Model.Value.IValue;

public class VarExp implements IExp{
    private String id;
    public VarExp(String varID) { id = varID; }
    public IValue eval(IDictionary<String,IValue> tbl) throws ProgramException  { return tbl.lookup(id); }
    public String toString() {
        return " ( Var Exp with Id "+id+" )";
    }
}
