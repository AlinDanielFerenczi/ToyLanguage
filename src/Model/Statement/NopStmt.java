package Model.Statement;

import Model.PrgState;
import Model.ProgramException;

public class NopStmt implements IStmt {
    public PrgState execute(PrgState state) throws ProgramException
    {
        return null;
    }

    public NopStmt() {}

    public String toString() {
        return " ( Nope )";
    }
}
