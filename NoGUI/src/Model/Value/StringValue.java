package Model.Value;

import Model.Type.IType;
import Model.Type.StringType;

public class StringValue implements IValue{
    private String val;

    public StringValue(String v) { val=v; }
    public String getVal() { return val; }
    public String toString() {  return " ( String Value " + val + " ) "; }
    public IType getType() { return new StringType();}
    public BoolValue equals(IValue mirror) {
        StringValue mirrorVal = (StringValue)mirror;
        boolean result = val.equals(mirrorVal.getVal());
        return new BoolValue(result);
    }
}
