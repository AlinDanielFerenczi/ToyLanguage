package Model.Value;

import Model.Type.IType;
import Model.Type.RefType;

public class RefValue implements IValue{
    private int address;
    private IType locationType;
    public int getAddr() {return address;}
    public IType getType() { return new RefType(locationType);}
    public BoolValue equals(IValue mirror) {
        IType mirrorType = mirror.getType();
        return new BoolValue(locationType.equals(mirrorType));
    }
    public RefValue(int addressId, IType targetType) {address = addressId; locationType = targetType;}

    public String toString() {
        return " ( Ref Value at Address "+address+" with type"+locationType.toString()+") ";
    }
}