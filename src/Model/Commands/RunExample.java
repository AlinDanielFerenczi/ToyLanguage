package Model.Commands;

import Controller.Controller;
import Model.ProgramException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep(); }
        catch (ProgramException exception) {
            System.out.println(exception);
        } //here you must treat the exceptions that can not be solved in the controller
    }
}