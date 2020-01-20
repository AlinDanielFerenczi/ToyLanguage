package Model.Statement;

import Model.ADT.IDictionary;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws ProgramException;
    IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException;
}
