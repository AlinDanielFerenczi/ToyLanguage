package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IList;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Value.IValue;

public class PrintStmt implements IStmt {
    private IExp exp;

    public PrintStmt(IExp printThis) {
        exp = printThis;
    }
    public String toString() {
        return " ( Printing " + exp.toString() + " ) ";
    }
    public PrgState execute(PrgState state) throws ProgramException
    {
        IList<IValue> out = state.getOutTable();
        IDictionary<String, IValue> symTbl=state.getSymTable();
        out.add(exp.eval(symTbl, state.getHeap()));
        return null;
    }
    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
