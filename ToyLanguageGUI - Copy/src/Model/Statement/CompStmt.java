package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt firstOne, IStmt secondOne)
    {
        first = firstOne;
        snd = secondOne;
    }

    public String toString() {
        return " ( Comp " +
                first.toString() +
                "; "
                + snd.toString() +
                " ) ";
    }

    public PrgState execute(PrgState state) throws ProgramException {
        IStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);

        return null;
    }

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        return snd.typecheck(first.typecheck(typeEnv));
    }
}
