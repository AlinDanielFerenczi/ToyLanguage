package Model.Type;

import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class IntType implements IType{
    public boolean equals(Object another){ return another instanceof IntType; }
    public String toString() { return " ( IntType ) "; }
    public IValue defaultValue() { return new IntValue(0); }
}
