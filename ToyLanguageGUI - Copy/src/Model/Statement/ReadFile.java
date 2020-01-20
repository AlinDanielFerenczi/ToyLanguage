package Model.Statement;

import Model.ADT.IDictionary;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt{
    private IExp pathExp;
    private String storage;
    public ReadFile(IExp targetFileExp, String id) {
        pathExp = targetFileExp;
        storage = id;
    }
    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> tbl = state.getSymTable();
        IValue pathValue = pathExp.eval(tbl, state.getHeap());
        IValue storageValue = tbl.lookup(storage);

        if(!pathValue.getType().equals(new StringType()))
            throw new ProgramException("First operator is not a string!");
        if(!storageValue.getType().equals(new IntType()))
            throw new ProgramException("Second operator is not a integer!");

        String path = ((StringValue)pathValue).getVal();
        IDictionary<String, BufferedReader> fileTbl = state.getFileTable();

        if(!fileTbl.isDefined(path))
            throw new ProgramException("File is not accessible!");

        try {
            String newValue = fileTbl.lookup(path).readLine();
            tbl.add(storage, new IntValue(Integer.parseInt(newValue)));
        }
        catch (IOException exception) {
            tbl.add(storage, new IntType().defaultValue());
        }

        return null;
    }

    public String toString() {
        return " ( Read File " + pathExp.toString() + " Into " + storage.toString() + " ) ";
    }

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        if(!pathExp.typecheck(typeEnv).equals(new StringType()))
            throw new ProgramException("Read invalid path");
        return typeEnv;
    }
}
