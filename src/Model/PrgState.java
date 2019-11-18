package Model;

import Model.ADT.IDictionary;
import Model.ADT.IList;
import Model.ADT.IStack;
import Model.Statement.IStmt;
import Model.Value.IValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IDictionary<String, BufferedReader> fileTable;
    private IList<IValue> out;
    //private IStmt originalProgram; //optional field, but good to have
    public PrgState(IStack<IStmt> stk, IDictionary<String,IValue> symTbl,
             IList<IValue> ot, IDictionary<String, BufferedReader> fileTbl, IStmt prg)
    {
        exeStack=stk;
        symTable=symTbl;
        out = ot;
        fileTable = fileTbl;
        //originalProgram=deepCopy(prg);  //recreate the entire original prg
        stk.push(prg);
    }

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
}