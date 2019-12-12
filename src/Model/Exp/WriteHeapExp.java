package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ProgramException;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.RefValue;

public class WriteHeapExp implements IExp{
    private IExp expression;
    private String name;
    public WriteHeapExp(String variable, IExp targetExp) {name=variable; expression = targetExp;}

    public String toString() {
        return " ( Writing to "+name+" the value"+expression.toString()+") ";
    }

    public IValue eval(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws ProgramException {
        if(!tbl.isDefined(name))
            throw new ProgramException("Not defined!");

        IValue variable = tbl.lookup(name);

        if(!(variable.getType() instanceof RefType))
            throw new ProgramException("Not reference");

        RefValue variableRef = (RefValue) variable;
        int variableAddr = variableRef.getAddr();

        if(!heap.isDefined(variableAddr))
            throw new ProgramException("Not defined in heap!");

        IValue newValue = expression.eval(tbl,heap);

        if(!((RefType)variableRef.getType()).getInner().equals(newValue.getType()))
            throw new ProgramException("Not compatible!");

        heap.update(variableAddr, newValue);

        return new RefValue(variableAddr,newValue.getType());
    }
}
