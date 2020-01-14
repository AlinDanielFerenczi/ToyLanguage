package Model.Commands;

import Controller.Controller;
import Model.ProgramException;

public class RunExample extends Command {
    private Controller ctr;

    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }

    public void execute() {
        ctr.allStep();
    }

    public Controller getCtr() { return ctr; }
}