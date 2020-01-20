package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ADT.IStack;
import Model.Exp.*;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class RepeatStmt implements IStmt{
    private IExp expression;
    private IStmt execute;

    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> tbl = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();
        IValue result = expression.eval(tbl, heap);

        if(!(result.getType() instanceof BoolType))
            throw new ProgramException("Invalid looping condition!");

        IStack<IStmt> executor = state.getStk();
        //executor.push(execute);
        IExp newCondition = new RelationalBoolExp("==",expression, new ValueExp(new BoolValue(false)));
        executor.push(new WhileStmt(newCondition, execute));

        return null;
    }

    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws ProgramException {
        if(!(expression.typecheck(typeEnv) instanceof BoolType))
            throw new ProgramException("Expresion of while is not correct");
        execute.typecheck(typeEnv);
        return typeEnv;
    }

    public String toString() {
        return "( Repeat Cond: !"
                +expression.toString()
                +" with Stmt: "
                +execute.toString()
                +")";
    }

    public RepeatStmt(IExp whileCond, IStmt step) { expression = whileCond; execute = step; }
}
