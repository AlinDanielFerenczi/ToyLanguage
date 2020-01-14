package Controller;

import Model.ADT.IList;
import Model.PrgState;
import Model.Value.IValue;
import Model.ViewRepresentations.HeapRepresentation;
import Model.ViewRepresentations.SymTableRepresentation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ProgramViewController implements Initializable {
    public TextField selectedIdField;
    private Controller programController;
    public TableView<HeapRepresentation> heapTable;
    public ListView<String> fileTable;
    public ListView<String> prgStateIdentifiers;
    public ListView<String> outTable;
    public TableView<SymTableRepresentation> symTable;
    public ListView<String> exeStack;
    private Integer targetState = 0;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<HeapRepresentation,String> addressColumn = new TableColumn<HeapRepresentation,String>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<HeapRepresentation,String>("address"));
        addressColumn.prefWidthProperty().bind(heapTable.widthProperty().multiply(0.3));
        TableColumn<HeapRepresentation,String> valueColumn = new TableColumn<HeapRepresentation,String>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<HeapRepresentation,String>("value"));
        valueColumn.prefWidthProperty().bind(heapTable.widthProperty().multiply(0.7));

        ArrayList<TableColumn<HeapRepresentation, String>> heapColumns = new ArrayList<TableColumn<HeapRepresentation, String>>();
        heapColumns.add(addressColumn);
        heapColumns.add(valueColumn);
        heapTable.getColumns().addAll(heapColumns);

        TableColumn<SymTableRepresentation,String> variableNameColumn = new TableColumn<SymTableRepresentation,String>("Name");
        variableNameColumn.setCellValueFactory(new PropertyValueFactory<SymTableRepresentation,String>("variableName"));
        variableNameColumn.prefWidthProperty().bind(symTable.widthProperty().multiply(0.3));
        TableColumn<SymTableRepresentation,String> variableValueColumn = new TableColumn<SymTableRepresentation,String>("Value");
        variableValueColumn.setCellValueFactory(new PropertyValueFactory<SymTableRepresentation,String>("value"));
        variableValueColumn.prefWidthProperty().bind(symTable.widthProperty().multiply(0.7));

        ArrayList<TableColumn<SymTableRepresentation, String>> symTableColumns = new ArrayList<TableColumn<SymTableRepresentation, String>>();
        symTableColumns.add(variableNameColumn);
        symTableColumns.add(variableValueColumn);
        symTable.getColumns().addAll(symTableColumns);

        prgStateIdentifiers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedIdString = prgStateIdentifiers
                        .getSelectionModel()
                        .getSelectedItem()
                        .split(":")[0]
                        .trim();
                selectedIdField.setText("Selected program:  "+selectedIdString);
                targetState = Integer.parseInt(selectedIdString);
                updateFields();
            }
        });
    }

    private void updateFields() {
        updateHeapTable();
        updatePrgIdentifiers();
        updateOutTable();
        updateFileTable();
        updateSymTable();
        updateExeTable();
    }

    private void updateHeapTable() {
        ObservableList<HeapRepresentation> heapTableContents = FXCollections.observableArrayList();
        IList<PrgState> prgList = programController.getPrgList();
        if(prgList.size() > 0)
            prgList.current(0)
                    .getHeap()
                    .getContent()
                    .forEach((key, value) -> heapTableContents.add(new HeapRepresentation(key, value)));
        heapTable.setItems(heapTableContents);
    }

    private void updateSymTable() {
        ObservableList<SymTableRepresentation> symTableContents = FXCollections.observableArrayList();
        IList<PrgState> prgList = programController.getPrgList();

        if(prgList.size() > 0)
            prgList.current(0)
                    .getSymTable()
                    .getContent()
                    .forEach((key, value) -> symTableContents.add(new SymTableRepresentation(key, value)));

        symTable.setItems(symTableContents);
    }

    private void updatePrgIdentifiers() {
        ObservableList<String> prgStateContents = FXCollections.observableArrayList();

        programController.getPrgList()
                .stream()
                .forEach(prg->prgStateContents.add(programController.getPrgList().find(prg)+" : "+prg.toString()));

        prgStateIdentifiers.setItems(prgStateContents);
    }

    private void updateOutTable() {
        ObservableList<String> outTableContents = FXCollections.observableArrayList();

        IList<PrgState> prgList = programController.getPrgList();
        if(prgList.size() > 0)
            prgList.current(targetState)
                    .getOutTable()
                    .stream()
                    .forEach(entry->outTableContents.add(entry.toString()));

        outTable.setItems(outTableContents);
    }

    private void updateFileTable() {
        ObservableList<String> fileTableContents = FXCollections.observableArrayList();
        IList<PrgState> prgList = programController.getPrgList();

        if(prgList.size() > 0)
            prgList.current(targetState)
                    .getFileTable()
                    .getContent()
                    .keySet()
                    .forEach(entry->fileTableContents.add(entry.toString()));

        fileTable.setItems(fileTableContents);
    }

    private void updateExeTable() {
        ObservableList<String> exeTableContents = FXCollections.observableArrayList();
        IList<PrgState> prgList = programController.getPrgList();

        if(prgList.size() > 0)
            prgList.current(targetState)
                    .getStk()
                    .stream()
                    .forEach(entry->exeTableContents.add(entry.toString()));

        exeStack.setItems(exeTableContents);
    }

    void setTargetProgram(Controller ctr) {
        programController = ctr;
        updateFields();
    }

    public void oneStep(ActionEvent actionEvent) {
        programController.runOneStep();
        targetState = 0;
        selectedIdField.setText("Selected program: "+0);
        updateFields();
    }
}
