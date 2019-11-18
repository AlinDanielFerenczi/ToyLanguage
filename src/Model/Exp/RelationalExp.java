package Model.Exp;

import Model.ADT.IDictionary;
import Model.Operator.*;
import Model.ProgramException;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class RelationalExp implements IExp{
    private IExp e1;
    private IExp e2;
    private IRelationalOperator op;

    public RelationalExp(IExp firstExp, IExp secondExp, String operator) {
        e1 = firstExp;
        e2 = secondExp;
        op = operator.equals("<=") ? new LessEqualOperator()
                : operator.equals("<") ? new LessOperator()
                : operator.equals(">=") ? new GreaterEqualOperator()
                : operator.equals(">") ? new GreaterOperator()
                : operator.equals("!=") ? new DifferentOperator()
                : operator.equals("==") ? new EqualOperator()
                : null;
    }

    public IValue eval(IDictionary<String,IValue> tbl) throws ProgramException {
        if(op == null)
            throw new ProgramException("Invalid Operator!");

        IValue resultFirstExp = e1.eval(tbl);
        if(!resultFirstExp.getType().equals(new IntType()))
            throw new ProgramException("First operand is not integer!");

        IValue resultSecondExp = e2.eval(tbl);
        if(!resultSecondExp.getType().equals(new IntType()))
            throw new ProgramException("Second operand is not integer!");

        IntValue firstValue = (IntValue)resultFirstExp;
        IntValue secondValue = (IntValue)resultSecondExp;

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
}
