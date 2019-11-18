package View;

import Controller.Controller;
import Model.ADT.ProgramDictionary;
import Model.ADT.ProgramList;
import Model.ADT.ProgramStack;
import Model.Commands.ExitCommand;
import Model.Commands.RunExample;
import Model.Exp.ArithExp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
import Model.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {
        ProgramList<PrgState> listOfThreads;

        IStmt ex1 = new CompStmt(new VarDeclStmt("v",
                new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), ex1);
        listOfThreads = new ProgramList<PrgState>();
        listOfThreads.add(prg1);
        Repository repository1 = new Repository(listOfThreads, "log1.txt");
        Controller controller1 = new Controller(repository1, false);


        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        PrgState prg2 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), ex2);
        listOfThreads = new ProgramList<PrgState>();
        listOfThreads.add(prg2);
        Repository repository2 = new Repository(listOfThreads, "log1.txt");
        Controller controller2 = new Controller(repository2, false);

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        PrgState prg3 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), ex3);
        listOfThreads = new ProgramList<PrgState>();
        listOfThreads.add(prg3);
        Repository repository3 = new Repository(listOfThreads, "log1.txt");
        Controller controller3 = new Controller(repository3, false);

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\user\\Documents\\test.txt"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));

        PrgState prg4 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), ex4);
        listOfThreads = new ProgramList<PrgState>();
        listOfThreads.add(prg4);
        Repository repository4 = new Repository(listOfThreads, "log1.txt");
        Controller controller4 = new Controller(repository4, false);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        try {
            menu.show();
        } catch (Exception exception) {
            System.out.println("Bye!");
        }
    }
}