package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ADT.IStack;
import Model.ADT.ProgramStack;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class WhileStmt implements IStmt {
    private IExp expression;
    private IStmt execute;
    public PrgState execute(PrgState state) throws ProgramException
    {
        IDictionary<String, IValue> tbl = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();
        IValue result = expression.eval(tbl, heap);

        if(!(result.getType() instanceof BoolType))
            throw new ProgramException("Invalid looping condition!");

        BoolValue shouldContinue = (BoolValue)result;

        if(shouldContinue.getVal())
        {
            IStack<IStmt> executor = state.getStk();
            executor.push(this);
            executor.push(execute);
        }

        return null;
    }

    public WhileStmt(IExp whileCond, IStmt step) { expression = whileCond; execute = step; }

    public String toString() {
        return " ( While )";
    }
}
