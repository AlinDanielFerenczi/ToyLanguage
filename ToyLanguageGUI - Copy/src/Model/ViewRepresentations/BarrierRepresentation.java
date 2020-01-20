package Model.ViewRepresentations;

import Model.Value.IValue;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class BarrierRepresentation {
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty value = new SimpleStringProperty("");
    private final SimpleListProperty<Integer> list = new SimpleListProperty<Integer>(new SimpleListProperty<Integer>());

    public BarrierRepresentation() {

    }

    public BarrierRepresentation(Integer address, Integer value, ArrayList<Integer> listInput) {
        ObservableList<Integer> list = FXCollections.observableArrayList(listInput);
        setAddress(address.toString());
        setValue(value.toString());
        setList(list);
    }

    public void setAddress(String addressString) {
        address.set(addressString);
    }

    public void setValue(String valueString) {
        value.set(valueString);
    }

    public void setList(ObservableList<Integer> listValue) {
        list.set(listValue);
    }

    public String getAddress() {
        return address.get();
    }
    public String getValue() {
        return value.get();
    }
    public ObservableList<Integer> getList() {
        return list.get();
    }
}
