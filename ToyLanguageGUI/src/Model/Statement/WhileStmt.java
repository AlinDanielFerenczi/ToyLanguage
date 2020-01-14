package Model.Statement;

import Model.ADT.*;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.BoolType;
import Model.Type.IType;
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

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        if(!(expression.typecheck(typeEnv) instanceof BoolType))
            throw new ProgramException("Expresion of while is not correct");
        execute.typecheck(typeEnv);
        return typeEnv;
    }
}