package Model.Exp;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Operator.*;
import Model.ProgramException;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

import java.util.Arrays;
import java.util.List;

public class ArithExp implements IExp {
    private IExp e1;
    private IExp e2;
    private IArithmeticOperator op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(char operator, IExp exp1, IExp exp2) {
        e1 = exp1;
        e2 = exp2;
        op = operator == '+' ? new SumOperator()
                : operator == '-' ? new SubtractOperator()
                : operator == '*' ? new MultiplyOperator()
                : operator == '/' ? new DivideOperator()
                : null;
    }

    public IValue eval(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws ProgramException {
        if (op == null)
            throw new ProgramException("Invalid Operator!");

        IValue v1,v2;
        IntType targetType = new IntType();

        v1= e1.eval(tbl, heap);
        if (!v1.getType().equals(targetType))
            throw new ProgramException("First operand is not an integer!");
        v2 = e2.eval(tbl, heap);
        if (!v2.getType().equals(targetType))
            throw new ProgramException("Second operand is not an integer!");

        IntValue i1 = (IntValue)v1;
        IntValue i2 = (IntValue)v2;

        return op.process(i1, i2);
    }

    public String toString() {
        return " ( Arithmetic Exp " +
                op.toString() +
                " Between " +
                e1.toString() +
                " And " +
                e2.toString() +
                " ) ";
    }
}
