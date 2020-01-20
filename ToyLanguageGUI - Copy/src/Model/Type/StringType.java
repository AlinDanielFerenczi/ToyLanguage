package Model.Type;

import Model.Value.IValue;
import Model.Value.StringValue;

public class StringType implements IType {
    public boolean equals(Object another){ return another instanceof StringType; }
    public String toString() { return " ( StringType ) "; }
    public IValue defaultValue() { return new StringValue(""); }
}