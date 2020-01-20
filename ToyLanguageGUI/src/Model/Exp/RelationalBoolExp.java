package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Operator.*;
import Model.ProgramException;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class RelationalBoolExp implements IExp{
    private IExp e1;
    private IExp e2;
    private IRelationalOperator op;

    public RelationalBoolExp( String operator, IExp firstExp, IExp secondExp) {
        e1 = firstExp;
        e2 = secondExp;
        op = operator.equals("!=") ? new DifferentOperator()
                : operator.equals("==") ? new EqualOperator()
                : null;
    }

    public IValue eval(IDictionary<String,IValue> tbl, IHeap<IValue> heap) throws ProgramException {
        if(op == null)
            throw new ProgramException("Invalid Operator!");

        IValue resultFirstExp = e1.eval(tbl, heap);
        if(!resultFirstExp.getType().equals(new BoolType()))
            throw new ProgramException("First operand is not bool!");

        IValue resultSecondExp = e2.eval(tbl, heap);
        if(!resultSecondExp.getType().equals(new BoolType()))
            throw new ProgramException("Second operand is not bool!");

        BoolValue firstValue = (BoolValue)resultFirstExp;
        BoolValue secondValue = (BoolValue)resultSecondExp;

        return op.process(firstValue, secondValue);
    }

    public String toString() {
        return " ( Relational Exp " +
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
        if(firstType.equals(new BoolType())) {
            if(secondType.equals(new BoolType())) {
                return new BoolType();
            }
            else
                throw new ProgramException("second operand is not an bool");
        }
        else
            throw new ProgramException("first operand is not an bool");
    }
}
