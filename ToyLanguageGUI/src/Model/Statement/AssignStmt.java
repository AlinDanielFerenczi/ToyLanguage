package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;

public class AssignStmt implements IStmt {
    private String id;
    private IExp exp;

    public AssignStmt(String assignId, IExp assignExp) {
        id = assignId;
        exp = assignExp;
    }

    public String toString() {
        return " ( Assigning " + id + " = " + exp.toString() + " ) ";
    }

    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> symTbl = state.getSymTable();

        if (!symTbl.isDefined(id))
            throw new ProgramException("The used variable " + id + " was not declared before");

        IValue val = exp.eval(symTbl, state.getHeap());
        IType typeId = (symTbl.lookup(id)).getType();

        if (!typeId.equals(new RefType(new IntType())) && !val.getType().equals(typeId))
            throw new ProgramException("Declared type of variable " +
                    id +
                    " and type of the assigned expression do not match");

        symTbl.update(id, val);

        return null;
    }

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        IType typevar = typeEnv.lookup(id);
        IType typexp = exp.typecheck(typeEnv);
        if(!typevar.equals(typexp))
            throw new ProgramException("Assignment: right hand side and left hand side have different types ");
        return typeEnv;
    }
}

