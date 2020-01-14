package Model.Value;

import Model.Type.BoolType;
import Model.Type.IType;

public class BoolValue implements IValue{
    private boolean val;

    public BoolValue(boolean v){val=v;}
    public boolean getVal() {return val;}
    public String toString() { return " ( Bool Value "+Boolean.toString(val)+" ) "; }
    public IType getType() { return new BoolType();}
    public BoolValue equals(IValue mirror) {
        BoolValue mirrorVal = (BoolValue)mirror;
        boolean result = val == mirrorVal.getVal();
        return new BoolValue(result);
    }
}
