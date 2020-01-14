package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue{
    private int val;

    public IntValue(int v){val=v;}
    public int getVal() {return val;}
    public String toString() {  return " ( Int Value "+Integer.toString(val)+" ) "; }
    public IType getType() { return new IntType();}
    public BoolValue equals(IValue mirror) {
        IntValue mirrorVal = (IntValue)mirror;
        boolean result = val == mirrorVal.getVal();
        return new BoolValue(result);
    }
}
