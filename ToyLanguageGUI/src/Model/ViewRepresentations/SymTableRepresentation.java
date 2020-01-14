package Model.ViewRepresentations;

import Model.Value.IValue;
import javafx.beans.property.SimpleStringProperty;

public class SymTableRepresentation {
    private final SimpleStringProperty variableName = new SimpleStringProperty("");
    private final SimpleStringProperty value = new SimpleStringProperty("");

    public SymTableRepresentation() {}

    public SymTableRepresentation(String name, IValue value) {
        setVariableName(name);
        setValue(value.toString());
    }

    private void setVariableName(String addressString) {
        variableName.set(addressString);
    }

    public void setValue(String valueString) {
        value.set(valueString);
    }

    public String getVariableName() {
        return variableName.get();
    }
    public String getValue() {
        return value.get();
    }
}
