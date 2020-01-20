package Model.ViewRepresentations;

import Model.Value.IValue;
import javafx.beans.property.SimpleStringProperty;

public class HeapRepresentation {
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty value = new SimpleStringProperty("");

    public HeapRepresentation() {

    }

    public HeapRepresentation(Integer address, IValue value) {
        setAddress(address.toString());
        setValue(value.toString());
    }

    public void setAddress(String addressString) {
        address.set(addressString);
    }

    public void setValue(String valueString) {
        value.set(valueString);
    }

    public String getAddress() {
        return address.get();
    }
    public String getValue() {
        return value.get();
    }
}
