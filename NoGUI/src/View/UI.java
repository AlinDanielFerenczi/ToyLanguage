package View;

import Controller.Controller;
import Model.ADT.ProgramList;
import Model.ProgramException;
import Repository.Repository;

import java.util.Scanner;

public class UI {
    private Controller executor;
    public UI() { };
    public void main() throws ProgramException {
        Scanner in = new Scanner(System.in);
        int target;
        do
        {
            System.out.println("Select program:");
            target = Integer.parseInt(in.nextLine());
            Repository repository = new Repository(new ProgramList<>(),"C:\\Users\\user\\Documents\\");
            if(target != -1) {
                executor = new Controller(repository, true);
                executor.allStep();
            }
        } while(target != -1);
    }

    public Controller getExecutor() {
        return executor;
    }
}
