package Model.Statement;

import Model.ADT.*;
import Model.PrgState;
import Model.ProgramException;
import Model.Value.IValue;

import java.io.BufferedReader;
import java.util.Map;

public class ForkStmt implements IStmt{
    private IStmt futureStart;
    public ForkStmt(IStmt startStatement) {
        futureStart = startStatement;
    }

    private IDictionary<String, IValue> deepCopy(IDictionary<String, IValue> original) {
        IDictionary<String, IValue> copy = new ProgramDictionary<String, IValue>();
        for (Map.Entry<String, IValue> entry : original.getContent().entrySet())
        {
            copy.add(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    public PrgState execute(PrgState state) throws ProgramException {
        return new PrgState(new ProgramStack<IStmt>(),
                deepCopy(state.getSymTable()),
                state.getOutTable(),
                state.getFileTable(),
                state.getHeap(),
                futureStart);
    }
}
