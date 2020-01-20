package Model.Statement;

import Model.ADT.IBarrier;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.RefValue;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrier implements IStmt{
    private String id;
    private IExp exp;

    public NewBarrier(String assignId, IExp assignExp) {
        id = assignId;
        exp = assignExp;
    }

    public String toString() {
        return " ( NewBarrier " + id + " = " + exp.toString() + " ) ";
    }

    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> symTbl = state.getSymTable();
        IBarrier barrierTable = state.getBarrier();
        if (!symTbl.isDefined(id))
            throw new ProgramException("The used variable " + id + " was not declared before");

        IValue valType = exp.eval(symTbl, state.getHeap());
        if(!valType.getType().equals(new IntType()))
            throw new ProgramException("Invalid type not integer!");

        IntValue value = (IntValue) valType;

        Lock l = new ReentrantLock();
        l.lock();
        int address = barrierTable.add(new Pair<Integer, ArrayList<Integer>>(value.getVal(),new ArrayList<Integer> ()));
        symTbl.update(id,new IntValue(address));
        l.unlock();

        return null;
    }

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        IType typevar = typeEnv.lookup(id);
        IType typexp = exp.typecheck(typeEnv);
        if (!typevar.equals(new IntType()) || !typexp.equals(new IntType()))
            throw new ProgramException("Invalid barrier");
        typeEnv.add(id,new IntType());
        return typeEnv;
    }
}
