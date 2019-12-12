package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class IfStmt implements IStmt {
    private IExp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(IExp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "( If " + exp.toString() + " Then " + thenS.toString()
                + " Else " + elseS.toString() + " )";
    }

    public PrgState execute(PrgState state) throws ProgramException
    {
        IStack<IStmt> stack = state.getStk();
        IDictionary<String, IValue> symTbl = state.getSymTable();
        IValue cond = exp.eval(symTbl,state.getHeap());

        if(!cond.getType().equals(new BoolType()))
            throw new ProgramException("Invalid type for conditional expression!");

        BoolValue value = (BoolValue)cond;
        boolean innerValue = value.getVal();

        if(innerValue)
            stack.push(thenS);
        else
            stack.push(elseS);

        return null;
    }
}
