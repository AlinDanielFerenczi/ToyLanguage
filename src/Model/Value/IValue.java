package Model.Value;

import Model.Type.IType;

public interface IValue {
    IType getType();
    BoolValue equals(IValue mirror);
}

