package Repository;

import Model.ADT.IList;
import Model.ADT.ProgramDictionary;
import Model.ADT.ProgramList;
import Model.ADT.ProgramStack;
import Model.Exp.ArithExp;
import Model.Exp.VarExp;
import Model.Exp.ValueExp;
import Model.PrgState;
import Model.ProgramException;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;

public class Repository implements IRepository{
    private IList<PrgState> listOfThreads;
    private String logFilePath;

    public Repository(ProgramList<PrgState> program, String targetFilePath) {
        logFilePath = targetFilePath;

        listOfThreads = program;
    }

    public IList<PrgState> getPrgList() { return listOfThreads; }

    public void setPrgList(IList<PrgState> newThreads) { listOfThreads = newThreads; }

    public String toString() {
        return "Repository";
    }

    public void logPrgStateExec(PrgState current) throws ProgramException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            String response = formatter.format(date)+ " Thread "+ current.getId()+": " +
                    "\n Current STMT in Stack: " + current.getStk().toString()+
                    "\n Symbol Table: " + current.getSymTable().toString()+
                    "\n File Table: " + current.getFileTable().toString()+
                    "\n OutputTable: " + current.getOutTable().toString() +
                    "\n Heap: " +current.getHeap().toString() +
                    "\n Barrier: " +current.getBarrier().toString() +
                    "\n";
            logFile.append(response);
        } catch (IOException exception)
        {
            throw new ProgramException(exception.getMessage());
        }

    }
}
