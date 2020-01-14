package Controller;

import Model.ADT.ProgramDictionary;
import Model.ADT.ProgramHeap;
import Model.ADT.ProgramList;
import Model.ADT.ProgramStack;
import Model.Commands.Command;
import Model.Commands.RunExample;
import Model.Exp.*;
import Model.PrgState;
import Model.ProgramException;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.ProgramRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectionViewController implements Initializable {
    private ProgramRepository createPrograms() {
        ProgramList<PrgState> listOfThreads;
        ProgramRepository menu = new ProgramRepository();

        if(new File("log1.txt").delete())
            System.out.println("Log 1 deleted!");
        if(new File("log2.txt").delete())
            System.out.println("Log 2 deleted!");
        if(new File("log3.txt").delete())
            System.out.println("Log 3 deleted!");
        if(new File("log4.txt").delete())
            System.out.println("Log 4 deleted!");
        if(new File("log5.txt").delete())
            System.out.println("Log 5 deleted!");
        if(new File("log6.txt").delete())
            System.out.println("Log 6 deleted!");
        if(new File("log7.txt").delete())
            System.out.println("Log 7 deleted!");
        if(new File("log8.txt").delete())
            System.out.println("Log 8 deleted!");
        if(new File("log9.txt").delete())
            System.out.println("Log 9 deleted!");
        if(new File("log10.txt").delete())
            System.out.println("Log 10 deleted!");

        IStmt ex1 = new CompStmt(new VarDeclStmt("v",
                new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        try {
            PrgState prg1 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex1);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg1);
            Repository repository1 = new Repository(listOfThreads, "log1.txt");
            Controller controller1 = new Controller(repository1, true);
            menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        try {
            PrgState prg2 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex2);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg2);
            Repository repository2 = new Repository(listOfThreads, "log2.txt");
            Controller controller2 = new Controller(repository2, true);
            menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        try {
            PrgState prg3 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex3);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg3);
            Repository repository3 = new Repository(listOfThreads, "log3.txt");
            Controller controller3 = new Controller(repository3, true);
            menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\user\\Documents\\test.txt"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));
        try {
            PrgState prg4 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex4);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg4);
            Repository repository4 = new Repository(listOfThreads, "log4.txt");
            Controller controller4 = new Controller(repository4, true);
            menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        try {
            PrgState prg5 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex5);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg5);
            Repository repository5 = new Repository(listOfThreads, "log5.txt");
            Controller controller5 = new Controller(repository5, true);
            menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex6 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+',new ReadHeapExp( new ReadHeapExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
        try {
            PrgState prg6 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex6);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg6);
            Repository repository6 = new Repository(listOfThreads, "log6.txt");
            Controller controller6 = new Controller(repository6, true);
            menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex7 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new AssignStmt("v",new WriteHeapExp("v",new ValueExp(new IntValue(30)))),
                                        new PrintStmt(new ArithExp('+',new ReadHeapExp(new VarExp("v")),new ValueExp(new IntValue(5))))))));
        try {
            PrgState prg7 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex7);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg7);
            Repository repository7 = new Repository(listOfThreads, "log7.txt");
            Controller controller7 = new Controller(repository7, true);
            menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex8 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v",new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp( new ReadHeapExp(new VarExp("a")))))))));
        try {
            PrgState prg8 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex8);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg8)
            ;
            Repository repository8 = new Repository(listOfThreads, "log8.txt");
            Controller controller8 = new Controller(repository8, true);
            menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        IStmt ex9 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(">",new VarExp("v"),new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
        try {
            PrgState prg9 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex9);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg9);
            Repository repository9 = new Repository(listOfThreads, "log9.txt");
            Controller controller9 = new Controller(repository9, true);
            menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }
        IStmt ex10 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("a",new WriteHeapExp("a",new ValueExp(new IntValue(30)))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt( new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt( new ReadHeapExp(new VarExp("a")))))))));

        try {
            PrgState prg10 = new PrgState(new ProgramStack<IStmt>(), new ProgramDictionary<String, IValue>(),
                    new ProgramList<IValue>(), new ProgramDictionary<String, BufferedReader>(), new ProgramHeap<IValue>(), ex10);
            listOfThreads = new ProgramList<PrgState>();
            listOfThreads.add(prg10);
            Repository repository10 = new Repository(listOfThreads, "log10.txt");
            Controller controller10 = new Controller(repository10, true);
            menu.addCommand(new RunExample("10", ex10.toString(), controller10));
        } catch (ProgramException e) {
            System.out.println(e.toString());
        }

        return menu;
    }
    @FXML
    private Text selectedIdField;
    private int selectedId;
    private ProgramRepository listOfAvailablePrograms;

    public ListView<String> listView;

    public void initialize(URL url, ResourceBundle resourceBundle)  {
        listOfAvailablePrograms = createPrograms();
        ObservableList<String> items = FXCollections.observableArrayList();

        for (Command command:listOfAvailablePrograms.getCommands().values()) {
            items.add(String.format("%4s : %s", command.getKey(), command.getDescription()));
        }

        listView.setItems(items);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedIdString = listView
                        .getSelectionModel()
                        .getSelectedItem()
                        .split(":")[0]
                        .trim();
                selectedIdField.setText("Selected program:  "+selectedIdString);
                selectedId = Integer.parseInt(selectedIdString);
            }
        });
    }

    @FXML
    private void submitSelection(ActionEvent actionEvent) throws IOException {
        double screenRation = 0.75;
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\View\\programWindow.fxml"));
        Parent root = loader.load();
        stage.setTitle("Toy Language");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Scene newScene = new Scene(root, screenSize.getWidth() * screenRation, screenSize.getHeight() * screenRation);
        stage.setScene(newScene);
        ProgramViewController targetController = (ProgramViewController) loader.getController();
        RunExample requestedCommand = (RunExample) listOfAvailablePrograms.getCommands().values().toArray()[selectedId-1];
        targetController.setTargetProgram(requestedCommand.getCtr());
    }
}
