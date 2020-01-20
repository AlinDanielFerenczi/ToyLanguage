package Model.Statement;

import Model.ADT.IBarrier;
import Model.ADT.IDictionary;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import javafx.util.Pair;

import java.util.ArrayList;

public class AwaitStmt implements IStmt{
    private String id;

    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws ProgramException {
        return typeEnv;
    }

    public AwaitStmt(String idString) { id = idString; }

    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> symTbl = state.getSymTable();
        IBarrier barrierTable = state.getBarrier();

        if (!symTbl.isDefined(id))
            throw new ProgramException("The used variable " + id + " was not declared before");

        IValue valType = symTbl.lookup(id);

        if(!valType.getType().equals(new IntType()))
            throw new ProgramException("Invalid type not integer!");

        IntValue value = (IntValue) valType;

        Pair<Integer, ArrayList<Integer>> barrier = barrierTable.lookup(value.getVal());

        if(barrier.getKey() > barrier.getValue().size()) {
            if (!barrier.getValue().contains(state.getId()))
                barrier.getValue().add(state.getId());
            state.getStk().push(this);
        }

        return null;
    }

    public String toString() {
        return "( Await )";
    }
}
