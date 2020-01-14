package View;

import Controller.Controller;
import Model.ADT.ProgramDictionary;
import Model.ADT.ProgramHeap;
import Model.ADT.ProgramList;
import Model.ADT.ProgramStack;
import Model.Commands.Command;
import Model.ProgramException;
import Repository.ProgramRepository;
import Model.Commands.ExitCommand;
import Model.Commands.RunExample;
import Model.Exp.*;
import Model.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.Repository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.Map;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception{
        double screenRation = 0.75;
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Toy Language");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Scene newScene = new Scene(root, screenSize.getWidth() * screenRation, screenSize.getHeight() * screenRation);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
