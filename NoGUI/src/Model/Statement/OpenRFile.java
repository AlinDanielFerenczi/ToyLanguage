package Model.Statement;

import Model.ADT.IDictionary;
import Model.Exp.IExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.*;

public class OpenRFile implements IStmt{
    private BufferedReader bufferedReader;
    private IExp pathExp;

    public OpenRFile(IExp exp) { pathExp = exp;}

    public PrgState execute(PrgState state) throws ProgramException {
        IDictionary<String, IValue> tbl = state.getSymTable();
        IValue result = pathExp.eval(tbl,state.getHeap());

        if(!result.getType().equals(new StringType()))
            throw new ProgramException("Operator is not a string");

        String path = ((StringValue)result).getVal();

        try {
            File file = new File(path);
            bufferedReader = new BufferedReader(new FileReader(file));
            state.getFileTable().add(path, bufferedReader);
        } catch (FileNotFoundException exception) {
            throw new ProgramException("File could not be opened!");
        }

        return null;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public String toString() {
        return " ( Open Read File " + pathExp.toString() + " ) ";
    }
}
