package Model.Statement;

import Model.ADT.IDictionary;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.StringType;

public class NopStmt implements IStmt {
    public PrgState execute(PrgState state) throws ProgramException
    {
        return null;
    }

    public NopStmt() {}

    public String toString() {
        return " ( Nope )";
    }

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        return typeEnv;
    }
}
