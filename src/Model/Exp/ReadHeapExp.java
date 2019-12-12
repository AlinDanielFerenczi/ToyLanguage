package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ProgramException;
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

    public IValue eval(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws ProgramException {
        IValue targetValue = expression.eval(tbl, heap);

        if(!(targetValue instanceof RefValue))
            throw new ProgramException("Invalid ref type!");

        RefValue targetRef = (RefValue)targetValue;

        int targetAddr = targetRef.getAddr();

        if(!heap.isDefined(targetAddr))
            throw new ProgramException("Not defined!");

        return heap.lookup(targetAddr);
    }
}
