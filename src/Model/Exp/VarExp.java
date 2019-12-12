package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ProgramException;
import Model.Value.IValue;

public class VarExp implements IExp{
    private String id;
    public VarExp(String varID) { id = varID; }
    public IValue eval(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws ProgramException  { return tbl.lookup(id); }
    public String toString() {
        return " ( Var Exp with Id "+id+" )";
    }
}
