package Model;

import Model.ADT.*;
import Model.Statement.IStmt;
import Model.Type.IType;
import Model.Value.IValue;

import java.io.*;

public class PrgState {
    private static int id = 0;
    private int threadId;
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IDictionary<String, BufferedReader> fileTable;
    private IList<IValue> out;
    private IHeap<IValue> heap;
    //private IStmt originalProgram; //optional field, but good to have
    public PrgState(IStack<IStmt> stk, IDictionary<String,IValue> symTbl,
             IList<IValue> ot, IDictionary<String, BufferedReader> fileTbl, IHeap<IValue> heapDict, IStmt prg) throws ProgramException {
        if(heapDict.getContent().keySet().isEmpty() && symTbl.getContent().keySet().isEmpty()) {
            ProgramDictionary<String, IType> typeEnv = new ProgramDictionary<String, IType>();
            prg.typecheck(typeEnv);
            try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter("typeEnv.txt", true)))) {
                String response = typeEnv.toString();
                logFile.append("\n------------------\n");
                logFile.append(response);
            } catch (IOException exception)
            {
                throw new ProgramException(exception.getMessage());
            }
        }
        threadId=manageId();
        exeStack=stk;
        symTable=symTbl;
        out = ot;
        fileTable = fileTbl;
        heap = heapDict;
        //originalProgram=deepCopy(prg);  //recreate the entire original prg
        exeStack.push(prg);
    }

    public boolean isNotCompleted() { return !exeStack.isEmpty(); };
    public IStack<IStmt> getStk() {
        return exeStack;
    }
    public IDictionary<String, IValue> getSymTable()
    {
        return symTable;
    }
    public IList<IValue> getOutTable()
    {
        return out;
    }
    public IDictionary<String, BufferedReader> getFileTable() { return fileTable; };
    public IHeap<IValue> getHeap() { return heap; }

    private IStmt deepCopy(IStmt original) {
        try {
            return (IStmt)((PrgState)original).clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String toString() {
        return exeStack.toString();
    }

    private static synchronized int manageId() {
        id++;
        return id;
    }

    public int getId() {
        return threadId;
    }

    public PrgState oneStep() throws ProgramException{
        if(exeStack.isEmpty())
            throw new ProgramException("Prgstate stack is empty");

        try {
            IStmt crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        } catch (Exception ex) {
            throw new ProgramException(ex.getMessage());
        }
    }
}