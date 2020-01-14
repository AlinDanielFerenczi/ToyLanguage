package Model.Type;

import Model.Value.IValue;

public interface IType {
    IValue defaultValue();
    boolean equals(Object another);
    String toString();
}

