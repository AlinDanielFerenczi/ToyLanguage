package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.RefValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WriteHeapExp implements IExp{
    private IExp expression;
    private String name;
    public WriteHeapExp(String variable, IExp targetExp) {name=variable; expression = targetExp;}

    public String toString() {
        return " ( Writing to "+name+" the value"+expression.toString()+") ";
    }

    public synchronized IValue eval(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws ProgramException {
        Lock l = new ReentrantLock();
        l.lock();

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
        l.unlock();

        return new RefValue(variableAddr,newValue.getType());
    }

    public IType typecheck(IDictionary<String,IType> typeEnv) throws ProgramException{
        IType typ=expression.typecheck(typeEnv);
        IType target = typeEnv.lookup(name);
        if(!(target instanceof RefType))
            throw new ProgramException("Target is not a ref");
        RefType targetReference = (RefType) target;
        if (typ.equals(targetReference.getInner())) {
            return targetReference;
        } else
            throw new ProgramException("the rH argument is not a Ref Type");
    }
}
