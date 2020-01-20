package Model.Statement;

import Model.ADT.IDictionary;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.*;

public class CloseRFile implements IStmt {
    private IExp pathExp;

    public CloseRFile(IExp exp) {
        pathExp = exp;
    }

    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> tbl = state.getSymTable();
        IValue result = pathExp.eval(tbl,state.getHeap());

        if (!result.getType().equals(new StringType()))
            throw new ProgramException("Operator is not a string");

        String path = ((StringValue) result).getVal();

        try {
            IDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.isDefined(path))
                throw new ProgramException("File is not accessible!");
            fileTable.lookup(path).close();
            fileTable.remove(path);
        } catch (IOException exception) {
            throw new ProgramException("File could not be closed!");
        }

        return null;
    }

    public String toString() {
        return " ( Open Read File " + pathExp.toString() + " ) ";
    }

    public IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws ProgramException {
        if (!pathExp.typecheck(typeEnv).equals(new StringType()))
            throw new ProgramException("Close invalid path\"");
        return typeEnv;
    }
}