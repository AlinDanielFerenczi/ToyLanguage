package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.RefValue;

public class NewStmt  implements IStmt{
    private String id;
    private IExp exp;

    public NewStmt(String assignId, IExp assignExp) {
        id = assignId;
        exp = assignExp;
    }

    public String toString() {
        return " ( New " + id + " = " + exp.toString() + " ) ";
    }

    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> symTbl = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        if (!symTbl.isDefined(id))
            throw new ProgramException("The used variable " + id + " was not declared before");

        IType typeId = (symTbl.lookup(id)).getType();

        if (!(typeId instanceof RefType))
            throw new ProgramException("Declared type of variable " +
                    id +
                    " and type of the assigned expression do not match");

        IValue val = exp.eval(symTbl, heap);

        if(!((RefType)typeId).getInner().equals(val.getType()))
            throw new ProgramException("Incompatible types");

        IValue newAddress = new RefValue(heap.add(val),val.getType());
        symTbl.update(id,newAddress);

        return null;
    }

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        IType typevar = typeEnv.lookup(id);
        IType typexp = exp.typecheck(typeEnv);
        if (!typevar.equals(new RefType(typexp)))
            throw new ProgramException("NEW stmt: right hand side and left hand side have different types ");
        typeEnv.add(id,new RefType(typexp));
        return typeEnv;
    }
}
