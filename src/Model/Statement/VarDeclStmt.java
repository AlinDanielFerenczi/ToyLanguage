package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Value.IValue;

public class VarDeclStmt implements IStmt {
    private String id;
    private IType type;

    public VarDeclStmt(String varId, IType varType)
    {
        id = varId;
        type = varType;
    }
    public PrgState execute(PrgState state) throws ProgramException
    {
        IDictionary<String, IValue> symTbl = state.getSymTable();

        if (symTbl.isDefined(id))
            throw new ProgramException("Variable is already declared!");

        IValue newVariable = type.defaultValue();;
        symTbl.add(id,newVariable);

        return state;
    }
    public String toString() {
        return " ( Declare Variable of Type " + type.toString() + " with Id "+id+" ) ";
    }
}

