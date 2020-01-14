package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Operator.AndOperator;
import Model.Operator.ILogicOperator;
import Model.Operator.OrOperator;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.ProgramException;

public class LogicExp implements IExp{
    private IExp e1;
    private IExp e2;
    private ILogicOperator op;

    public LogicExp(IExp firstExp, IExp secondExp, String operator) {
        e1 = firstExp;
        e2 = secondExp;
        op = operator.equals("&&") ? new AndOperator()
                : operator.equals("||") ? new OrOperator()
                : null;
    }

    public IValue eval(IDictionary<String,IValue> tbl, IHeap<IValue> heap) throws ProgramException {
        IValue resultFirstExp = e1.eval(tbl, heap);
        if(!resultFirstExp.getType().equals(new BoolType()))
            throw new ProgramException("First operand is not boolean!");

        IValue resultSecondExp = e2.eval(tbl, heap);
        if(!resultSecondExp.getType().equals(new BoolType()))
            throw new ProgramException("Second operand is not boolean!");

        BoolValue firstValue = (BoolValue)resultFirstExp;
        BoolValue secondValue = (BoolValue)resultSecondExp;

        return op.process(firstValue, secondValue);
    }

    public String toString() {
        return " ( Logic Exp " +
                op.toString() +
                " Between " +
                e1.toString() +
                " And " +
                e2.toString() +
                " ) ";
    }

    public IType typecheck(IDictionary<String, IType> typeEnv) throws ProgramException {
        if(op == null)
            throw new ProgramException("Invalid Operator!");
        IType firstType, secondType;
        firstType = e1.typecheck(typeEnv);
        secondType = e2.typecheck(typeEnv);
        if(firstType.equals(new IntType())) {
            if(secondType.equals(new IntType())) {
                return new IntType();
            }
            else
                throw new ProgramException("second operand is not an integer");
        }
        else
            throw new ProgramException("first operand is not an integer");
    }
}
