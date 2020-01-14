package Model.Statement;

import Model.PrgState;
import Model.ProgramException;

public interface IStmt {
    PrgState execute(PrgState state) throws ProgramException;
}
