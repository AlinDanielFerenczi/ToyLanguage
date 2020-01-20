package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

public class ReadHeapExp implements IExp{
    private IExp expression;
    public ReadHeapExp(IExp targetExp) {
        expression = targetExp;
    }

    public String toString() {
        return " ( Reading Heap from"+expression.toString()+") ";
    }

    public synchronized IValue eval(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws ProgramException {
        IValue targetValue = expression.eval(tbl, heap);

        if(!(targetValue instanceof RefValue))
            throw new ProgramException("Invalid ref type!");

        RefValue targetRef = (RefValue)targetValue;

        int targetAddr = targetRef.getAddr();

        if(!heap.isDefined(targetAddr))
            throw new ProgramException("Not defined!");

        return heap.lookup(targetAddr);
    }

    public IType typecheck(IDictionary<String,IType> typeEnv) throws ProgramException{
        IType typ=expression.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new ProgramException("the rH argument is not a Ref Type");
    }
}
